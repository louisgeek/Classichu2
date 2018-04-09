package com.classichu.classichu2.tool;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by louisgeek on 2016/12/2.
 * V1.1
 * Update by louisgeek on 2018/4/9.
 */

public class FileTool {
    public static final long KB_IN_BYTE = 1024;//Byte byte
    public static final long MB_IN_BYTE = KB_IN_BYTE * 1024;
    public static final long GB_IN_BYTE = MB_IN_BYTE * 1024;
    public static final long TB_IN_BYTE = GB_IN_BYTE * 1024;
    public static final long PB_IN_BYTE = TB_IN_BYTE * 1024;

    public static String getFileContent(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        if (SdcardTool.hasSDCardMounted()) {
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fileInputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufReader = null;
                try {
                    fileInputStream = new FileInputStream(file);

                    // inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    inputStreamReader = new InputStreamReader(fileInputStream);
                    bufReader = new BufferedReader(inputStreamReader);

                    String line = "";
                    while ((line = bufReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileInputStream.close();
                        inputStreamReader.close();
                        bufReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String getFileContentWithRN(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        if (SdcardTool.hasSDCardMounted()) {
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fileInputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufReader = null;
                try {
                    fileInputStream = new FileInputStream(file);

                    // inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                    inputStreamReader = new InputStreamReader(fileInputStream);
                    bufReader = new BufferedReader(inputStreamReader);

                    String line = "";
                    while ((line = bufReader.readLine()) != null) {
                        stringBuilder.append(line.trim() + "\r\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileInputStream.close();
                        inputStreamReader.close();
                        bufReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 返回 文件大小  单位 Bytes
     *
     * @param path
     * @return
     */
    public static long getFileAndDirTotalSize(String path) {
        long size = 0;
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] childFiles = file.listFiles();
                for (int i = 0; i < childFiles.length; i++) {
                    size += childFiles[i].length();
                    getFileAndDirTotalSize(childFiles[i].getAbsolutePath());
                }
            }
            size += file.length();
        }
        return size;
    }

    public static String getFileAndDirTotalSizeFixed(String path) {
        long size = getFileAndDirTotalSize(path);
        DecimalFormat df = new DecimalFormat("#.00");
        String sizeStr;
        if (size < KB_IN_BYTE) {
            sizeStr = df.format((double) size) + "B";
        } else if (size < MB_IN_BYTE) {
            sizeStr = df.format((double) size / MB_IN_BYTE) + "K";
        } else if (size < GB_IN_BYTE) {
            sizeStr = df.format((double) size / GB_IN_BYTE) + "M";
        } else if (size < TB_IN_BYTE) {
            sizeStr = df.format((double) size / TB_IN_BYTE) + "G";
        } else {
            //} else if (size < PB_IN_BYTE) {
            sizeStr = df.format((double) size / PB_IN_BYTE) + "T";
        }
        return sizeStr;
    }

    public static void deleteFileAndDir(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] childFiles = file.listFiles();
                for (int i = 0; i < childFiles.length; i++) {
                    deleteFileAndDir(childFiles[i]);
                }
            }
            file.delete();
        }
    }

    public static void deleteFileAndDir(String path) {
        deleteFileAndDir(new File(path));
    }

    public static long getStorageTotalSpace(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        }
        return file.getTotalSpace();
    }

    public static long getStorageTotalSpace(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        return getStorageTotalSpace(file.getAbsolutePath());
    }

    public static long getStorageAvailableSpace(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        }
        // getUsableSpace 比  getFreeSpace 准确
        return file.getUsableSpace();
    }

    public static long getStorageAvailableSpace(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        return getStorageAvailableSpace(file.getAbsolutePath());
    }

    public static String getMBFormBytes(long size) {
        float mb = size * 1.0f / 1024 / 1024 / 1024;
        return String.format(Locale.CHINA, "%.2f", mb);
    }
}
