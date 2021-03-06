package com.chocfun.baselib.http.retrofit;

import android.support.annotation.NonNull;

import com.chocfun.baselib.BuildConfig;
import com.chocfun.baselib.http.retrofit.interceptor.LogInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SimpleRetrofitFactory implements IRetrofitFactory {

    private String mBaseUrl;

    public SimpleRetrofitFactory(@NonNull String baseUrl) {
        mBaseUrl = baseUrl;
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 打印HTTP日志
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LogInterceptor());
        }

        return builder.build();
    }

    @Override
    public Retrofit provideRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(mBaseUrl);
        builder.client(provideOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }
}
