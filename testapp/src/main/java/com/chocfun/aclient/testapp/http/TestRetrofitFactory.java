package com.chocfun.aclient.testapp.http;

import com.chocfun.baselib.BuildConfig;
import com.chocfun.baselib.http.retrofit.IRetrofitFactory;
import com.chocfun.baselib.http.retrofit.interceptor.LogInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitFactory implements IRetrofitFactory {

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new TestIntercept());
        // 打印HTTP日志
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new LogInterceptor());
        }

        return builder.build();
    }

    @Override
    public Retrofit provideRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://fanyi.youdao.com/");
        builder.client(provideOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }
}
