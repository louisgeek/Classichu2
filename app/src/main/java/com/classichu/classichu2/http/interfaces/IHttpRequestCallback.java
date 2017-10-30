package com.classichu.classichu2.http.interfaces;

/**
 * Created by louisgeek on 2016/12/28.
 */

public interface IHttpRequestCallback {
    void onSuccess(String result);
    void onFailure(String errorMsg);
}
