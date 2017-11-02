package com.classichu.classichu2.widget;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.view.View;

import com.classichu.classichu2.tool.ReflectTool;


/**
 * Created by Classichu on 2017/5/24.
 * 可关闭ShiftingMode的BottomNavigationView
 */

public class BottomNavigationViewSupport extends BottomNavigationView {

    private BottomNavigationMenuView mMenuView;
    private BottomNavigationItemView[] mButtons;

    public BottomNavigationViewSupport(Context context) {
        this(context, null);
    }

    public BottomNavigationViewSupport(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationViewSupport(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //
        this.enableMenuViewShiftMode(false);
        this.enableItemViewShiftingMode(false);
    }

    /**
     * 根据索引选中对应Item
     * 推荐
     *
     * @param index
     */
    public void setChecked(int index) {
        if (index >= 0) {
            getMenu().getItem(index).setChecked(true);
        }
    }

    /**
     * 根据menu item id选中对应Item
     * 和 setChecked 功能一致
     *
     * @param menu_item_id
     */
    public void setClickedItem(int menu_item_id) {
        View view = findViewById(menu_item_id);
        if (view != null) {
            view.performClick();
        }
    }

    public void enableMenuViewShiftMode(boolean enable) {
        if (mMenuView == null) {
            mMenuView = ReflectTool.getFieldValueFromSuperClass(BottomNavigationView.class, this, "mMenuView");
        }
        ReflectTool.setFieldValue(mMenuView, "mShiftingMode", enable);
        // mMenuView.updateMenuView();
    }

    public void enableItemViewShiftingMode(boolean enable) {
        if (mMenuView == null) {
            mMenuView = ReflectTool.getFieldValueFromSuperClass(BottomNavigationView.class, this, "mMenuView");
        }
        if (mMenuView == null) {
            return;
        }
        if (mButtons == null) {
            mButtons = ReflectTool.getFieldValue(mMenuView, "mButtons");
        }
        for (BottomNavigationItemView button : mButtons) {
            ReflectTool.setFieldValue(button, "mShiftingMode", enable);
        }
        mMenuView.updateMenuView();
    }
}

