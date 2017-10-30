package com.classichu.classichu2.rxjava;


import android.util.Log;

import com.classichu.classichu2.app.FinalData;
import com.classichu.classichu2.custom.CLog;
import com.classichu.classichu2.tool.NetWorkTool;
import com.classichu.classichu2.tool.ToastTool;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Classichu on 2017-6-5.
 *
 * 列举优先使用Observable情况：
 * 1.当上游在一段时间发送的数据量不大（以1000为界限,官方给出以1000个事件为分界线）的时候优先选择使用Observable；
   2.在处理GUI相关的事件，比如鼠标移动或触摸事件，这种情况下很少会出现backpressured的问题，用Observable就足以满足需求；
   3.获取数据操作是同步的，但你的平台不支持Java流或者相关特性。使用Observable的开销低于Flowable。

 */
public abstract class RxHttpResultObserver<T> implements RxHttpResult<T>, Observer<T> {
    //开始
    protected abstract void onStart(Disposable disposable);

    @Override
    public void onNetWorkError(int code, String msg) {
        //simple
        ToastTool.show(msg);
        CLog.e(msg);
        //
        onFinish();
    }

    //==================
    @Override
    public void onSubscribe(Disposable d) {
        if (!NetWorkTool.isNetWorkConnected()) {
            onNetWorkError(FinalData.NetWorkCodeMsg.UN_CONNECTED_CODE,
                    FinalData.NetWorkCodeMsg.UN_CONNECTED_MSG);
            //
            if (d != null && !d.isDisposed()) {
                d.dispose();
            }
            return;
        }
        //开始
        onStart(d);
    }

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException || //服务器异常
                e instanceof ConnectException || //数据连接异常
                e instanceof SocketTimeoutException ||  //网络超时
                e instanceof TimeoutException) {  //超时
            Log.e("zfq", "onError: "+e.getMessage());
            //
            onNetWorkError(FinalData.NetWorkCodeMsg.ERROR_CODE,
                    FinalData.NetWorkCodeMsg.ERROR_MSG);
        } else {
            onFailure(e.getMessage());
        }
        e.printStackTrace();
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
