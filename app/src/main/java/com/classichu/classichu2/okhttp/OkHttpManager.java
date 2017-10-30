package com.classichu.classichu2.okhttp;


import android.util.Log;

import com.classichu.classichu2.custom.CLog;
import com.classichu.classichu2.custom.ClassicApplication;
import com.classichu.classichu2.okhttp.Interceptor.HttpCookiesInterceptor;
import com.classichu.classichu2.okhttp.Interceptor.HttpHeadersInterceptor;
import com.classichu.classichu2.tool.EmptyTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Classichu on 2017-6-5.
 */

public class OkHttpManager {

    /**
     * 双重校验锁 单例
     */
    private volatile static OkHttpManager instance;
    private OkHttpClient okHttpClient;
    private OkHttpManager() {
      buildOkHttpClient();
    }
    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private void buildOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                CLog.i(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        ClassicApplication classicApplication=ClassicApplication.getInstance();
        HttpHeadersInterceptor httpHeadersInterceptor=new HttpHeadersInterceptor(classicApplication.getHeadersMap());
        HttpCookiesInterceptor httpCookiesInterceptor=new HttpCookiesInterceptor();

        //OkHttp的是否单例影响着CookieJar的工作
        CookieJar myCookieJar=new CookieJar() {
            //private final
            Map<String, List<Cookie>> cookiesMap= new HashMap<>();
            {
                Log.i("zfq", "OkHttpManager instance initializer: ");
            }
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                Log.i("zfq", "OkHttpManager saveFromResponse "+url);
                cookiesMap.put(url.host(), cookies);
                if (EmptyTool.isNotEmpty(cookies)) {
                    for (Cookie cookie : cookies) {
                        Log.i("zfq", "OkHttpManager saveFromResponse: " + cookie.value());
                    }
                }
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                Log.i("zfq", "OkHttpManager loadForRequest "+url);
                List<Cookie> cookies = cookiesMap.get(url.host());
                if (EmptyTool.isNotEmpty(cookies)) {
                    for (Cookie cookie : cookies) {
                        Log.i("zfq", "OkHttpManager saveFromResponse: " + cookie.value());
                    }
                 }
               return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
        //httrh requestInterceptor=new RequestInterceptor();
        //配置okhttp
        okHttpClient= new OkHttpClient.Builder()
                .connectTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                //日志
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                //
                .addNetworkInterceptor(httpHeadersInterceptor)
                .addInterceptor(httpHeadersInterceptor)
                //
               // .addInterceptor(httpCookiesInterceptor)
                .cookieJar(myCookieJar)
                .build();
    }
}
