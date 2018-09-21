package com.chocfun.aclient.testapp.http;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TestService {
    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Observable<Translation> getCall();

    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.13241&q=car")
    Observable<Translation> getCallWrong();
}
