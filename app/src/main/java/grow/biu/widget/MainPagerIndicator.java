package grow.biu.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flow.grow.R;

public class MainPagerIndicator extends LinearLayout {
    public interface OnIndicatorClickListener {
        void onIndicatorClick(View view, int position);
    }

    private OnIndicatorClickListener mListener;
    private int mCurrentIndex = 0;

    public void setOnIndicatorClickListener(OnIndicatorClickListener listener) {
        this.mListener = listener;
    }

    public MainPagerIndicator(Context context) {
        super(context);
    }

    public MainPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setCurrentItem(mCurrentIndex);
    }

    public void addIndicator(int iconRes, int textRes) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.main_pager_indicator_item_view, null);
        TextView textView = view.findViewById(R.id.indicator_item_view);
        textView.setText(textRes);
        Drawable top = getResources().getDrawable(iconRes);
        int size = getResources().getDimensionPixelSize(R.dimen.common_30);
        top.setBounds(0, 0, size, size);
        textView.setCompoundDrawables(null, top, null, null);
        LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        addView(view, layoutParams);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onIndicatorClick(view, getChildCount());
            }
        });
    }

    public void setCurrentItem(int index) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view == null)
                continue;
            if (index == i)
                view.setSelected(true);
            else
                view.setSelected(false);
        }
    }
}
