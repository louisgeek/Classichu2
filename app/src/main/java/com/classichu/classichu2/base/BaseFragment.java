package com.classichu.classichu2.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classichu.classichu2.custom.CustomFragment;

/**
 * Created by Classichu on 2017/9/30.
 */

public abstract class BaseFragment extends CustomFragment {
    public static final String BUNDLE_EXTRA_INT_1 = "BUNDLE_EXTRA_INT_1";
    public static final String BUNDLE_EXTRA_STR_1 = "BUNDLE_EXTRA_STR_1";

    protected static String mTag;
    protected Activity mActivity;
    protected Context mContext;
    protected View mRootLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mTag = this.getClass().getSimpleName();

        mRootLayout = inflater.inflate(setupLayoutResId(), container, false);


        /**
         * last
         */
        initView(mRootLayout,savedInstanceState);

        return mRootLayout;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * ===================================protected abstract=============================
     */
    protected abstract int setupLayoutResId();

    protected abstract void initView(View rootLayout,Bundle savedInstanceState);

    /**
     * =======================================protected===================================
     */

    protected <V extends View> V findById(int resId) {
        return (V) mRootLayout.findViewById(resId);
    }

    protected void startAty(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    protected void startAty(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
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
}
