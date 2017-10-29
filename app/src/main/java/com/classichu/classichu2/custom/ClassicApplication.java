package com.classichu.classichu2.custom;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by louisgeek on 2017/2/20.
 * 经典Application
 */

public class ClassicApplication extends Application {
    protected static boolean mDebug=true;
    private static ClassicApplication mInstance;
    private static Context mAppContext;
    private static final String TAG = "ClassicApplication";
    private Map<String,String> mHeadersMap=new HashMap<>();


    public Map<String, String> getHeadersMap() {
        if (!mHeadersMap.containsKey("JSESSIONID")){
        mHeadersMap.put("JSESSIONID","");
        }
        return mHeadersMap;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:App ClassicApplication");
        //
        mInstance = this;
        mAppContext = getApplicationContext();
        //BGASwipeBack
      //  ClassicBGASwipeBackHelper.initCallAtApplicationOnCreate(this);
    }

    public static boolean isDebug() {
        return mDebug;
    }

    public static void setDebug(boolean debug) {
        mDebug = debug;
    }

    public static ClassicApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    /**
     * 当终止应用程序对象时调用，不保证一定被调用，当程序是被内核终止以便为其他应用程序释放资源，那么将不会提醒，
     * 并且不调用应用程序的对象的onTerminate方法而直接终止进程
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        // 程序终止的时候执行
        //CLog.d("KooApplication onTerminate");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // 低内存的时候执行
        // CLog.d("KooApplication onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        // 程序在内存清理的时候执行
        // CLog.d("KooApplication onTrimMemory");
    }
}
