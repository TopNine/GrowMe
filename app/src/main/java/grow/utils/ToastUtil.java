package grow.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void maskText(Context context, String msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }
}
