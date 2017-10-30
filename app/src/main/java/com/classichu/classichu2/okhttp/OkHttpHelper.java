package com.classichu.classichu2.okhttp;


import com.classichu.classichu2.custom.CLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;

/**
 * Created by louisgeek on 2016/12/6.
 */

public class OkHttpHelper {

    private static final String TAG = "OkHttpHelper";
    private Map<String, String> mDefaultHeadersMap;
    private Map<String, String> mCustomHeadersMap;
    private StringBuffer mParamsStringBuffer;
    private Callback mResponseCallback;


  /*  public final String UserId = "";
    public final String UserPassword = UserId + "UZ" + "" + "UZ";*/

    public Map<String, String> setupDefaultHeaders() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());

        Map<String, String> map = new HashMap<>();
       /* map.put("UserId", UserId);
        map.put("UserPassword", MD5Tool.encode(UserPassword + time));
        map.put("CurrentTime", time);*/
        return map;
    }

    private OkHttpHelper(Builder builder) {
        if (mDefaultHeadersMap == null) {
            mDefaultHeadersMap = new HashMap<>();
        }

        /**
         *默认headers
         */
        mDefaultHeadersMap.put("userKey", "");
        mDefaultHeadersMap.put("userToken", "");
        //云种验证
        mDefaultHeadersMap.putAll(setupDefaultHeaders());
        /**
         *设置自定义headers
         */
        if (builder.customHeadersMap != null && builder.customHeadersMap.size() > 0) {
            if (mCustomHeadersMap == null) {
                mCustomHeadersMap = new HashMap<>();
            }
            mCustomHeadersMap.putAll(builder.customHeadersMap);
        }

        /**
         * 默认params
         */

        Map<String, String> defaultParamsMap = new HashMap<>();
      /*  defaultParamsMap.put("showapi_appid", ApiUrls.SHOWAPI_APPID);
        defaultParamsMap.put("showapi_sign", ApiUrls.SHOWAPI_SIGN_SIMPLE);*/
        mParamsStringBuffer = new StringBuffer();
        mParamsStringBuffer.append(ParamsTool.paramsMapToStr(defaultParamsMap));

        /**
         * 设置自定义params
         */
        if (builder.paramsStringBuffer != null && builder.paramsStringBuffer.length() > 0) {
            /**
             * 最后一个是&
             */
            if (mParamsStringBuffer.lastIndexOf("&") == mParamsStringBuffer.length() - 1) {
            /* do nothing */
            } else {
                mParamsStringBuffer.append("&");
            }
            mParamsStringBuffer.append(builder.paramsStringBuffer);
        }
        /**
         *
         */
        mResponseCallback = builder.responseCallback;
    }

    public void doGetUrl(String webUrl) {
        //Log.d(TAG, "doGetUrl: url:" + webUrl);
        CLog.d("url:" + webUrl);
        OkHttpSingleton.getInstance().doGet(webUrl, mDefaultHeadersMap, mCustomHeadersMap, mResponseCallback);
    }

    public void cancel() {
        //OkHttpSingleton.getInstance().();
    }

    public void doPostUrl(String webUrl) {
        //Log.d(TAG, "doPostUrl: url:" + webUrl);
        CLog.d("url:" + webUrl);
        // KLog.d("mParamsStringBuffer:"+mParamsStringBuffer.toString());
        OkHttpSingleton.getInstance().doPost(webUrl, mDefaultHeadersMap, mCustomHeadersMap, mParamsStringBuffer.toString(), mResponseCallback);
    }

    public static class Builder {
        private Map<String, String> customHeadersMap;
        private StringBuffer paramsStringBuffer;
        private Callback responseCallback;

        public Builder headers(String headersStr) {
            return headers(ParamsTool.paramsStrToMap(headersStr));
        }

        public Builder headers(Map<String, String> customHeadersMap) {
            this.customHeadersMap = customHeadersMap;
            return this;
        }

        public Builder params(String params) {
            if (params != null && params.trim().length() > 0) {
                paramsStringBuffer = new StringBuffer();
                paramsStringBuffer.append(params);
                CLog.d("params:" + paramsStringBuffer.toString());
            }
            return this;

        }

        public Builder params(Map<String, String> paramsMap) {
            if (paramsMap == null) {
                return this;
            }
            return params(ParamsTool.paramsMapToStr(paramsMap));
        }

        public Builder callback(Callback responseCallback) {
            this.responseCallback = responseCallback;
            return this;
        }


        public OkHttpHelper build() {
            return new OkHttpHelper(this);//返回带Builder的OkHttpHelper
        }
    }


}
