package grow.firstcode.anim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

import grow.base.BaseActivity;

public class AnimActivity extends BaseActivity {
    private static final String TAG = "AnimActivity";
    private View mLoadingView;

    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, AnimActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        initView();
    }

    private void initView() {
        mLoadingView = findViewById(R.id.lv_loading);
        Button operateBtn = findViewById(R.id.btn_operate_anim);
        operateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLoading();
            }
        });
    }

    private void toggleLoading() {
        int visibility = mLoadingView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
        mLoadingView.setVisibility(visibility);
    }
}

