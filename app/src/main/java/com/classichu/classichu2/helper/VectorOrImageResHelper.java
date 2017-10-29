package com.classichu.classichu2.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;

import com.classichu.classichu2.tool.BaseTool;


/**
 * Created by louisgeek on 2017/2/20.
 */
public class VectorOrImageResHelper {
    public static Drawable getDrawable(int imageOrVectorResId) {
        Context appContext = BaseTool.getAppContext();
        /**
         * 自动处理VectorDrawable  or  Image  否则5.0以下使用Vector会报错
         */
        Drawable drawable = AppCompatDrawableManager.get().getDrawable(appContext, imageOrVectorResId);
        return drawable;
    }
}
