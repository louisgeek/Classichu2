package com.classichu.classichu2.logic.setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseActivity;
import com.classichu.classichu2.listener.DotNumberKeyListener;
import com.classichu.classichu2.tool.SharedPreferencesTool;

import butterknife.BindView;


public class SettingActivity extends BaseActivity {


    @BindView(R.id.id_et_java_ip)
    EditText id_et_java_ip;
    @BindView(R.id.id_et_java_port)
    EditText id_et_java_port;


    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        configTitle("设置");


        id_et_java_ip.setText((String) SharedPreferencesTool.get("java_ip", ""));
        id_et_java_port.setText((String) SharedPreferencesTool.get("java_port", ""));
        id_et_java_ip.setKeyListener(new DotNumberKeyListener());
        //###id_et_java_ip.setInputType(0x00002002);//代码中的TYPE_NUMBER_FLAG_DECIMAL 是8192  0x00002000 不行， xml中是8194 0x00002002
        id_et_java_port.setInputType(InputType.TYPE_CLASS_NUMBER);


    }

    private void configTitle(CharSequence title) {
        Toolbar toolbar = findById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //必须设置在setSupportActionBar(mToolbar);后才有效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_classic_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_classic_ok:
                SharedPreferencesTool.put("java_ip", id_et_java_ip.getText().toString());
                SharedPreferencesTool.put("java_port", id_et_java_port.getText().toString());
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
