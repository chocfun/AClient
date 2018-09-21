package com.chocfun.baselib.http.retrofit;

import java.util.HashMap;

import retrofit2.Retrofit;

/**
 * Retrofit实例管理，单例模式
 */
public class RetrofitUtil {
    private HashMap<String, Retrofit> mRetrofits = new HashMap<>();

    private RetrofitUtil() {

    }

    private static class SingletonHolder {
        private static RetrofitUtil instance = new RetrofitUtil();
    }

    public static RetrofitUtil getInstance() {
        return SingletonHolder.instance;
    }

    public Retrofit add(String key, IRetrofitFactory factory) {
        if (! mRetrofits.containsKey(key)) {
            mRetrofits.put(key, factory.provideRetrofit());
        } else {
            throw new IllegalArgumentException(key + " 已经存在");
        }

        return mRetrofits.get(key);
    }

    public Retrofit get(String key) {
        if (!mRetrofits.containsKey(key) || (null == mRetrofits.get(key))) {
            throw new IllegalArgumentException(key + " 不存在");
        }
        return mRetrofits.get(key);
    }
}
