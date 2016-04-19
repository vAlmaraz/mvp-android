package com.valmaraz.mvp;

/**
 * Created by Víctor Almaraz on 16/04/2016.
 * http://www.valmaraz.com
 */
public class Log {

    public static void i(String tag, String string) {
        if (Environment.showLog) {
            android.util.Log.i(tag, string);
        }
    }

    public static void e(String tag, String string) {
        if (Environment.showLog) {
            android.util.Log.e(tag, string);
        }
    }

    public static void d(String tag, String string) {
        if (Environment.showLog) {
            android.util.Log.d(tag, string);
        }
    }

    public static void v(String tag, String string) {
        if (Environment.showLog) {
            android.util.Log.v(tag, string);
        }
    }

    public static void w(String tag, String string) {
        if (Environment.showLog) {
            android.util.Log.w(tag, string);
        }
    }

    public static String getStackTraceString(Exception e) {
        return android.util.Log.getStackTraceString(e);
    }
}
