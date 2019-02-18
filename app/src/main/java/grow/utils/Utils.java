package grow.utils;

public class Utils {
    public static void killProgress() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
