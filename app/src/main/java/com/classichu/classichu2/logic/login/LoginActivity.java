package com.classichu.classichu2.logic.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseActivity;


public class LoginActivity extends BaseActivity{


    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        configTitle("登录");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.id_frame_layout, LoginFragment.newInstance("", ""))
                .commitAllowingStateLoss();
    }

    private void configTitle(CharSequence title) {
        Toolbar toolbar = findById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //必须设置在setSupportActionBar(mToolbar);后才有效
      /*  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
        //
        setTitle(title);
    }


}

