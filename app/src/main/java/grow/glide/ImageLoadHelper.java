package grow.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.flow.grow.R;

import java.io.File;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ImageLoadHelper {
    private static final String TAG = "CI.ImageLoadHelper";

    public static void loadUrl(Context context, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadRes(Context context, int res, final ImageView imageView) {
        Glide.with(context)
                .load(res)
                .apply(new RequestOptions().centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadFile(Context context, File file, final ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(file)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    public static void loadBitmap(Context context, Bitmap bitmap, final ImageView imageView) {
        Glide.with(context)
                .load(bitmap)
                .into(imageView);
    }

    public static Bitmap getBitmap(Context context, String url) {
        try {
            return Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File downLoadUrl(Context context, String url) {
        try {
            File file = Glide.with(context).download(url).submit().get();
            Log.i(TAG, "-----------file: " + file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "-----------download url failed: " + e);
        }
        return null;
    }

    public static void loadVipCircle(Context context, Drawable res, ImageView view) {
        RequestOptions options = new RequestOptions()
                .transforms(new CircleTransform(2, Color.WHITE)
                        , new VipTransform(context.getResources().getDrawable(R.mipmap.vip_image))
                ).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        Glide.with(context).load(res).apply(options).transition(withCrossFade()).into(view);
    }
}
