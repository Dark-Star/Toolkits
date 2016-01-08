package com.darkstar.toolkits.guard;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.darkstar.toolkits.common.FileUtils;
import com.darkstar.toolkits.common.LogHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by levy on 2016/1/7.
 * To be implement more function for log collection
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = CrashHandler.class.getSimpleName();

    private static CrashHandler mInstance;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;
    private String mLogRoot;
    private String mAction;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US);

    public static synchronized CrashHandler getInstance() {
        if (mInstance == null) {
            mInstance = new CrashHandler();
        }
        return mInstance;
    }

    private CrashHandler() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void init(Context context, String root) {
        init(context, root);
    }

    public void init(Context context, String root, String action) {
        mContext = context;
        mLogRoot = root;
        mAction = action;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String dirName = "Log_" + sdf.format(new Date());
        File dir = new File(mLogRoot, dirName);
        File crashFile = FileUtils.newFile(dir, "exception.txt");
        FileUtils.writeStr2File(LogHelper.readThrowable(ex), crashFile);
        if (!TextUtils.isEmpty(mAction)) {
            mContext.startService(new Intent(mAction));
        }
        mDefaultHandler.uncaughtException(thread, ex);
    }
}
