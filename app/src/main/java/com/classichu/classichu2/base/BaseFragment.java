package com.classichu.classichu2.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classichu.classichu2.R;
import com.classichu.classichu2.custom.CLog;
import com.classichu.classichu2.custom.CustomFragment;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.tool.SizeTool;
import com.classichu.classichu2.widget.floatmenu.FloatMenuManager;
import com.classichu.classichu2.widget.floatmenu.floatbutton.FloatButtonCfg;
import com.classichu.classichu2.widget.floatmenu.menu.FloatMenuCfg;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    protected SwipeRefreshLayout id_swipe_refresh_layout;
    private Unbinder mUnbinder;

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
        //
        mUnbinder = ButterKnife.bind(this, mRootLayout);
        //
        initSwipeRefreshLayout();

        initFloatMenu();
        /**
         * last
         */
        initView(mRootLayout, savedInstanceState);

        return mRootLayout;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * ===================================protected abstract=============================
     */
    protected abstract int setupLayoutResId();

    protected abstract void initView(View rootLayout, Bundle savedInstanceState);

    protected abstract void loginAgainSuccess(UserLoginBean userLoginBean);

    /**
     * =======================================protected===================================
     */

    protected <V extends View> V findById(int resId) {
        return (V) mRootLayout.findViewById(resId);
    }

    protected <V extends View> V findById(View view, int resId) {
        return (V) view.findViewById(resId);
    }

    protected void showSnack(int resId) {
        Snackbar.make(mRootLayout, resId, Snackbar.LENGTH_SHORT).show();
    }

    protected void showSnack(int resId, int duration) {
        Snackbar.make(mRootLayout, resId, duration).show();
    }

    protected void showSnack(CharSequence text) {
        Snackbar.make(mRootLayout, text, Snackbar.LENGTH_SHORT).show();
    }

    protected void showSnack(CharSequence text, int duration) {
        Snackbar.make(mRootLayout, text, duration).show();
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


    protected void showSwipeRefreshLayout() {
        if (id_swipe_refresh_layout != null) {
            // id_swipe_refresh_layout.setRefreshing(true);
            id_swipe_refresh_layout.post(new Runnable() {
                @Override
                public void run() {
                    id_swipe_refresh_layout.setRefreshing(true);
                }
            });
        }
    }

    protected void hideSwipeRefreshLayout() {
        if (id_swipe_refresh_layout != null) {
            id_swipe_refresh_layout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    id_swipe_refresh_layout.setRefreshing(false);
                }
            }, 600);
        }
    }


    protected int configSwipeRefreshLayoutResId() {
        return 0;
    }

    protected void toRefreshData() {
    }


    /**
     * =======================================private===================================
     */
    private void initSwipeRefreshLayout() {
        if (configSwipeRefreshLayoutResId() == 0) {
            return;
        }
        id_swipe_refresh_layout = findById(configSwipeRefreshLayoutResId());
        // id_swipe_refresh_layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        id_swipe_refresh_layout.setColorSchemeResources(R.color.colorAccent);
        //mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        id_swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CLog.d("onRefresh刷新数据");
                toRefreshData();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected int[] configFloatMenuItemDrawables() {
        return null;
    }

    protected void updateFloatMenuItemDrawables(int[] itemDrawables, final FloatMenuManager.OnFloatMenuItemClickListener onFloatMenuItemClickListener) {
        if (mFloatMenuManager != null) {
            mFloatMenuManager.setUpdateMenuItems(itemDrawables, onFloatMenuItemClickListener);
        }
    }

    protected boolean onFloatMenuClick(View view, int resid) {
        return false;
    }


    private FloatMenuManager mFloatMenuManager;

    private void initFloatMenu() {
        Context context = getActivity();
        int[] itemDrawables = configFloatMenuItemDrawables();
        if (itemDrawables == null || itemDrawables.length <= 0) {
            return;
        }
        //1 初始化悬浮按钮配置，定义好悬浮按钮大小和icon的drawable
        int iconSize = SizeTool.dp2px(56);
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.selector_menu_icon);
        FloatButtonCfg buttonCfg = new FloatButtonCfg(iconSize, icon);

        //2 需要显示悬浮菜单
        //2.1 初始化悬浮菜单配置，有菜单item的大小和菜单item的个数
        int menuSize = SizeTool.dp2px(250);
        int menuItemSize = SizeTool.dp2px(48);
        FloatMenuCfg menuCfg = new FloatMenuCfg(menuSize, menuItemSize);
        //3 生成mFloatMenuManager
        mFloatMenuManager = new FloatMenuManager(context, buttonCfg, menuCfg);
        mFloatMenuManager.setPermission(new FloatMenuManager.IFloatMenuPermission() {
            @Override
            public boolean onRequestFloatButtonPermission() {
                return true;
            }

            @Override
            public boolean hasFloatButtonPermission(Context context) {
                return true;
            }
        });
        mFloatMenuManager.setUpdateMenuItems(itemDrawables, new FloatMenuManager.OnFloatMenuItemClickListener() {
            @Override
            public boolean onFloatMenuItemClick(View view, int resid) {
                return onFloatMenuClick(view, resid);
            }
        });
        //5 如果没有添加菜单，可以设置悬浮按钮点击事件
    /*	if (mFloatMenuManager.getMenuItemSize() == 0) {
            mFloatMenuManager.setOnFloatButtonClickListener(new FloatMenuManager.OnFloatButtonClickListener() {
				@Override
				public void onFloatButtonClick() {
					// toast("点击了悬浮按钮");
				}
			});
		}*/
        mFloatMenuManager.show();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mFloatMenuManager != null) {
            mFloatMenuManager.show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFloatMenuManager != null) {
            mFloatMenuManager.hide();
        }
    }

}
