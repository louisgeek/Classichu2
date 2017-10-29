package com.classichu.classichu2.rxjava;

/**
 * Created by Classichu on 2017-6-5.
 *
 * 作为常规的 请求返回的 各个方法 用于预处理
 *
 * 让各种观察者的实现类  同时实现该接口
 *
 */

public interface RxHttpResult<T> {

    void onSuccess(T t);

    void onNetWorkError(int code, String msg);

    void onFailure(String msg);

    void onFinish();
}
