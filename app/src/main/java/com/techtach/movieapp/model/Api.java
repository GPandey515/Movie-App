package com.techtach.movieapp.model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by noones on 7/12/15.
 */


public interface Api {

    @GET("/discover/movie")
    void getData( @Query("sort_by") String sort, @Query("api_key") String key, Callback<ApiResponse> response);

}
