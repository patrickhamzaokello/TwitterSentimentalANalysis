package com.pkasemer.sensetweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("tweet")
        // as we are calling data from array so we are calling
        // it with array list and naming that method as getAllCourses();
    Call<List<Tweet>> getAllCourses();
}
