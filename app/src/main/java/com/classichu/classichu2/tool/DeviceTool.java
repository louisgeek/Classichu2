package com.classichu.classichu2.tool;

import android.util.Log;

/**
 * Created by Classichu on 2017-7-17.
 */

public class DeviceTool {

    /**
     * 获取设备的系统版本号
     */
    public static int getDeviceSDKVersion() {
        int sdk = android.os.Build.VERSION.SDK_INT;
        return sdk;
    }


    public static String getDevice() {
        String device = android.os.Build.DEVICE;
        Log.d("KKK", "getDevice device: " + device);
        return device;
    }

    public static String getProduct() {
        String product = android.os.Build.PRODUCT;
        Log.d("KKK", "getDevice product: " + product);
        return product;
    }

    /**
     * 获取设备的厂商 eg.Xiaomi
     */
    public static String getDeviceManufacturer() {
        String manufacturer = android.os.Build.MANUFACTURER;
        String board = android.os.Build.BOARD;
        Log.d("KKK", "getDeviceManufacturer manufacturer: " + manufacturer);
        Log.d("KKK", "getDeviceManufacturer board: " + board);
        return manufacturer;
    }

    /**
     * 获取设备的型号  eg.MI2SC
     */
    public static String getModel() {
        String model = android.os.Build.MODEL;
        return model;
    }




}
