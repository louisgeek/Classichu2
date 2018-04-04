package com.classichu.classichu2.helper;

import android.graphics.Bitmap;


import com.classichu.classichu2.tool.CacheTool;
import com.classichu.classichu2.tool.CacheToolOld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by louisgeek on 2016/12/30.
 */

public class CacheHelper {
    public static <T> T getData(String key) {
        return (T) CacheTool.getInstance().getObject(key);
    }

    public static void putData(String key, Serializable objectSerializable) {
        CacheTool.getInstance().putSerializable(key, objectSerializable);
    }

    @Deprecated
    public static <T> void putDataList(String key, List<T> tList) {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(tList);
        putData(key, arrayList);
    }

    public static String getString(String key) {
        return CacheTool.getInstance().getString(key);
    }

    public static void putString(String key, String value) {
        CacheTool.getInstance().putString(key, value);
    }

   /* public static Bitmap getBitmap(String key) {
        //return ACache.get(BaseTool.getAppContext()).getAsBitmap(key);
        return CacheTool.getInstance().getBitmap(key);
    }*/

   /* public static void putBitmap(String key, Bitmap bitmap) {
       // ACache.get(BaseTool.getAppContext()).put(key, bitmap);
        CacheTool.getInstance().put(key, bitmap);
    }*/

  /*  public static boolean remove(String key) {
       // return ACache.get(BaseTool.getAppContext()).remove(key);
        return CacheTool.getInstance().remove(key);
    }*/

  /*  public static File getCacheFile(String key) {
        return ACache.get(BaseTool.getAppContext()).file(key);
        // return CacheToolOld.getInstance().get(key);
    }*/

  /*  public static void clearAllCache() {
        // ACache.get(BaseTool.getAppContext()).clear();
        CacheTool.getInstance().clear();
    }*/
}
