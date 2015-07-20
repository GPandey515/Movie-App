package com.techtach.movieapp.model;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by noones on 7/12/15.
 */


public interface Api {

    @GET("/discover/movie?sort_by=popularity.desc&api_key=ae066f4966c3265df3df63177b063567")
    void getData(Callback<ApiResponse> response);

    @GET("/discover/movie?sort_by=vote_average.desc&api_key=ae066f4966c3265df3df63177b063567")
    void getHighRated(Callback<ApiResponse> response);

}
