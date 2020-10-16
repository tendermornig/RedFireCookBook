package com.hnqcgc.redfirecookbook.util;

import android.util.Log;

public class LogUtil {

    private static LogUtil logUtil;

    private static final int  VERBOSE = 1;

    private static final int  DEBUG = 2;

    private static final int  INFO = 3;

    private static final int WARN = 4;

    private static final int  ERROR = 5;

    private static final int level = VERBOSE;

    private LogUtil() {}

    public static LogUtil getInstance() {
        if (logUtil == null) {
            logUtil = new LogUtil();
        }
        return logUtil;
    }

    public void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag,msg);
        }
    }

    public void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag,msg);
        }
    }

    public void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag,msg);
        }
    }

    public void w(String tag, String msg) {
        if (level <= WARN) {
            Log.w(tag,msg);
        }
    }

    public void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag,msg);
        }
    }

}
