package com.darkstar.toolkits.utils;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by levy on 2015/11/19.
 */

@SuppressWarnings("unused")
public class CommonUtils {
    private static final String TAG = CommonUtils.class.getSimpleName();

    public static final String CHARSET_UTF8 = "UTF-8";

    // Time Unit
    public static final int ONE_SECOND = 1000;
    public static final int TEN_SECOND = ONE_SECOND * 10;
    public static final int HALF_MINUTE = TEN_SECOND * 3;
    public static final int ONE_MINUTE = HALF_MINUTE * 2;


    public static void waitWithoutInterrupt(Object object) {
        try {
            object.wait();
        } catch (InterruptedException e) {
            Log.w(TAG, "unexpected interrupt: " + object);
        }
    }

    public static void closeSilently(Closeable o) {
        if (o == null) {
            return;
        }
        try {
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dismissDialogSafely(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void sendLocalBroadcast(Context context, Intent intent) {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        lbm.sendBroadcast(intent);
    }

    public static void registerLocalBroadcast(Context context, BroadcastReceiver receiver,
                                              IntentFilter filter) {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        lbm.registerReceiver(receiver, filter);
    }

    public static void unRegisterLocalBroadcast(Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(context);
        lbm.unregisterReceiver(receiver);
    }
}
