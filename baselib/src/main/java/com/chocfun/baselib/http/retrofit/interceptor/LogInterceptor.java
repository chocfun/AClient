package com.chocfun.baselib.http.retrofit.interceptor;

import com.chocfun.baselib.log.LogHelper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 日志打印拦截器
 */
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        LogHelper.i(String.format("HTTP Request:\n%s %s\n",
                request.url(),
                request.method()));

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
        // 为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
        BufferedSource responseSource = response.body().source();
        responseSource.request(Long.MAX_VALUE);
        Buffer responseBuffer = responseSource.buffer();

        long t2 = System.nanoTime();
        LogHelper.i(String.format(Locale.getDefault(),
                "HTTP Response:\n%s in %.1fms%n%s%n%s\n%s\n\n",
                response.request().url(), // 访问链接
                (t2 - t1) / 1e6d, // 耗时
                "" + response.code() + " " +  response.message(), // HTTP Code Message
                response.headers(), // http header
                responseBuffer.clone().readString(Charset.forName("UTF-8")) // http response body
                ));

        return response;
    }
}
