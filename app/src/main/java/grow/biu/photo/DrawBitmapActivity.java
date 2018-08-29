package grow.biu.photo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.flow.grow.R;

import java.io.File;

import grow.biu.glide.ImageLoadHelper;


public class DrawBitmapActivity extends Activity {
    private static final String TAG = "DrawBitmapActivity";
    private static final int PERMISSION_STORAGE = 0x600;
    private ImageView mImageView;
    private Button mButton;
    private Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_bitmap_activity);
        initView();
    }

    public static void startDrawActivity(Context context) {
        Intent intent = new Intent(context, DrawBitmapActivity.class);
        context.startActivity(intent);
    }

    private void drawBitmap() {
        String path = Environment.getExternalStorageDirectory() + "/timg.jpg";
        File file = new File(path);
        if (!file.exists()) {
            Log.i(TAG, "---------------------file: " + file + ",path: " + path);
            return;
        }
//        Bitmap.createBitmap(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL, Bitmap.Config.ARGB_8888);
        Bitmap bitmap = BitmapFactory.decodeFile(path).copy(Bitmap.Config.ARGB_8888, true);
//        Bitmap bitmap = ImageLoadHelper.getBitmap(this,path);
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Log.i(TAG, "---------------------bitmap: " + bitmap);
        if (bitmap == null)
            return;

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        Bitmap topMap = BitmapFactory.decodeResource(getResources(), R.mipmap.top);
        canvas.drawBitmap(topMap, 0, 0, paint);

        Bitmap topBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        canvas.drawBitmap(topBitmap, 0, 0, paint);

        mImageView.setImageBitmap(bitmap);
    }

    private void drawVIPBitmap() {
        ImageLoadHelper.loadVipCircle(this, getResources().getDrawable(R.mipmap.ic_launcher), mImageView);
    }

    private void drawVIPBitmap2() {
        int width = mImageView.getWidth();
        int height = mImageView.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bitmap);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        Rect srcRect;
        Rect destRect;
        int drawW;
        int drawH;
        Bitmap source = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        drawW = width * 8 / 10;
        drawH = height * 8 / 10;

        srcRect = new Rect(0, 0, width, height);
        destRect = new Rect(0, (height - source.getHeight()) / 2, width, width + source.getHeight());
        canvas.drawBitmap(source, srcRect, destRect, paint);

//        Bitmap defBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.vip_image)).getBitmap();
//        drawW = defBitmap.getWidth();
//        drawH = defBitmap.getHeight();
//        srcRect = new Rect(0, 0, drawW, drawH);
//        destRect = new Rect(0, 0, drawW, drawH);
//        canvas.drawBitmap(defBitmap, srcRect, destRect, paint);
        mImageView.setImageBitmap(bitmap);
    }

    private void initView() {
        mImageView = findViewById(R.id.draw_bitmap);
        mButton = findViewById(R.id.draw_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawVIPBitmap();
            }
        });

        mButton2 = findViewById(R.id.draw_button2);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawVIPBitmap2();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    drawBitmap();
                    return;
                }
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    , PERMISSION_STORAGE);
        } else
            drawBitmap();
    }
}
