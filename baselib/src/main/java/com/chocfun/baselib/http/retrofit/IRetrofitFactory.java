package com.chocfun.baselib.http.retrofit;

import retrofit2.Retrofit;

/**
 * Retrofit 工厂类，提供创建 Retrofit 的相关内容
 */
public interface IRetrofitFactory {
    /**
     * 提供Retrofit实例
     */
    Retrofit provideRetrofit();
}
