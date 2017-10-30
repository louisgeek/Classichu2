package com.classichu.classichu2.api;

import android.util.Log;

import com.classichu.classichu2.retrofit.RetrofitManager;
import com.classichu.classichu2.tool.SharedPreferencesTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Classichu on 2017/5/22.
 */

public class ApiServiceFactory{

    /**
     * 双重校验锁 单例
     */
    private volatile static ApiServiceFactory instance;

    private ApiServiceFactory() {
    }

    public static ApiServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ApiServiceFactory.class) {
                if (instance == null) {
                    instance = new ApiServiceFactory();
                }
            }
        }
        return instance;
    }

    /**
     *统一管理
     */
    private Map<String, Object> serviceMap = new HashMap<>();
    public <T> T createApi(String url,Class<T> tClass) {
        Log.i("zfq", "createApi: url:"+url);
        Log.i("zfq", "createApi: tClass:"+tClass.getName());
        Object service = serviceMap.get(url+tClass.getName());
    //  if (service == null) {
            service = RetrofitManager.getInstance().getRetrofit(url).create(tClass);
            serviceMap.put(url+tClass.getName(), service);
       // }
        return (T) service;
    }



    private String setupBaseUrl() {
        String java_ip= (String) SharedPreferencesTool.get("java_ip","");
        String java_port= (String) SharedPreferencesTool.get("java_port","");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("http://");
        stringBuffer.append(java_ip);
        stringBuffer.append(":");
        stringBuffer.append(java_port);
        stringBuffer.append("/NIS/");
        String baseUrl=stringBuffer.toString();
        return  baseUrl;
    }

    public UserApi  getUserApi(){
        return createApi(setupBaseUrl(),UserApi.class);
    }


    public PatientApi  getPatientApi(){
        return createApi(setupBaseUrl(),PatientApi.class);
    }
    /**
     * test
     */
/*    private TestApi testApi;
    public TestApi getTestApi() {
        if (testApi == null) {
            Retrofit retrofit = RetrofitManager.getInstance().getRetrofit("https://git.oschina.net/louisgeek/Demo/raw/master/api/");
            testApi = retrofit.create(TestApi.class);
        }
        return testApi;
    }*/

}
