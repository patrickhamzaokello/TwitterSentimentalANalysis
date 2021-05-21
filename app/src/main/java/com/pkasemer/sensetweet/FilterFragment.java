package com.pkasemer.sensetweet;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FilterFragment extends Fragment {

    private SenseDBHelper db;
    private Cursor cursor;
    List<Tweet> tweetList;
    private ProgressBar progressBar;


    public FilterFragment() {
        // Required empty public constructor
    }


    public static FilterFragment newInstance(String param1, String param2) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_filter, container, false);
        progressBar = view.findViewById(R.id.idPBLoading);


        RecyclerView recyclerView = view.findViewById(R.id.filterrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        db = new SenseDBHelper(view.getContext());
        tweetList = db.listContacts();
        if (tweetList.size() > 0) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Tweetadapter tweetadapter= new Tweetadapter(tweetList);
            recyclerView.setAdapter(tweetadapter);
            tweetadapter.notifyDataSetChanged();
        }
        else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(view.getContext(), "There is no tweet saved in database. Start adding now", Toast.LENGTH_LONG).show();
        }


        return  view;
    }


    @Override
    public  void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void onClickListenerMapsButton(View v, long id){
        Bundle bundle = new Bundle();
        bundle.putString("key", String.valueOf(id));
        GuideFragment nextFrag= new GuideFragment();
        nextFrag.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, nextFrag, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}