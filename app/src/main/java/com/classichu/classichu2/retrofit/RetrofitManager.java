package com.classichu.classichu2.retrofit;


import com.classichu.classichu2.okhttp.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Classichu on 2017-6-5.
 */

public class RetrofitManager {

    /**
     * 双重校验锁 单例
     */
    private volatile static RetrofitManager instance;
    private RetrofitManager() {

    }
    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public  Retrofit getRetrofit(String baseUrl) {
        Retrofit  retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(OkHttpManager.getInstance().getOkHttpClient())
                    //string
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        return retrofit;
    }
}
