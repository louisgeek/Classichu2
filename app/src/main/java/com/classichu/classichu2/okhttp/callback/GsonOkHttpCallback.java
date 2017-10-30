package com.classichu.classichu2.okhttp.callback;

import android.util.Log;

import com.classichu.classichu2.okhttp.OkHttpSingleton;
import com.classichu.classichu2.tool.ThreadTool;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by louisgeek on 2016/7/11.
 */
public abstract class GsonOkHttpCallback<T> implements okhttp3.Callback {

    private static final String TAG = "GsonOkHttpCallback";


    @Override
    public void onFailure(Call call, final IOException e) {
        if (call.isCanceled()) {
            this.OnError("GsonOkHttpCallback call is canceled,call:" + call.toString() + ";message:" + e.getMessage(),  OkHttpSingleton.OKHTTP_CALL_CANCEL);
        } else {
            this.OnError(e.getMessage(),  OkHttpSingleton.OKHTTP_CALL_ERROR);
        }
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {

        if (response.isSuccessful()) {
            ResponseBody responseBody=response.body();
            String response_body_result = responseBody.string();
            responseBody.close();
            Log.d(TAG, "onResponse upxxx isSuccessful: response_body_result:" + response_body_result);

            final T t = this.OnSuccess(response_body_result, response.code());

            if (t != null) {
                ThreadTool.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        OnSuccessOnUI(t,response.code());
                    }
                });
            } else {
                Log.e(TAG, "onResponse upxxx  isSuccessful:but  t is null");
                this.OnError(response.message(), response.code());
            }

        } else {
           Log.e(TAG, "onResponse upxxx Not isSuccessful:"+response.code() );
            this.OnError(response.message(), response.code());
        }
    }

    public abstract T OnSuccess(String result, int statusCode);

    public abstract void OnSuccessOnUI(T baseBean, int statusCode);

    public abstract void OnError(String errorMsg, int statusCode);


}
