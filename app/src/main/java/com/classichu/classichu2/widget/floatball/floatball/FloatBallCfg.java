package com.classichu.classichu2.widget.floatball.floatball;

import android.graphics.drawable.Drawable;

public class FloatBallCfg {
    public Drawable mIcon;
    public int mSize;
    public int mEdge_hide_delayMillis;
    public float mEdge_hide_width_percent;

    public FloatBallCfg(int size, Drawable icon) {
        this(size, icon, 2 * 1000, 0.7f);
    }

    public FloatBallCfg(int size, Drawable icon, float edge_hide_percent) {
        this(size, icon, 2 * 1000, edge_hide_percent);
    }

    public FloatBallCfg(int size, Drawable icon, int edge_hide_delayMillis, float edge_hide_width_percent) {
        mSize = size;
        mIcon = icon;
        mEdge_hide_delayMillis = edge_hide_delayMillis;
        mEdge_hide_width_percent = edge_hide_width_percent;
    }
}
