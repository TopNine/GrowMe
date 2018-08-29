package grow.biu.grant;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class FileProviderCompat {
    public static Uri getUriForFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getUriForFileAPI24(context, file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public static Uri getUriForFileAPI24(Context context, File file) {
        Uri fileUri = FileProvider.getUriForFile(context,
                context.getPackageName() + ".fileProvider", file);
        return fileUri;
    }
}
