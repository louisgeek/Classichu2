package com.classichu.classichu2.tool;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.classichu.classichu2.custom.CLog;

import java.lang.reflect.Field;

/**
 * Created by louisgeek on 2016/11/16.
 */

public class ScreenTool {
    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.widthPixels;

    }

    public static int getScreenWidth2() {
        Context appContext=BaseTool.getAppContext();
        WindowManager windowManager = (WindowManager) appContext
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static int getScreenHeight2(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getScreenHeight3() {
        Context appContext=BaseTool.getAppContext();
        WindowManager windowManager = (WindowManager) appContext
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * dpi为160时，density为1
     * density = (dpi*1.0)/ 160;
     *
     * @return
     */
    public static float getScreenDensity() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        CLog.d("displayMetrics.density:" + displayMetrics.density);
        CLog.d("displayMetrics.densityDpi:" + displayMetrics.densityDpi);
        CLog.d("displayMetrics.scaledDensity:" + displayMetrics.scaledDensity);
        CLog.d("displayMetrics.xdpi:" + displayMetrics.xdpi);
        CLog.d("displayMetrics.ydpi:" + displayMetrics.ydpi);
        return displayMetrics.density;
    }

    public static float getScreenDensity2() {
        Context appContext=BaseTool.getAppContext();
        WindowManager windowManager = (WindowManager) appContext
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.density;
    }

    /**
     * @return
     */
    public static int getStatusBarHeight() {
        Class<?> clazz;
        Object obj;
        Field field;
        int resourceId;
        int statusBarHeight = 0;
        try {
            clazz = Class.forName("com.android.internal.R$dimen");
            obj = clazz.newInstance();
            field = clazz.getField("status_bar_height");
            resourceId = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
            //Log.v("@@@@@@", "the status bar height is : " + statusBarHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    public static int getStatusBarHeight2() {
        int statusBarHeight = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
