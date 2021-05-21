package com.pkasemer.sensetweet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


public class LocalTweets extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Tweet> tweetList;
    Tweetadapter tweetadapter;
    private ProgressBar progressBar;

    public LocalTweets() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getView();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_localtweets, container, false);
        progressBar = rootview.findViewById(R.id.idPBLoading);

        initData();
        initRecyclerView(rootview);
        return rootview;
    }


    private void initRecyclerView(View view) {
        progressBar.setVisibility(view.GONE);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        tweetadapter = new Tweetadapter(tweetList);
        recyclerView.setAdapter(tweetadapter);
        tweetadapter.notifyDataSetChanged();
    }

    private void initData() {
        tweetList = new ArrayList<>();
        tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                -4,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));

        tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));tweetList.add(new Tweet("1395363614975004675",
                "Starting in a few seconds, do not miss  Welcome to the ride make believe the real truth that exists",
                -0.1,
                0.2,
                "MM_Loembe",
                "Marguerite M. Loembe",
                "http://pbs.twimg.com/profile_images/1294326802182279169/jim68KJe_normal.jpg",
                "2021-05-20 12:59:50"));


    }
}