package grow.glide;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.Target;

import java.security.MessageDigest;

public class VipTransform extends BitmapTransformation {
    private static final String TAG = "UI.VipTransform";

    private static final int VERSION = 1;
    private static final String ID = "VipTransform" + VERSION;
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private Drawable mDefDrawable;

    public VipTransform(Drawable drawable) {
        this.mDefDrawable = drawable;
    }

    @SuppressWarnings("PMD.CompareObjectsWithEquals")
    @Override
    protected Bitmap transform(
            @NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        int targetWidth = outWidth == Target.SIZE_ORIGINAL ? toTransform.getWidth() : outWidth;
        int targetHeight = outHeight == Target.SIZE_ORIGINAL ? toTransform.getHeight() : outHeight;
        Bitmap bitmap = pool.get(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Matrix matrix = new Matrix();
        float scaleW = 0.8f * targetWidth / toTransform.getWidth();
        float scaleH = 0.8f * targetHeight / toTransform.getHeight();
        float scale = Math.min(scaleW, scaleH);
        Log.i(TAG, "scaleW==" + scaleW);
        Log.i(TAG, "scaleH==" + scaleH);
        matrix.postScale(scale, scale);
        matrix.postTranslate((targetWidth - toTransform.getWidth() * scale) / 2, targetHeight - toTransform.getHeight() * scale);
        canvas.drawBitmap(toTransform, matrix, paint);

        if (mDefDrawable == null)
            return bitmap;

        matrix.reset();
        Bitmap defBitmap = ((BitmapDrawable) mDefDrawable).getBitmap();
        scaleH = 0.35f * targetHeight / defBitmap.getHeight();
        Log.i(TAG, "def scaleH==" + scaleH);
        matrix.setScale(scaleH, scaleH);
        matrix.postTranslate((targetWidth - defBitmap.getWidth() * scaleH) / 2, 0);
        canvas.drawBitmap(defBitmap, matrix, paint);
        return bitmap;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof VipTransform;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
