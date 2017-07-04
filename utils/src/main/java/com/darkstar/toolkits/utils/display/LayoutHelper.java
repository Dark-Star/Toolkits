package com.darkstar.toolkits.utils.display;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.darkstar.toolkits.utils.logger.LogUtils;

/**
 * Created by levy on 2015/11/19.
 */
@SuppressWarnings("unused")
public class LayoutHelper {
    private static final String TAG = LayoutHelper.class.getSimpleName();

    public static int getPx(Context context, int unit, int value) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return (int) (value * metrics.density);
            case TypedValue.COMPLEX_UNIT_SP:
                return (int) (value * metrics.scaledDensity);
        }
        return 0;
    }

    public static int getDp(Context context, int unit, int value) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return (int) (value / metrics.density);
            case TypedValue.COMPLEX_UNIT_DIP:
                return value;
            case TypedValue.COMPLEX_UNIT_SP:
                return (int) (value / metrics.scaledDensity);
        }
        return 0;
    }

    public static int getSp(Context context, int unit, int value) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return (int) (value / metrics.scaledDensity);
            case TypedValue.COMPLEX_UNIT_DIP:
                return (int) ((value * metrics.density) /
                              (metrics.scaledDensity)); //first dp to px then  px to sp
            case TypedValue.COMPLEX_UNIT_SP:
                return value;
        }
        return 0;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public static void printScreenParams(Context cxt) {
        WindowManager wm = (WindowManager) cxt.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        float density = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        LogUtils.i(TAG, "xdpi=" + xdpi + "; ydpi=" + ydpi);
        LogUtils.i(TAG, "density=" + density + "; densityDPI=" + densityDPI);

        int screenWidthDip = dm.widthPixels;        // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels;      // 屏幕宽（dip，如：533dip）

        LogUtils.i(TAG,
                "screenWidthDip=" + screenWidthDip + "; screenHeightDip=" + screenHeightDip);

        int screenWidth = (int) (dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）
        int screenHeight = (int) (dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px）

        LogUtils.i(TAG, "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);

        Configuration config = cxt.getResources().getConfiguration();

        int smallestScreenWidth = config.smallestScreenWidthDp;      // 屏幕最小宽

        LogUtils.i(TAG, "smallestScreenWidth=" + smallestScreenWidth);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        int screenWidthDip = dm.widthPixels;

        LogUtils.i(TAG, "screenWidthDip=" + screenWidthDip);

        return screenWidthDip;
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        int screenHeightDip = dm.heightPixels;

        LogUtils.i(TAG, "screenHeightDip=" + screenHeightDip);

        return screenHeightDip;
    }
}
