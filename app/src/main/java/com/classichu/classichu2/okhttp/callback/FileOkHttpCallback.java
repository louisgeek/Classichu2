package com.classichu.classichu2.okhttp.callback;


import com.classichu.classichu2.okhttp.OkHttpSingleton;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by louisgeek on 2016/7/11.
 */
public abstract class FileOkHttpCallback implements okhttp3.Callback {

    private static final String TAG = "FileOkHttpCallback";
    @Override
    public void onFailure(Call call, final IOException e) {
        if (call.isCanceled()) {
            this.OnError("FileOkHttpCallback call is canceled,call:" + call.toString() + ";message:" + e.getMessage(), OkHttpSingleton.OKHTTP_CALL_CANCEL);
        } else {
            this.OnError(e.getMessage(), OkHttpSingleton.OKHTTP_CALL_ERROR);
        }
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody responseBody=response.body();
            //文件流数据
            InputStream inputStream = responseBody.byteStream();
            responseBody.close();
            //Log.d(TAG, "onResponse: inputStream:"+inputStream);
            this.OnSuccess(inputStream, response.code());
        } else {
            this.OnError(response.message(), response.code());
        }
    }

    public abstract void OnSuccess(InputStream inputStream, int statusCode);

    public abstract void OnError(String errorMsg, int statusCode);
}
