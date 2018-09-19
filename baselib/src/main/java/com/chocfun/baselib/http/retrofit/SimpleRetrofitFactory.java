package com.chocfun.baselib.http.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class SimpleRetrofitFactory implements IRetrofitFactory {

    @Override
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        return builder.build();
    }

    @Override
    public Retrofit provideRetrofit() {
        return null;
    }
}
