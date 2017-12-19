package grow.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.flow.grow.R;

import java.io.File;

import grow.glide.ImageLoadHelper;
import grow.utils.TestUtils;

public class PhotoActivity extends Activity {
    private static final String TAG = "UI.PhotoActivity";

    private ImageView mImageView;
    private Button mButton;
    private static String testUrl = "http://d.hiphotos.baidu.com/image/pic/item/ca1349540923dd54bdb23fb8db09b3de9d824819.jpg";
    private static String webpUrl = "http://cdn7.ushareit.cn/sz2/i/171113/1EdDJ_w300_h225_s1300552.gif";

    enum LoadType {
        FILE, BITMAP, DRAWABLE, URL, WEBP, RUN_WEBP, GIF, DOWNLOAD
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PhotoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.glide_image_view);
        mButton = (Button) findViewById(R.id.test_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadPhoto(LoadType.DOWNLOAD, testUrl);
//                laodMusicIcon();
                loadNoExeImage();
                testActivity(null);
            }
        });
    }

    private void laodMusicIcon() {
//        String path = "/storage/emulated/0/SHAREit/download/audios/Chetak.sa";
        String path = "/storage/emulated/0/SHAREit/download/audios/Jatt Di Kanak.sa";
//        String path = Environment.getExternalStorageDirectory()+"/sKVF5_128k.mp3";
//        String path = Environment.getExternalStorageDirectory()+"/sKnBn_320k.mp3";
        TestUtils.setArtwork(this, path, mImageView);
    }

    private void loadPhoto(LoadType type, String url) {
        switch (type) {
            case FILE:
                downloadAndLoadFile();
                break;
            case GIF:
                loadUrl(url);
                break;
            case URL:
                loadUrl(url);
                break;
            case WEBP:
                loadUrl(url);
                break;
            case BITMAP:
                loadBitmap(url);
                break;
            case DRAWABLE:
                loadUrl(url);
                break;
            case RUN_WEBP:
                loadUrl(url);
                break;
            case DOWNLOAD:
                downloadUrl(url);
                break;
            default:
                Log.i(TAG, "no load type");
                break;
        }
    }

    private void loadUrl(String url) {
        ImageLoadHelper.loadUrl(this, url, mImageView);
    }

    private void loadBitmap(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = ImageLoadHelper.getBitmap(PhotoActivity.this, url);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoadHelper.loadBitmap(PhotoActivity.this, bitmap, mImageView);
                    }
                });
            }
        }).start();
    }

    private void downloadAndLoadFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final File file = ImageLoadHelper.downLoadUrl(PhotoActivity.this, testUrl);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoadHelper.loadFile(PhotoActivity.this, file, mImageView);
                    }
                });
            }
        }).start();
    }

    private void downloadUrl(String url) {
        Glide.with(this)
                .downloadOnly()
                .apply(new RequestOptions().skipMemoryCache(true))
                .load(url)
                .listener(new RequestListener<File>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                        Log.i(TAG, "-----------------resource failed: " + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                        Log.i(TAG, "-----------------resource: " + resource);
                        mImageView.setImageBitmap(BitmapFactory.decodeFile(resource.getAbsolutePath()));
                        return false;
                    }
                }).submit();
    }

    private void loadNoExeImage() {
        String path = Environment.getExternalStorageDirectory() + "/top";
        ImageLoadHelper.loadUrl(this, path, mImageView);
    }

    private void testActivity(Context context) {
        if (context instanceof Activity) {
            Log.i(TAG, "-----------------context: ");
        }

        Log.i(TAG, "-----------------context: failed");
    }
}
