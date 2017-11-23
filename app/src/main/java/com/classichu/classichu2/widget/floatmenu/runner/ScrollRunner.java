package com.classichu.classichu2.widget.floatmenu.runner;


import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

public class ScrollRunner implements Runnable {
    private Scroller mScroller;
    private IFloatAction mCarrier;
    private int mDuration = 250;
    private int lastX, lastY;

    public ScrollRunner(IFloatAction carrier) {
        mCarrier = carrier;
        mScroller = new Scroller(carrier.getContext(), new LinearInterpolator());
    }

    public void start(int dx, int dy) {
        start(dx, dy, mDuration);
    }

    public void start(int dx, int dy, int duration) {
        start(0, 0, dx, dy, duration);
    }

    public void start(int startX, int startY, int dx, int dy) {
        start(startX, startY, dx, dy, mDuration);
    }

    public void start(int startX, int startY, int dx, int dy, int duration) {
        this.mDuration = duration;
        mScroller.startScroll(startX, startY, dx, dy, duration);
        mCarrier.removeCallbacks(this);
        mCarrier.post(this);
        lastX = startX;
        lastY = startY;
    }

    @Override
    public void run() {
        if (mScroller.computeScrollOffset()) {
            Log.i("qqq", "run: ");
            final int currentX = mScroller.getCurrX();
            final int currentY = mScroller.getCurrY();
            mCarrier.onMove(lastX, lastY, currentX, currentY);
            mCarrier.post(this);
            lastX = currentX;
            lastY = currentY;
        } else {
            mCarrier.removeCallbacks(this);
            mCarrier.onDone();
        }
    }

}