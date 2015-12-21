package com.darkstar.toolkits.common;

import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by levy on 2015/12/21.
 */
public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static File newFile(String dir, String name) {
        return newFile(new File(dir, name));
    }

    public static File newFile(File dir, String name) {
        return newFile(new File(dir, name));
    }

    public static File newFile(String path) {
        return newFile(new File(path));
    }

    public static File newFile(File file) {
        if (file == null) return null;
        boolean ret = false;
        File dir = file.getParentFile();

        if (!dir.exists()) {
            ret = dir.mkdirs();
        }
        if (!ret) return null;
        try {
            ret = file.createNewFile();
        } catch (IOException e) {
            Log.w(TAG, "", e);
            return null;
        }
        return ret ? file : null;
    }
}
