package com.classichu.classichu2.widget.floatmenu.menu;

import android.graphics.drawable.Drawable;

public abstract class FloatMenuItem {
    /**
     * 菜单icon
     */
    public Drawable mDrawable;

    public FloatMenuItem(Drawable drawable) {
        this.mDrawable = drawable;
    }

    /**
     * 点击次菜单执行的操作
     */
    public abstract void action();
}
