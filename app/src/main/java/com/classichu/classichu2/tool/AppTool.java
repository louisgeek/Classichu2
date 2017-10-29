package com.classichu.classichu2.tool;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.List;

/**
 * Created by louisgeek on 2016/9/29.
 */
public class AppTool {
    /**
     * 获取应用程序的IMEI号
     */
    public static String getIMEI() {
        Context appContext=BaseTool.getAppContext();
        TelephonyManager telecomManager = (TelephonyManager) appContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telecomManager.getDeviceId();
        return imei;
    }


    /**
     * 获取自己应用程序的名称
     */
    public static String getAppName() {
        Context appContext=BaseTool.getAppContext();
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;

        try {
            packageManager = appContext.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(appContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException var4) {
            applicationInfo = null;
        }

        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获取自己应用程序的图标
     */
    public static Bitmap getIconBitmap() {
        Context appContext=BaseTool.getAppContext();
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = appContext.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(appContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        try {
            Context appContext=BaseTool.getAppContext();
            PackageManager manager = appContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(appContext.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取版本名
     *
     * @return 当前应用的版本名
     */
    public static String getVersionName() {
        try {
            Context appContext=BaseTool.getAppContext();
            PackageManager manager = appContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(appContext.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本读取错误";
        }
    }

    /**
     * 获取应用程序包名
     */
    public static String getPackageName() {
        Context appContext=BaseTool.getAppContext();
        String pkgName = appContext.getPackageName();
        return pkgName;
    }

    /**
     * 判断app是否在前台还是在后台运行
     * @return
     */
    public static boolean isAppRunBackground() {
        Context appContext=BaseTool.getAppContext();
        ActivityManager activityManager = (ActivityManager) appContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(appContext.getPackageName())) {
                /*
				BACKGROUND=400 EMPTY=500 FOREGROUND=100
				GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				 */
                Log.i(appContext.getPackageName(), "louisz==此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + appContext.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(appContext.getPackageName(), "louisz==处于后台"
                            + appProcess.processName);
                    return true;
                } else {
                    Log.i(appContext.getPackageName(), "louisz==处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public static void installApk( String filePath) {
        Context appContext=BaseTool.getAppContext();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
    }

    public static void uninstallApk() {
        Context appContext=BaseTool.getAppContext();
        Uri uri = Uri.parse("package:com.xxx.xxx");
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
    }
}
