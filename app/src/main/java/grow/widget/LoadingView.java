package grow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flow.grow.R;

public class LoadingView extends LinearLayout {
    private ProgressBar mProgressBar;
    private TextView mLoadingText;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        setOrientation(VERTICAL);
        setViewHeight();
        setBackgroundColor(getResources().getColor(R.color.red));
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.grow_loading_view, this);
        mProgressBar = findViewById(R.id.pb_grow_loading);
        mLoadingText = findViewById(R.id.tv_loading_text);
    }

    private void setViewHeight() {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.common_100);
        setLayoutParams(layoutParams);
    }
}
