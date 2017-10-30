package com.classichu.classichu2.base;

import android.os.Bundle;

/**
 * Created by louisgeek on 2017/2/22.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        mPresenter = setupPresenter();
    }

    protected abstract P setupPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }
}
