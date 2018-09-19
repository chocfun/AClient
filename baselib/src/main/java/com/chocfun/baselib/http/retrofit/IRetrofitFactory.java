package com.chocfun.baselib.http.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Retrofit 工厂类，提供创建 Retrofit 的相关内容
 */
public interface IRetrofitFactory {
    OkHttpClient provideOkHttpClient();
    Retrofit provideRetrofit();
}
