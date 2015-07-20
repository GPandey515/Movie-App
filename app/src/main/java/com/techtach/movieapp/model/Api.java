package com.techtach.movieapp.model;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by noones on 7/12/15.
 */


public interface Api {

    @GET("/discover/movie?sort_by=popularity.desc&api_key={API-KEY}")
    void getData(Callback<ApiResponse> response);

    @GET("/discover/movie?sort_by=vote_average.desc&api_key={API-KEY}")
    void getHighRated(Callback<ApiResponse> response);

}
