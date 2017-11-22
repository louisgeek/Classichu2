package com.classichu.classichu2.widget.floatmenu;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

import com.classichu.classichu2.widget.floatmenu.floatbutton.FloatButton;
import com.classichu.classichu2.widget.floatmenu.floatbutton.FloatButtonCfg;
import com.classichu.classichu2.widget.floatmenu.menu.FloatMenu;
import com.classichu.classichu2.widget.floatmenu.menu.FloatMenuCfg;
import com.classichu.classichu2.widget.floatmenu.menu.FloatMenuItem;
import com.classichu.classichu2.widget.floatmenu.runner.IFloatMenuExpandAction;

import java.util.ArrayList;
import java.util.List;


public class FloatMenuManager {
    public int mScreenWidth, mScreenHeight;
    private int mStatusBarHeight;

    private IFloatMenuPermission mPermission;
    private OnFloatButtonClickListener onFloatButtonClickListener;
    private WindowManager mWindowManager;
    private Context mContext;
    private FloatButton floatButton;
    private FloatMenu floatMenu;
    public int floatbuttonX, floatbuttonY;
    private boolean isShowing = false;
    private List<FloatMenuItem> menuItems = new ArrayList<>();

    public FloatMenuManager(Context application, FloatButtonCfg buttonCfg) {
        this(application, buttonCfg, null);
    }

    public FloatMenuManager(Context application, FloatButtonCfg buttonCfg, FloatMenuCfg menuCfg) {
        //  !!!!!!!!!WindowManager 可能会报错 mContext = application.getApplicationContext();
        mContext = application;
        int statusbarId = application.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (statusbarId > 0) {
            mStatusBarHeight = application.getResources().getDimensionPixelSize(statusbarId);
        }
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        computeScreenSize();
        floatButton = new FloatButton(mContext, this, buttonCfg);
        floatMenu = new FloatMenu(mContext, this, menuCfg, new IFloatMenuExpandAction() {
            @Override
            public void onExpandChange(boolean isExpanded) {
                //###floatButton.setSelected(isExpanded);
                if (isExpanded) {
                    ObjectAnimator.ofFloat(floatButton, "rotation", 0f, 45f+90f).setDuration(250).start();
                } else {
                    ObjectAnimator.ofFloat(floatButton, "rotation", 45f+90f, 0f).setDuration(250).start();
                }
            }
        });
    }

    public void buildMenu() {
        inflateMenuItem();
    }

    /**
     * 添加一个菜单条目
     *
     * @param item
     */
    public FloatMenuManager addMenuItem(FloatMenuItem item) {
        menuItems.add(item);
        return this;
    }

    public int getMenuItemSize() {
        return menuItems != null ? menuItems.size() : 0;
    }

    /**
     * 设置菜单
     *
     * @param items
     */
    public FloatMenuManager setMenu(List<FloatMenuItem> items) {
        menuItems.clear();
        menuItems.addAll(items);
        return this;
    }

    private void inflateMenuItem() {
        floatMenu.removeAllItemViews();
        for (FloatMenuItem item : menuItems) {
            floatMenu.addItem(item);
        }
    }

    /**
     * 常规入口
     *
     * @param itemDrawables
     * @param onFloatMenuItemClickListener
     */
    public void setUpdateMenuItems(int[] itemDrawables, final OnFloatMenuItemClickListener onFloatMenuItemClickListener) {
        if (itemDrawables == null || itemDrawables.length <= 0) {
            return;
        }
        List<FloatMenuItem> floatMenuItemList = new ArrayList<>();
        for (int itemDrawableResid : itemDrawables) {
            FloatMenuItem floatMenuItem = new FloatMenuItem(itemDrawableResid) {
                @Override
                public boolean actionClick(View view, int resid) {
                    if (onFloatMenuItemClickListener != null) {
                        return onFloatMenuItemClickListener.onFloatMenuItemClick(view, resid);
                    }
                    return false;
                }
            };
            floatMenuItemList.add(floatMenuItem);
        }
        this.closeMenu();
        this.setMenu(floatMenuItemList).buildMenu();
    }

    public int getButtonSize() {
        return floatButton.getSize();
    }

    public void computeScreenSize() {
        Point point = new Point();
        mWindowManager.getDefaultDisplay().getSize(point);
        mScreenWidth = point.x;
        mScreenHeight = point.y;
        mScreenHeight -= mStatusBarHeight;
    }

    public void show() {
        if (mPermission == null) {
            return;
        }
        if (!mPermission.hasFloatButtonPermission(mContext)) {
            mPermission.onRequestFloatButtonPermission();
            return;
        }
        if (isShowing) return;
        isShowing = true;
        floatButton.setVisibility(View.VISIBLE);
        floatButton.attachToWindow(mWindowManager);
        floatMenu.detachFromWindow(mWindowManager);
    }

    public void closeMenu() {
        floatMenu.closeMenu();
    }

    public void reset() {
        floatButton.setVisibility(View.VISIBLE);
        floatButton.postSleepRunnable();
        floatMenu.detachFromWindow(mWindowManager);
    }

    public void onFloatButtonClick() {
        if (menuItems != null && menuItems.size() > 0) {
            floatMenu.attachToWindow(mWindowManager);
        } else {
            if (onFloatButtonClickListener != null) {
                onFloatButtonClickListener.onFloatButtonClick();
            }
        }
    }

    public void hide() {
        if (!isShowing) return;
        isShowing = false;
        floatButton.detachFromWindow(mWindowManager);
        floatMenu.detachFromWindow(mWindowManager);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        computeScreenSize();
        reset();
    }

    public void setPermission(IFloatMenuPermission iPermission) {
        this.mPermission = iPermission;
    }

    public void setOnFloatButtonClickListener(OnFloatButtonClickListener listener) {
        onFloatButtonClickListener = listener;
    }

    public interface OnFloatButtonClickListener {
        void onFloatButtonClick();
    }


    public interface OnFloatMenuItemClickListener {
        boolean onFloatMenuItemClick(View view, int resid);
    }

    public interface IFloatMenuPermission {

        boolean onRequestFloatButtonPermission();

        /**
         * detect whether allow  using floatmenu here or not.
         *
         * @return
         */
        boolean hasFloatButtonPermission(Context context);

        /**
         * request floatmenu permission
         */
//##############@ void requestFloatButtonPermission(Activity activity);
    }
}
