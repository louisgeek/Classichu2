package com.classichu.classichu2.helper;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by louisgeek on 2017/3/14.
 */

public class WindowBgHelper {
    // 设置背景颜色变暗
    public static void bgShow(Context context) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.9f; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }
    // 设置背景颜色变亮
    public static void bgClear(Context context) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = 1.0f; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }

}
