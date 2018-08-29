package grow.biu.glide;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.request.target.Target;

import java.security.MessageDigest;

public class VipCircleTransform implements Transformation<Bitmap> {
    private static final String TAG = "UI.VipCircleTransform";
    private BitmapPool mBitmapPool;
    private int mDefaultRes;

    public VipCircleTransform(Context context, int def) {
        this.mBitmapPool = Glide.get(context).getBitmapPool();
        mDefaultRes = def;
    }

    public String getId() {
        return "VipCircleTransform";
    }

    @Override
    public Resource<Bitmap> transform(Context context, Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();
        int targetWidth = outWidth == Target.SIZE_ORIGINAL ? source.getWidth() : outWidth;
        int targetHeight = outHeight == Target.SIZE_ORIGINAL ? source.getHeight() : outHeight;
        Bitmap bitmap = mBitmapPool.get(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Matrix matrix = new Matrix();
        float scaleW = 0.8f * (targetWidth / source.getWidth());
        float scaleH = 0.8f * (targetHeight / source.getHeight());
        Log.i(TAG, "scaleW==" + scaleW);
        Log.i(TAG, "scaleH==" + scaleH);
        matrix.postScale(scaleW, scaleH);
        matrix.postTranslate((targetWidth - source.getWidth() * scaleW) / 2, targetHeight - source.getHeight() * scaleH);
        canvas.drawBitmap(source, matrix, paint);

        matrix.reset();
        Bitmap defBitmap = ((BitmapDrawable) context.getResources().getDrawable(mDefaultRes)).getBitmap();
        scaleW = 0.25f * (targetWidth / defBitmap.getWidth());
        scaleH = 0.25f * (targetHeight / defBitmap.getHeight());
        Log.i(TAG, "def scaleW==" + scaleW);
        Log.i(TAG, "def scaleH==" + scaleH);
        matrix.setScale(scaleW, scaleH);
        matrix.postTranslate((targetWidth - defBitmap.getWidth() * scaleW) / 2, 0);
        canvas.drawBitmap(defBitmap, matrix, paint);
        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof VipCircleTransform;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }


    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            messageDigest.update(getId().getBytes(CHARSET));
        }
    }
}
