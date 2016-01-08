package com.darkstar.toolkits.common;

import java.io.File;
import java.io.FileOutputStream;
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
        File dir = file.getParentFile();

        boolean ret;
        if (!dir.exists()) {
            ret = dir.mkdirs();
            if (!ret) return null;
        }
        try {
            ret = file.createNewFile();
        } catch (IOException e) {
            LogUtils.w(TAG, "", e);
            return null;
        }
        return ret ? file : null;
    }

    public static boolean writeStr2File(String str, File file) {
        if (!file.exists()) {
            file = newFile(file);
        }
        if (file == null) return false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(str.getBytes(Utils.CHARSET_UTF8));
        } catch (IOException e) {
            LogUtils.w(TAG, "", e);
            return false;
        } finally {
            Utils.closeSilently(fos);
        }
        return true;
    }

    public static void delete(File file) {
        boolean ret;
        if (file.isFile()) {
            ret = file.delete();
            LogUtils.w(TAG,
                "File [" + file.getName() + "] deleted " + (ret ? "Failed" : "Succeed"));
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                ret = file.delete();
                LogUtils.w(TAG,
                    "File [" + file.getName() + "] deleted " + (ret ? "Failed" : "Succeed"));
                return;
            }

            for (File f : childFiles) {
                delete(f);
            }
            ret = file.delete();
            LogUtils.w(TAG,
                "File [" + file.getName() + "] deleted " + (ret ? "Failed" : "Succeed"));
        }
    }
}
