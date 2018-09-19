package com.chocfun.baselib.http.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class SimpleRetrofitFactory implements IRetrofitFactory {

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        return builder.build();
    }

    @Override
    public Retrofit provideRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("");
        builder.client(provideOkHttpClient());

        return builder.build();
    }
}
