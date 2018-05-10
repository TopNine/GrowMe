package grow.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class MainIndicator extends LinearLayout {
    public interface OnIndicatorClickListener {
        void onIndicatorClick(View view, int position);
    }

    private OnIndicatorClickListener mListener;
    private int mCurrentIndex = 0;

    public void setOnIndicatorClickListener(OnIndicatorClickListener listener) {
        this.mListener = listener;
    }

    public MainIndicator(Context context) {
        super(context);
    }

    public MainIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setCurrentItem(mCurrentIndex);
    }

    private void addIndicator() {

    }

    private void setCurrentItem(int index) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view == null)
                continue;

            if (i == index)
                view.setSelected(true);
            else
                view.setSelected(false);
        }
    }
}
