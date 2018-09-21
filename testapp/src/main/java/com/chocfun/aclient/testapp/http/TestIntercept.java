package com.chocfun.aclient.testapp.http;

import com.chocfun.baselib.log.LogHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TestIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        LogHelper.i("TestIntercept : intercept --------------------------");
        if (response.code() == 200) {
            throw new IllegalArgumentException("测试抛出异常");
        }
        return response;
    }
}
