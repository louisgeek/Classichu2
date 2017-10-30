package com.classichu.classichu2.okhttp.Interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by louisgeek on 2017/6/5.
 */

public class HttpHeadersInterceptor implements Interceptor {
    public HttpHeadersInterceptor(Map<String, String> headMap) {
        this.mHeadMap = headMap;
    }
    private Map<String,String> mHeadMap;
    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求
        Request request = chain.request();
        Request.Builder newBuilder= request.newBuilder();
        //newBuilder.addHeader("Accept", "Application/JSON");
        for (String key:mHeadMap.keySet()) {
            String value=mHeadMap.get(key);
            newBuilder.addHeader(key,value);
            Log.i("zfq", "HttpHeadersInterceptor intercept: "+key+"="+value);
        }
        Request newRequest=newBuilder.build();
        //响应
        Response response = chain.proceed(newRequest);
        return response;
    }
}
