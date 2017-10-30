package com.classichu.classichu2.okhttp.Interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by louisgeek on 2017/6/5.
 */

public class HttpCookiesInterceptor implements Interceptor {
    private List<String> mCookies=new ArrayList<>();
    {
        Log.i("zfq", "HttpCookiesInterceptor instance initializer: ");
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        //请求  设置Cookie
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        //newBuilder.addHeader("Accept", "Application/JSON");
        if (mCookies != null) {
            for (String cookie : mCookies) {//1条
                newBuilder.addHeader("Cookie", cookie);
                Log.i("zfq", "HttpCookiesInterceptor intercept: Cookie:"+cookie);
            }
        }
        Request newRequest = newBuilder.build();

        //响应
        Response response = chain.proceed(newRequest);
        if (response.headers("Set-Cookie") != null){
            for (String header : response.headers("Set-Cookie")) {//1条
                mCookies.add(header);
                Log.i("zfq", "HttpCookiesInterceptor intercept: Set-Cookie:"+header);
            }
        }
        return response;
    }
}
