package grow.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flow.grow.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

import grow.glide.ImageLoadHelper;
import grow.helper.MediaHelper;


public class TestShareActivity extends Activity {
    private static final String TAG = "UI.TestShareActivity";

    private Button mStartBtn;
    private TextView mResultView;
    private ImageView mImageView;
    private ImageView mImageView2;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestShareActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_it_activity);
        Log.i(TAG, "onCreate: ");
        initView();
        loadData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
        loadUri(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    private void loadData() {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action) && action.equalsIgnoreCase(Intent.ACTION_VIEW)) {
//            loadExternalImage();
//            loadExternalMusic();
            loadUri(intent);
        }
    }

    private void initView() {
        mStartBtn = (Button) findViewById(R.id.share_start);
        mImageView = findViewById(R.id.image_view);
        mImageView2 = findViewById(R.id.image_view2);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testTransactionTooLargeException();
//                loadExternalImage();
//                loadUri();
            }
        });
        mResultView = (TextView) findViewById(R.id.test_result);
    }

    private void loadUri(Intent intent) {
//        Intent intent = getIntent();
        Uri uri = intent.getData();
        Log.i(TAG, "------------------loadUri: uri==" + uri);
        String path = uri.toString();
        ImageLoadHelper.loadUrl(this, path, mImageView);
    }

    private void loadExternalMusic() {
        Intent intent = getIntent();
        Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        Log.i(TAG, "------------------loadExternalMusic: " + uri);
        String path = uri.getPath();
        Log.i(TAG, "------------------loadExternalMusic: path==" + uri);

        String encodePath = uri.getEncodedPath();
        Log.i(TAG, "------------------loadExternalMusic: encodePath==" + uri);

        String filePath = MediaHelper.saveUriToFile(this, uri);
        Log.i(TAG, "loadExternalMusic: filePath==" + filePath);
    }

    private void loadExternalImage() {
//        Uri uri = Uri.parse("content://com.android.chrome.FileProvider/downloads/u%3D500014805%2C1856676836%26fm%3D27%26gp%3D0.jpg");
        Intent intent = getIntent();
//        Uri uri = intent.getData();
        Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        Log.i(TAG, "------------------loadExternalImage: " + uri);

        String path = MediaHelper.getRealPathFromURI(this, uri);
        String path1 = uri.getPath();
        String path2 = uri.getEncodedPath();
        Log.i(TAG, "loadExternalImage: path== " + path);
        Log.i(TAG, "loadExternalImage: path111== " + path1);
        Log.i(TAG, "loadExternalImage: path222== " + path2);
//        ImageLoadHelper.loadUrl(this, path1, mImageView);
//        ImageLoadHelper.loadUrl(this, path2, mImageView);
        try {
            Bitmap bitmap2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            ImageLoadHelper.loadUrl(this, getContentResolver().openInputStream(uri), mImageView2);
//            String bitmapPath = MediaHelper.saveUriToFile(bitmap2, uri);
//            Log.i(TAG, "loadExternalImage: bitmapPath==" + bitmapPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
//            FileProvider.
            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptor != null) {
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
//                ImageLoadHelper.loadBi   tmap(this, bitmap, mImageView);
                String bitmapPath = MediaHelper.saveUriToFile(this, uri);
                Log.i(TAG, "loadExternalImage: bitmapPath==" + bitmapPath);
                ImageLoadHelper.loadUrl(this, fileDescriptor, mImageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ImageLoadHelper.loadUri(this, uri, mImageView);
    }
}
