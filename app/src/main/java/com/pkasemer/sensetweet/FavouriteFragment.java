package com.pkasemer.sensetweet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FavouriteFragment extends Fragment {

    RecyclerView recyclerViewxml;
    LinearLayoutManager layoutManager;
    List<Tweet> tweetList;
    Tweetadapter tweetadapter;
    private ProgressBar progressBar;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerViewxml = view.findViewById(R.id.favrecyclerView);
        progressBar = view.findViewById(R.id.idPBLoading);

        // creating new array list.
        tweetList = new ArrayList<>();

        // calling a method to
        // get all the courses.
        initRecyclerView(view);
        getAllCourses(view);

        return view;
    }

    private void getAllCourses(View view) {

        tweetList = new ArrayList<>();
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://infinite-plateau-16760.herokuapp.com/")
                // on below line we are calling add
                // Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // on below line we are calling a method to get all the courses from API.
        Call<List<Tweet>> call = retrofitAPI.getAllCourses();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<List<Tweet>>() {

            @Override
            public void onResponse(Call<List<Tweet>> call, retrofit2.Response<List<Tweet>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                System.out.println("in response before if");

                if (response.isSuccessful()) {

                    // on successful we are hiding our progressbar.
                    progressBar.setVisibility(View.GONE);

                    // below line is to add our data from api to our array list.
                    tweetList = response.body();

                    System.out.println("in response okello");

                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < tweetList.size(); i++) {
                        tweetadapter = new Tweetadapter(tweetList);
                        recyclerViewxml.setAdapter(tweetadapter);
                        tweetadapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Poor Network", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                // in the method of on failure we are displaying a
                // toast message for fail to get data.

                Toast.makeText(view.getContext(), "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView(View view) {
        layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewxml.setLayoutManager(layoutManager);

    }

}