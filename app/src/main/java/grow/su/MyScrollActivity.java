package grow.su;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flow.grow.R;

public class MyScrollActivity extends Activity {
    private ImageView mImageView1;
    private ImageView mImageView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_scroll_activity);
        initView();
    }

    private void initView() {
        mImageView1 = findViewById(R.id.image_view1);
        mImageView2 = findViewById(R.id.image_view2);
        int margin = getResources().getDimensionPixelOffset(R.dimen.common_30);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mImageView1.getLayoutParams();
        params.height = getResources().getDisplayMetrics().heightPixels - margin;
        mImageView1.setLayoutParams(params);
        mImageView1.setImageResource(R.mipmap.ic_launcher);
        mImageView2.setImageResource(R.mipmap.top);
    }
}
