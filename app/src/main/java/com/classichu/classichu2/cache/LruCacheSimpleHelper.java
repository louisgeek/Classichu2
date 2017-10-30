package com.classichu.classichu2.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.classichu.classichu2.custom.CLog;
import com.classichu.classichu2.tool.HttpTool;
import com.classichu.classichu2.tool.ThreadTool;


/**
 * Created by louisgeek on 2016/11/9.
 */

public class LruCacheSimpleHelper {
    public static void loadImage(String imageUrl, final ImageView imageView) {
        LruCacheBitmapTool.initFirst(imageView.getContext());
        //
        final Bitmap bitmap = LruCacheBitmapTool.getBitmapCacheLevels_Enter(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            LruCacheBitmapTool.getBitmapFromUrlAndSaveCacheToDisk(imageUrl, new LruCacheBitmapTool.OnLoadImageCallBack() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    public static void loadString(Context context, final String keyRaw, final OnRetrunStringCallBack onRetrunStringCallBack) {
        LruCacheStringTool.initFirst(context);
        //
        final String value = LruCacheStringTool.getStingCacheLevels(keyRaw);
        if (value != null) {
            CLog.d("value:" + value);
            ThreadTool.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onRetrunStringCallBack.onReturnString(value);
                }
            });
        } else {
            HttpTool.getUrlBackString(keyRaw,null, new HttpTool.OnUrlBackStringCallBack() {
                @Override
                public void onSuccess(final String backStr) {
                    LruCacheStringTool.saveCacheToDisk(keyRaw, backStr);
                    ThreadTool.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onRetrunStringCallBack.onReturnString(backStr);
                        }
                    });
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
    }

    public interface OnRetrunStringCallBack {
        void onReturnString(String backStr);
    }
}
