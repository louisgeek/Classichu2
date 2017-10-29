package com.classichu.classichu2.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Classichu on 2017/9/30.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static final String BUNDLE_EXTRA_INT_1 = "BUNDLE_EXTRA_INT_1";
    public static final String BUNDLE_EXTRA_STR_1 = "BUNDLE_EXTRA_STR_1";
    protected static String mTag;
    protected Activity mActivity;
    protected Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(setupLayoutResId());
        mTag = this.getClass().getSimpleName();
        mContext = this;
        mActivity = this;
        /**
         * last
         */
        initView(savedInstanceState);
    }

    /**
     * ===================================protected abstract=============================
     */
    protected abstract int setupLayoutResId();

    protected abstract void initView(Bundle savedInstanceState);
    /**
     * =================================protected===============================
     */
    /**
     * Find View
     */
    protected <V extends View> V findById(int resId) {
        return (V) findViewById(resId);
    }

    protected <V extends View> V findById(View view, int resId) {
        return (V) view.findViewById(resId);
    }

    protected void startAty(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startAty(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void startAtyForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void startAtyForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    protected String getBundleExtraStr1() {
        String bundleExtra = null;
        Bundle bundle = getBundleExtra();
        if (bundle != null) {
            bundleExtra = bundle.getString(BUNDLE_EXTRA_STR_1, "");
        }
        return bundleExtra;
    }

    protected int getBundleExtraInt1() {
        int bundleExtra = 0;
        Bundle bundle = getBundleExtra();
        if (bundle != null) {
            bundleExtra = bundle.getInt(BUNDLE_EXTRA_INT_1);
        }
        return bundleExtra;
    }

    protected Bundle getBundleExtra() {
        return getIntent().getExtras();
    }

    protected Bundle createBundleExtraInt1(int value1) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_EXTRA_INT_1, value1);
        return bundle;
    }

    protected Bundle createBundleExtraStr1(String value1) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_EXTRA_STR_1, value1);
        return bundle;
    }

    /**
     * 获取根视图的容器 包含标题栏 content等
     */
    protected View getContentView() {
        //DecorView是一个FrameLayout子类  里面有一个id为content的FrameLayout用来存放我们的布局
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

    /**
     * 获取根视图  xml根节点 Layout
     */
    protected View getContentViewRootLayout() {
        //DecorView是一个FrameLayout子类  里面有一个id为content的FrameLayout用来存放我们的布局
        ViewGroup viewGroup = (ViewGroup) getContentView();
        return viewGroup.getChildAt(0);
    }


}
