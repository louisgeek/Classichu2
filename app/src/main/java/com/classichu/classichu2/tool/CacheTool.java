package com.classichu.classichu2.tool;

import android.app.Application;
import android.os.Parcelable;
import android.os.Process;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;


import com.classichu.classichu2.custom.ClassicApplication;
import com.classichu.classichu2.tool.DateTool;
import com.classichu.classichu2.tool.EmptyTool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * Created by classichu on 2018/3/22.
 */


public class CacheTool {
    private static final String TAG = "CacheTool";

    private static final String DEFAULT_CACHE_NAME = "cacheName";

    public static final long SECOND_IN_MILLIS = 1000;
    public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
    public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
    public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
    public static final long WEEK_IN_MILLIS = DAY_IN_MILLIS * 7;

    //时间字符串长度
    private static final int OVER_DUE_TIME_STR_LEN = 19;

    private static final SimpleArrayMap<String, CacheTool> sSimpleArrayMapCacheInstance = new SimpleArrayMap<>();

    private CacheTool() {
        //AppApplication appApplication
    }

    private static Application getApp() {
        return ClassicApplication.getInstance();
    }

    public CacheTool(long maxSize, int maxCount) {
    }

    private static File mCacheDir;

    private static CacheTool getInstance(File cacheDir, long maxSize, int maxCount) {
        String filePath = cacheDir.getAbsolutePath() + "_" + Process.myPid();
        CacheTool cacheTool = sSimpleArrayMapCacheInstance.get(filePath);
        if (cacheTool == null) {
            cacheTool = new CacheTool(maxSize, maxCount);
            sSimpleArrayMapCacheInstance.put(filePath, cacheTool);
        }
        File cachePath = new File(filePath);
        if (!cachePath.exists()) {
            cachePath.mkdirs();
        }
        mCacheDir = cachePath;
        return cacheTool;
    }

    public static CacheTool getInstance(String cacheName, long maxSize, int maxCount) {
        if (EmptyTool.isBlank(cacheName)) {
            cacheName = DEFAULT_CACHE_NAME;
        }
        String filePath = getApp().getCacheDir().getAbsolutePath();
        File file = new File(filePath, cacheName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return getInstance(file, maxSize, maxCount);
    }

    public static CacheTool getInstance() {
        return getInstance("", Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) {
            return defaultValue;
        }
        return new String(bytes);
    }

    public byte[] getBytes(String key) {
        return getBytes(key, null);
    }

    public byte[] getBytes(String key, byte[] defaultValue) {
        File file = new File(mCacheDir, String.valueOf(key.hashCode()));
        try {
            if (file.exists()) {
                //
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                //
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] temp = new byte[1024];
                int length;
                while ((length = fileInputStream.read(temp)) != -1) {
                    byteArrayOutputStream.write(temp, 0, length);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                Log.e(TAG, "getBytes: " + new String(bytes));
//                Log.e(TAG, "getBytes: "+new String(bytes,"utf-8"));
                if (bytes.length > OVER_DUE_TIME_STR_LEN) {
                    // yyyy-MM-dd$HH:mm:ss
                    if (bytes[4] == '-'
                            || bytes[7] == '-'
                            || bytes[10] == '$'
                            || bytes[13] == ':'
                            || bytes[16] == ':'
                            ) {
                        int newLength = bytes.length - OVER_DUE_TIME_STR_LEN;
                        byte[] overDueTimeStrBytes = new byte[OVER_DUE_TIME_STR_LEN];
                        byte[] realSaveDataBytes = new byte[newLength];
                        System.arraycopy(bytes, 0, overDueTimeStrBytes, 0, OVER_DUE_TIME_STR_LEN);
                        System.arraycopy(bytes, OVER_DUE_TIME_STR_LEN, realSaveDataBytes, 0, newLength);
                        String overDueTimeStr = new String(overDueTimeStrBytes);
                        overDueTimeStr = overDueTimeStr.replace("$", " ");
                        long diff = DateTool.timeCompare(overDueTimeStr);
                        if (diff > 0) {
                            //已过期，删除文件
                            remove(key);
                            return defaultValue;
                        }
                        return realSaveDataBytes;
                    }
                }
                return bytes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public Object getObject(String key, Object defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) {
            return defaultValue;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            //
            byteArrayInputStream.close();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public Object getObject(String key) {
        return getObject(key, null);
    }

    public void putBytes(String key, byte[] dataBytes, long timeInMillis) {
        byte[] realSaveDataBytes;
        if (timeInMillis > 0) {
            String overDueTimeStr = DateTool.getNextTimeStr(timeInMillis);
            //yyyy-MM-dd HH:mm:ss —> yyyy-MM-dd$HH:mm:ss
            overDueTimeStr = overDueTimeStr.replace(" ", "$");
            byte[] overDueTimeBytes = overDueTimeStr.getBytes();
            realSaveDataBytes = new byte[overDueTimeBytes.length + dataBytes.length];
            //  [x,y] [1,2,3,4,5,6,7,8] —> [x,y,1,2,3,4,5,6,7,8]
            System.arraycopy(overDueTimeBytes, 0, realSaveDataBytes, 0, overDueTimeBytes.length);
            System.arraycopy(dataBytes, 0, realSaveDataBytes, overDueTimeBytes.length, dataBytes.length);
        } else {
            realSaveDataBytes = dataBytes;
        }
        File file = new File(mCacheDir, String.valueOf(key.hashCode()));
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(realSaveDataBytes);
            //
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putBytes(String key, byte[] dataBytes) {
        putBytes(key, dataBytes, -1);
    }

    public void putString(String key, String string) {
        putString(key, string, -1);
    }

    public void putString(String key, String string, long timeInMillis) {
        putBytes(key, string.getBytes(), timeInMillis);
    }

    public void putSerializable(String key, Serializable serializable, long timeInMillis) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            //
            putBytes(key, bytes, timeInMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putSerializable(String key, Serializable serializable) {
        putSerializable(key, serializable, -1);
    }


    public boolean remove(String key) {
        if (mCacheDir != null) {
            File file = new File(mCacheDir, String.valueOf(key.hashCode()));
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    public void clear() {
        if (mCacheDir == null) {
            return;
        }
        if (mCacheDir.exists()) {
            FileTool.deleteFileAndDir(mCacheDir);
        }
    }


}
