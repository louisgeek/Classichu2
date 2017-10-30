package com.classichu.classichu2.image;


import com.classichu.classichu2.image.impls.GlideImageLoader;
import com.classichu.classichu2.image.interfaces.IImageLoader;

/**
 * Created by louisgeek on 2016/12/28.
 */

public class ImageLoaderFactoryNew {

    public static <T extends IImageLoader> T create(Class<T> tClass) {
        IImageLoader base = null;
        try {
            base = (IImageLoader) Class.forName(tClass.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) base;
    }

    /**
     * test
     * @return
     */
    public static IImageLoader getTest() {
        IImageLoader iImageLoader = ImageLoaderFactoryNew.create(GlideImageLoader.class);
        return iImageLoader;
    }

}
