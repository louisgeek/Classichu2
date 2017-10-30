package com.classichu.classichu2.listener;

import android.os.CountDownTimer;
import android.view.View;

import com.classichu.classichu2.custom.CLog;


/**
 * Created by Classichu on 2017-6-9.
 */

public abstract class OnViewClickEnabledListener implements View.OnClickListener {
    private static final int CLICK_DELAY = 300;
    private CountDownTimer countDownTimer;
    private void start(final View view) {
        view.setEnabled(false);
        if (countDownTimer != null) {
            countDownTimer.cancel();
            //防止new出多个导致时间跳动加速
            countDownTimer = null;
        }
        countDownTimer = new CountDownTimer(CLICK_DELAY, CLICK_DELAY) {
            @Override
            public void onTick(long millisUntilFinished) {
                CLog.i("onTick"+String.valueOf(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                CLog.i("onFinish");
                view.setEnabled(true);
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            start(v);
            onViewClick(v);
        }
    }

    protected abstract void onViewClick(View v);
}
