package com.classichu.classichu2.tool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Classichu on 2017/5/24.
 */

public class ReflectTool {
    /**
     * bean2Map  常用
     *
     * @param beanObj
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> bean2Map(Object beanObj) throws Exception {
        if (beanObj == null) {
            return null;
        }
        Map<K, V> map = new HashMap<>();

        Field[] fields = beanObj.getClass().getDeclaredFields(); //获取所有的属性
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);//打破封装
            map.put((K) field.getName(), (V) field.get(beanObj));
            // Log.d(TAG, "bean2Map: "+field.getName()+"="+field.get(beanObj));
            stringBuilder.append(field.getName());
            stringBuilder.append("=");
            stringBuilder.append(field.get(beanObj));
            stringBuilder.append("&");

        }
        // Log.d(TAG, "bean2Map: all:"+stringBuilder.toString());
        return map;
    }

    /**
     * 反射获得对象的值
     *
     * @param
     * @param fieldName
     * @return
     */
    public static <T, E> T getFieldValue(E eObj, String fieldName) {
        try {
            Field field = eObj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(eObj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> void setFieldValue(E eObj, String fieldName, Object value) {
        try {
            Field field = eObj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(eObj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    /**
     * 反射获得对象的值
     * 常用于  子类继承父类调用
     * @param
     * @param fieldName
     * @return
     */
    public static <T> T getFieldValueFromSuperClass(Class tClass, Object obj, String fieldName) {
        try {
            Field field = tClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValueForSuperClass(Class tClass, Object obj, String fieldName, Object value) {
        try {
            Field field = tClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
