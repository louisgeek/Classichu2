package com.classichu.classichu2.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Classichu on 2017/9/30.
 */

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    private CompositeDisposable mCompositeDisposable;
    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        mView = null;
        clearDisposable();
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    private void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
