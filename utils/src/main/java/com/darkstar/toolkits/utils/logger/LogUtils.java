package com.darkstar.toolkits.utils.logger;

import android.util.Log;

/**
 * Created by levy on 2016/3/4.
 */
public class LogUtils {
    private static final String TAG = LogUtils.class.getSimpleName();

    private static boolean sVerbose = true;
    private static boolean sDebug = true;
    private static boolean sInfo = true;
    private static boolean sWarn = true;
    private static boolean sError = true;

    public static int v(String tag, String msg) {
        return printLog(Log.VERBOSE, tag, msg);
    }

    public static int d(String tag, String msg) {
        return printLog(Log.DEBUG, tag, msg);
    }

    public static int i(String tag, String msg) {
        return printLog(Log.INFO, tag, msg);
    }

    public static int w(String tag, String msg) {
        return printLog(Log.WARN, tag, msg);
    }

    public static int e(String tag, String msg) {
        return printLog(Log.ERROR, tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        return printLog(Log.VERBOSE, tag, msg, tr);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return printLog(Log.DEBUG, tag, msg, tr);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return printLog(Log.INFO, tag, msg, tr);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return printLog(Log.WARN, tag, msg, tr);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return printLog(Log.ERROR, tag, msg, tr);
    }

    private static int printLog(int level, String tag, String msg) {
        switch (level) {
            case Log.VERBOSE: {
                return sVerbose ? Log.v(tag, msg) : 0;
            }
            case Log.DEBUG: {
                return sDebug ? Log.d(tag, msg) : 0;
            }
            case Log.INFO: {
                return sInfo ? Log.i(tag, msg) : 0;
            }
            case Log.WARN: {
                return sWarn ? Log.w(tag, msg) : 0;
            }
            case Log.ERROR: {
                return sError ? Log.e(tag, msg) : 0;
            }
            default:
                return 0;
        }
    }

    private static int printLog(int level, String tag, String msg, Throwable tr) {
        switch (level) {
            case Log.VERBOSE: {
                return sVerbose ? Log.v(tag, msg, tr) : 0;
            }
            case Log.DEBUG: {
                return sDebug ? Log.d(tag, msg, tr) : 0;
            }
            case Log.INFO: {
                return sInfo ? Log.i(tag, msg, tr) : 0;
            }
            case Log.WARN: {
                return sWarn ? Log.w(tag, msg, tr) : 0;
            }
            case Log.ERROR: {
                return sError ? Log.e(tag, msg, tr) : 0;
            }
            default:
                return 0;
        }
    }

    public static void enable(int level) {
        setEnable(level, true);
    }

    public static void disable(int level) {
        setEnable(level, false);
    }

    private static void setEnable(int level, boolean enable) {
        switch (level) {
            case Log.VERBOSE: {
                sVerbose = enable;
                break;
            }
            case Log.DEBUG: {
                sDebug = enable;
                break;
            }
            case Log.INFO: {
                sInfo = enable;
                break;
            }
            case Log.WARN: {
                sWarn = enable;
                break;
            }
            case Log.ERROR: {
                sError = enable;
                break;
            }
            default:
                break;
        }
    }
}
