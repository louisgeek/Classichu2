package com.classichu.classichu2.image.impls;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.classichu.classichu2.helper.GlideHelper;
import com.classichu.classichu2.image.interfaces.IImageLoader;
import com.classichu.classichu2.image.interfaces.ILoadImageCallback;


/**
 * Created by louisgeek on 2016/12/28.
 */

public class GlideImageLoader implements IImageLoader {
    @Override
    public void displayImage(ImageView imageView, String url, int width, int height) {
        GlideHelper.displayImage(imageView, url, width, height);
    }

    @Override
    public void displayImage(ImageView imageView, String url) {
        GlideHelper.displayImage(imageView, url);
    }

    @Override
    public void displayImageHasNoPlaceholder(ImageView imageView, String url) {
        GlideHelper.displayImageHasNoPlaceholder(imageView, url);
    }

    @Override
    public void displayImage(ImageView imageView, int imageResId) {
        GlideHelper.displayImageRes(imageView, imageResId);
    }

    @Override
    public void loadImage(String url, final ILoadImageCallback loadImageCallback) {
        GlideHelper.loadImage(url, new GlideHelper.OnLoadImageBackListener() {
            @Override
            public void onLoadImageBack(Bitmap bitmap) {
                loadImageCallback.onLoadImageBack(bitmap);
            }
        });
    }
}
