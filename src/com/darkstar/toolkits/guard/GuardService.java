package com.darkstar.toolkits.guard;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by levy on 2016/1/7.
 */
public class GuardService extends Service {
    private static final String TAG = GuardService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @SuppressLint("InlinedApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PackageManager manager = getPackageManager();
                Intent i = manager.getLaunchIntentForPackage("com.cleverm");
                startActivity(i);
            }
        }, 2000);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }
}
