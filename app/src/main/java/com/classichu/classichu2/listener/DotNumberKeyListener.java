package com.classichu.classichu2.listener;

import android.text.InputType;
import android.text.method.NumberKeyListener;

/**
 * Created by Classichu on 2017/11/15.
 * 只能输入  点或者数字
 */

public class DotNumberKeyListener extends NumberKeyListener{
    @Override
    protected char[] getAcceptedChars() {
        return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }
}
