package grow.anim;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flow.grow.R;

public class LoadingView extends LinearLayout {
    private static final String TAG = "LoadingView";

    private ImageView mTopView;
    private ImageView mPointView;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loading, this);
        mTopView = findViewById(R.id.loading_top_view);

    }
}
