package com.chocfun.aclient.httpapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("v2/book/{id}")
    Call<ResponseBody> getBook(@Path("id") int id);

    @GET("v2/movie/new_movies")
    Call<ResponseBody> new_movies();
}
