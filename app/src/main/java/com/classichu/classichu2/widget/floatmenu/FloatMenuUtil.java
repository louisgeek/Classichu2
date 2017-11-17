package com.classichu.classichu2.widget.floatmenu;

import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;


public class FloatMenuUtil {

    public static WindowManager.LayoutParams getMyLayoutParams() {
        return getMyLayoutParams(false);
    }

    public static WindowManager.LayoutParams getMyLayoutParams(boolean backKeyCanHide) {
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        final int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt < Build.VERSION_CODES.KITKAT) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else if (sdkInt < Build.VERSION_CODES.N_MR1) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        }//fixme // // // //
        /* else if (sdkInt < Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else {//8.0以后
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }*/
        if (backKeyCanHide) {
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        } else {
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        }

        mLayoutParams.format = PixelFormat.RGBA_8888;
        // 悬浮窗默认显示以左上角为起始坐标
        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return mLayoutParams;
    }
}
