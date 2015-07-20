# Movie-App

###### Use your API key instead of `{API-KEY}` on App.java interface inside `model` package
`
    @GET("/discover/movie?sort_by=popularity.desc&api_key={API-KEY}")
    void getData(Callback<ApiResponse> response);

    @GET("/discover/movie?sort_by=vote_average.desc&api_key={API-KEY}")
    void getHighRated(Callback<ApiResponse> response);
`

