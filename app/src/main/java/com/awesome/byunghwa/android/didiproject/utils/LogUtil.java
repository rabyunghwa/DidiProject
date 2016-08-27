package com.awesome.byunghwa.android.didiproject.utils;

import android.util.Log;

public class LogUtil {

    static boolean isReleased = false;

    // message type set to Object so that we can handle
    // all types of messages
    public static void log(String tag, Object msg) {
        if (isReleased) {
            return;
        }
        Log.i(tag, String.valueOf(msg));
    }

}
