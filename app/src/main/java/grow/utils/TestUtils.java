package grow.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import com.flow.grow.R;


public class TestUtils {
    private static final String TAG = "Test.TestUtils";

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    public static Bitmap setArtwork(Context context, String musicPath, ImageView ivPic) {
        try {
            String path = musicPath;
            Log.i(TAG, "--------------path: " + path);
            MediaMetadataRetriever myRetriever = new MediaMetadataRetriever();
            myRetriever.setDataSource(path); // the URI of audio file
            byte[] artwork;

            artwork = myRetriever.getEmbeddedPicture();
            Log.i(TAG, "--------------artwork: " + artwork);
            if (artwork != null) {
                Bitmap bMap = BitmapFactory.decodeByteArray(artwork, 0, artwork.length);
                ivPic.setImageBitmap(bMap);
                Log.i(TAG, "--------------bMap: " + bMap);
                return bMap;
            } else {
                ivPic.setImageResource(R.mipmap.ic_launcher);
                return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            }
        } catch (Exception e) {
            Log.e(TAG, "--------------parse failed:  " + e);
            ivPic.setImageResource(R.mipmap.ic_launcher);
            return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
    }
}
