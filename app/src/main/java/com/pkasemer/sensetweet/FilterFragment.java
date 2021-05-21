package com.pkasemer.sensetweet;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class FilterFragment extends Fragment {

    private SQLiteDatabase db;
    private Cursor cursor;


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


        ListView listTweets = (ListView)view.findViewById(R.id.houselistview);
        SQLiteOpenHelper starbuzzDatabaseHelper = new SenseDBHelper(view.getContext());
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("TWEET",
                    new String[] {"_id", "name"},
                    null, null, null, null, null
            );

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(view.getContext(),
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"name"},
                    new int[]{android.R.id.text1}
            );

            listTweets.setAdapter(listAdapter);


        } catch (SQLException e){
            Toast toast = Toast.makeText(view.getContext(), "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickListenerMapsButton(view, id);
            }
        };

        //assign listener on list view
        listTweets.setOnItemClickListener(itemClickListener);


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