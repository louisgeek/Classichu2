package com.classichu.classichu2.widget.floatball.utils;

import android.os.Build;


public class DevUtil {


    public static boolean isOnePlus() {
        return getManufacturer().contains("oneplus");
    }

    public static String getManufacturer() {
        String manufacturer = Build.MANUFACTURER;
        manufacturer = manufacturer.toLowerCase();
        return manufacturer;
    }
}
