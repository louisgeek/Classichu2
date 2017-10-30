package com.classichu.classichu2.rxjava;


import com.classichu.classichu2.app.FinalData;
import com.classichu.classichu2.custom.CLog;
import com.classichu.classichu2.tool.NetWorkTool;
import com.classichu.classichu2.tool.ToastTool;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * Created by Classichu on 2017-6-5.
 *
 * 列举优先使用Flowable情况：
 *1.当上游在一段时间发送的数据量过大的时候（这个量我们往往无法预计），此时就要使用Flowable以限制它所产生的量的元素10K +处理。
 2.当你从本地磁盘某个文件或者数据库读取数据时（这个数据量往往也很大），应当使用Flowable，这样下游可以根据需求自己控制一次读取多少数据；
 3.以读取数据为主且有阻塞线程的可能时用Flowable，下游可以根据某种条件自己主动读取数据。
 *
 */
public abstract class RxHttpResultSubscriber<T> implements RxHttpResult<T>, Subscriber<T> {
    //开始
    protected abstract void onStart(Subscription subscription);

    @Override
    public void onNetWorkError(int code, String msg) {
        //simple
        ToastTool.show(msg);
        CLog.e(msg);
        //
        onFinish();
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (!NetWorkTool.isNetWorkConnected()) {
            onNetWorkError(FinalData.NetWorkCodeMsg.UN_CONNECTED_CODE,
                    FinalData.NetWorkCodeMsg.UN_CONNECTED_MSG);
            //
            if (s != null) {
                s.cancel();
            }
            return;
        }
        //需要调用request去请求资源，否则onNext和onComplete方法将不会被调用
        s.request(Long.MAX_VALUE);
        //开始
        onStart(s);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException || //服务器异常
                e instanceof ConnectException || //网络异常
                e instanceof SocketTimeoutException ||  //网络超时
                e instanceof TimeoutException) {  //超时
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
