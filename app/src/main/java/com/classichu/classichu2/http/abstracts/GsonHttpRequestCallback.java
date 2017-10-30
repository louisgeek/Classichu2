package com.classichu.classichu2.http.abstracts;


import com.classichu.classichu2.http.interfaces.IHttpRequestCallback;
import com.classichu.classichu2.tool.ThreadTool;

/**
 * Created by louisgeek on 2016/12/28.
 */

public abstract class GsonHttpRequestCallback<T> implements IHttpRequestCallback {

    @Override
    public void onSuccess(String result) {
        final T t = this.OnSuccess(result);
        ThreadTool.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                OnSuccessOnUI(t);
            }
        });
    }

    @Override
    public void onFailure(String errorMsg) {
        this.OnError(errorMsg);
    }

    public abstract T OnSuccess(String result);

    public abstract void OnSuccessOnUI(T baseBean);

    public abstract void OnError(String errorMsg);
}
