package com.chocfun.baselib.http.retrofit.interceptor;

import com.chocfun.baselib.log.LogHelper;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        LogHelper.i(String.format("Sending request %s %s",
                request.url(), request.method()));

        if("POST".equals(request.method()) || "PATCH".equals(request.method())){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                LogHelper.i("RequestParams:{"+sb.toString()+"}");
            } else {
                LogHelper.i(request.body().toString());
            }
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        LogHelper.i(String.format("Received response for %s in %.1fms%n%s%n%s",
                response.request().url(), (t2 - t1) / 1e6d,
                "" + response.code() + " " +  response.message(),
                response.headers()));

        return response;
    }
}
