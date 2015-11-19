package com.darkstar.toolkits.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by levy on 2015/11/19.
 */
@SuppressWarnings("unused")
public class LayoutHelper {
    private static final String TAG = LayoutHelper.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void dumpScreenInfo(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        DisplayMetrics realMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(realMetrics);
        StringBuilder sb = new StringBuilder();
        sb.append("resolution : ").append(metrics.widthPixels).append("x").append(metrics
            .heightPixels);
        sb.append(" real resolution : ").append(realMetrics.widthPixels).append("x").append
            (realMetrics.heightPixels);
        sb.append(" density = ").append(metrics.density);
        sb.append(" DPI = ").append(metrics.densityDpi);
        Log.i(TAG, sb.toString());
    }
}
