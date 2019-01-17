package grow.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

public class Logger {
    private static boolean sDebug;

    public static void instance(Context context) {
        sDebug = context.getApplicationInfo() != null && (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public static void v(String tag, String msg) {
        if (sDebug) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sDebug) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sDebug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sDebug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebug) {
            Log.d(tag, msg);
        }
    }
}
