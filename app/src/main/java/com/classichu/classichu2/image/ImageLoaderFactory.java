package com.classichu.classichu2.image;


import com.classichu.classichu2.image.impls.GlideImageLoader;
import com.classichu.classichu2.image.interfaces.IImageLoader;

/**
 * Created by louisgeek on 2016/12/28.
 */

public class ImageLoaderFactory {

    /**
     * 2016年12月28日13:52:01
     * 静态内部类实现单例模式方案的改写
     */
    private static class ImageLoaderFactoryInner4Glide {
        private static final GlideImageLoader INSTANCE = new GlideImageLoader();
    }

    /**
     * @return
     */
    public static IImageLoader getManager() {
        return ImageLoaderFactoryInner4Glide.INSTANCE;
        //  return ImageLoaderFactoryInner4Picasso.INSTANCE;
    }

}
