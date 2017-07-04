package com.darkstar.toolkits.utils.logger;

import com.darkstar.toolkits.utils.CommonUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by levy on 2015/12/21.
 */
public class LogcatHelper {
    public static final String BUFFER_MAIN = "main";
    public static final String BUFFER_EVENTS = "events";
    public static final String BUFFER_RADIO = "radio";

    public static final String PRIORITY_V = "V";
    public static final String PRIORITY_D = "D";
    public static final String PRIORITY_I = "I";
    public static final String PRIORITY_W = "W";
    public static final String PRIORITY_E = "E";
    public static final String PRIORITY_F = "F";
    public static final String PRIORITY_S = "S";

    public static final String FORMAT_BRIEF = "brief";
    public static final String FORMAT_PROCESS = "process";
    public static final String FORMAT_TAG = "tag";
    public static final String FORMAT_RAW = "raw";
    public static final String FORMAT_TIME = "time";
    public static final String FORMAT_THREADTIME = "threadtime";
    public static final String FORMAT_LONG = "long";

    public static void dumpLogcat(String buffer, String dst) throws IOException {
        Runtime.getRuntime().exec(buildLogcat(false, true, dst, buffer, null, FORMAT_TIME));
    }

    public static String buildLogcat(boolean clear, boolean dump, String path, String buffer,
                                     TagInfo filter, String format) {
        String str = "logcat";
        if (clear) {
            str += " -c";
            return str;
        }
        if (dump) {
            str = str + " -d";
        }
        if (path != null) {
            str = str + " -f " + path;
        }
        if (buffer != null) {
            str = str + " -b " + buffer;
        }
        if (filter != null) {
            str = str + " -s " + filter.tag + ":" + filter.priority;
        }
        str = str + " -v " + format;
        return str;
    }

    public static class TagInfo {
        public String tag;
        public String priority;
    }

    /**
     * read cause from throwable
     * @param ex throwable
     * @return result of cause
     */
    public static String readThrowable(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String ret = writer.toString();
        CommonUtils.closeSilently(printWriter);
        CommonUtils.closeSilently(writer);
        return ret;
    }
}
