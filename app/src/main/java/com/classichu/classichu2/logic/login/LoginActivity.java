package com.classichu.classichu2.logic.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseActivity;
import com.classichu.classichu2.helper.CacheHelper;
import com.classichu.classichu2.tool.CacheTool;
import com.classichu.dialogview.manager.DialogManager;


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
/*        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key1 = CacheHelper.getString("key11");
                String key2 = CacheHelper.getString("key22");
                DialogManager.showTipDialog(mActivity, "xx", key1 + "\n" + key2);
            }
        });*/
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

