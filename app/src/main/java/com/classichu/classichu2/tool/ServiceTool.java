package com.classichu.classichu2.tool;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by louisgeek on 2016/11/21.
 */

public class ServiceTool {
    /**
     * @param serviceClassName 包名+服务的类名（例如：com.xxxx.xxxxxx.TestService）
     * @return
     */
    public static boolean isServiceRunning(Context context,String serviceClassName) {
        Context appContext=BaseTool.getAppContext();
        final ActivityManager activityManager = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE); //这个value取任意大于1的值，但返回的列表大小可能比这个值小。

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)) {
                return true;
            }
        }
        return false;
    }
}
