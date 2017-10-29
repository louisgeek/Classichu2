package com.classichu.classichu2.custom;

import android.util.Log;


/**
 * Created by louisgeek on 2017/2/19.
 */

public class CLog {
    public static final int NONE = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;

    private static String mTAG = "CLog";
    private static int sLogLevel = VERBOSE;

    public static void setLogLevel(int logLevel) {
        sLogLevel = logLevel;
    }

    public static void setTAG(String tag) {
        mTAG = tag;
    }

    //=========================================
    public static void v(String tag, String msg) {
        if (sLogLevel <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLogLevel <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sLogLevel <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sLogLevel <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sLogLevel <= ERROR) {
            Log.e(tag, msg);
        }
    }

    //=========================================
    public static void v(String msg) {
        v(mTAG, msg);
    }

    public static void d(String msg) {
        d(mTAG, msg);
    }

    public static void i(String msg) {
        i(mTAG, msg);
    }

    public static void w(String msg) {
        w(mTAG, msg);
    }

    public static void e(String msg) {
        e(mTAG, msg);
    }

    /**
     * "hamburger".substring(4, 8) returns "urge"
     * "smiles".substring(1, 5) returns "mile"
     *
     * @param msg
     */
    public static void longLog_i(String msg) {
      /*  if (sLogLevel<INFO) {
            return;
        }*/
        int logMaxLength = 4000;
        if (msg.length() > logMaxLength) {
            int outSize = msg.length() % logMaxLength == 0 ? msg.length() / logMaxLength : msg.length() / logMaxLength + 1;
            for (int i = 0; i < outSize; i++) {
                if (i == outSize - 1) {//最后一行
                    Log.i(mTAG, msg.substring(i * logMaxLength));
                } else {
                    Log.i(mTAG, msg.substring(i * logMaxLength, (i + 1) * logMaxLength));
                }
            }
        } else {
            Log.i(mTAG, msg);
        }
    }

}
