package com.classichu.classichu2.helper;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;

import com.classichu.classichu2.R;
import com.jaeger.library.StatusBarUtil;


/**
 * Created by louisgeek on 2017/2/20.
 * 状态栏
 */

public class StatusBarHelper {

    public static void setColor(Activity activity, boolean statusBarColorEnable,int statusBarColorResId) {
        setColor(activity,statusBarColorEnable,statusBarColorResId,0);
    }
    public static void setColor(Activity activity, boolean statusBarColorEnable,int statusBarColorResId,int statusBarAlpha) {
        if (statusBarColorEnable){
            if (statusBarColorResId == 0) { //0 默认
                statusBarColorResId = R.color.colorPrimary;
            }
            //
            int statusBarColor = ContextCompat.getColor(activity, statusBarColorResId);
            StatusBarUtil.setColor(activity,statusBarColor,statusBarAlpha);
        }
    }
    public static void setColorForSwipeBack(Activity activity, boolean statusBarColorEnable,int statusBarColorResId) {
        setColorForSwipeBack(activity,statusBarColorEnable,statusBarColorResId,0);
    }
    public static void setColorForSwipeBack(Activity activity, boolean statusBarColorEnable,int statusBarColorResId,int statusBarAlpha) {
        if (statusBarColorEnable){
            if (statusBarColorResId == 0) { //0 默认
                statusBarColorResId = R.color.colorPrimary;
            }
            //
            int statusBarColor = ContextCompat.getColor(activity, statusBarColorResId);
            StatusBarUtil.setColorForSwipeBack(activity,statusBarColor,statusBarAlpha);
        }
    }
    public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout,
                                               boolean statusBarColorEnable, int statusBarColorResId) {
    setColorForDrawerLayout(activity,drawerLayout,statusBarColorEnable,statusBarColorResId,0);
    }
        public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout,
                                               boolean statusBarColorEnable, int statusBarColorResId,int statusBarAlpha) {
        if (statusBarColorEnable){
            if (statusBarColorResId == 0) { //0 默认
                statusBarColorResId = R.color.colorPrimary;
            }
            //
            int statusBarColor = ContextCompat.getColor(activity, statusBarColorResId);
            StatusBarUtil.setColorForDrawerLayout(activity,drawerLayout,statusBarColor,statusBarAlpha);
        }
    }
   }
