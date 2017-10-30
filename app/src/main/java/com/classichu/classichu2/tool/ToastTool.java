package com.classichu.classichu2.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.classichu.classichu2.R;
import com.classichu.classichu2.helper.VectorOrImageResHelper;


/**
 * Created by louisgeek on 2016/12/15.
 */

public class ToastTool {


    private static Toast mToast;
    private static Toast mImageToast;

    /**
     * @param message
     * @param duration
     * @return
     */
    private static Toast initToast(CharSequence message, int duration, int gravity) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseTool.getAppContext(), message, duration);
        } else {
            mToast.setText(message);
            mToast.setDuration(duration);
        }
        if (gravity != 0) {
            mToast.setGravity(gravity, 0, 0);
        }
        return mToast;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void show(String message) {
        initToast(message, Toast.LENGTH_SHORT, Gravity.NO_GRAVITY).show();
    }

    public static void show(String message, int duration) {
        initToast(message, duration, Gravity.NO_GRAVITY).show();
    }

    public static void show(int resId) {
        initToast(BaseTool.getAppContext().getText(resId), Toast.LENGTH_SHORT, Gravity.NO_GRAVITY).show();
    }

    public static void show(int resId, int duration) {
        initToast(BaseTool.getAppContext().getText(resId), duration, Gravity.NO_GRAVITY).show();
    }


    public static void showCenter(String message) {
        initToast(message, Toast.LENGTH_SHORT, Gravity.CENTER).show();
    }

    public static void showCenter(String message, int duration) {
        initToast(message, duration, Gravity.CENTER).show();
    }

    public static void showCenter(int resId) {
        initToast(BaseTool.getAppContext().getText(resId), Toast.LENGTH_SHORT, Gravity.CENTER).show();
    }

    public static void showCenter(int resId, int duration) {
        initToast(BaseTool.getAppContext().getText(resId), duration, Gravity.CENTER).show();
    }

    /**
     * 显示有image的toast
     *
     * @param message
     * @param drawable
     * @return
     */
    private static Toast initImageToast(final String message, final Drawable drawable) {
        Context appContext = BaseTool.getAppContext();
        if (mImageToast == null) {
            mImageToast = new Toast(appContext);
            mImageToast.setDuration(Toast.LENGTH_SHORT);
            mImageToast.setGravity(Gravity.CENTER, 0, 0);
            //
            View view = LayoutInflater.from(appContext).inflate(R.layout.layout_toast_image, null);
            TextView tv = (TextView) view.findViewById(R.id.id_tv_toast);
            tv.setText(TextUtils.isEmpty(message) ? "" : message);
            ImageView iv = (ImageView) view.findViewById(R.id.id_iv_toast);
            if (drawable != null) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageDrawable(drawable);
            } else {
                iv.setVisibility(View.GONE);
            }
            //
            mImageToast.setView(view);
        } else {
            View view = mImageToast.getView();
            //
            TextView tv = (TextView) view.findViewById(R.id.id_tv_toast);
            tv.setText(TextUtils.isEmpty(message) ? "" : message);
            ImageView iv = (ImageView) view.findViewById(R.id.id_iv_toast);
            if (drawable != null) {
                iv.setVisibility(View.VISIBLE);
                iv.setImageDrawable(drawable);
            } else {
                iv.setVisibility(View.GONE);
            }
        }
        return mImageToast;

    }

    /**
     * @param message
     * @param imageResId
     * @return
     */
    public static void showImage(final String message, int imageResId) {
        Context appContext = BaseTool.getAppContext();
        Drawable drawable = VectorOrImageResHelper.getDrawable(imageResId);
        initImageToast(message, drawable).show();
    }

    public static void showImageOk(final String message) {
        showImage(message, R.drawable.ic_check_circle_black_24dp);
    }

    public static void showImageInfo(final String message) {
        showImage(message, R.drawable.ic_info_black_24dp);
    }

    public static void showImageWarn(final String message) {
        showImage(message, R.drawable.ic_error_black_24dp);
    }
}