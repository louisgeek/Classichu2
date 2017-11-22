package com.classichu.classichu2.widget.floatmenu.menu;

import android.view.View;

public abstract class FloatMenuItem {
    public int resid ;

    public FloatMenuItem(int resid) {
        this.resid=resid;
    }


    public abstract boolean actionClick(View view, int resid);
}
