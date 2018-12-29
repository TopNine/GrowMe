package grow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flow.grow.R;

import grow.biu.glide.ImageLoadHelper;

public class LoadingView extends ScrollView implements View.OnAttachStateChangeListener {
    private static final String TAG = "LoadingView";

    private ProgressBar mProgressBar;
    private TextView mLoadingText;
    private static final String url = "https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0F/09/ChMkJlauze2IPKICABzBh_ueXY0AAH9JAMQ2qUAHMGf334.jpg";

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.grow_loading_view, this);
        mProgressBar = findViewById(R.id.pb_grow_loading);
        mLoadingText = findViewById(R.id.tv_loading_text);
        ImageView imageView = findViewById(R.id.iv_loading);
        ImageLoadHelper.loadUrl(getContext(), url, imageView);
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        Log.i(TAG, "onViewAttachedToWindow: ");
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        Log.i(TAG, "onViewDetachedFromWindow: ");
    }
}
