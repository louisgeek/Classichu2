package com.classichu.classichu2.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseActivity;


public class LoginActivity extends BaseActivity implements LoginContract.View {


    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        Toolbar toolbar = findById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
       // ActionBar actionBar = getSupportActionBar();
        setTitle("登录");


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_frame_layout, LoginFragment.newInstance("", ""))
                .commitAllowingStateLoss();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String msg) {

    }
}

