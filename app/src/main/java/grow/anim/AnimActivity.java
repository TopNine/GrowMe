package grow.anim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

public class AnimActivity extends AppCompatActivity {
    private static final String TAG = "BinaryTreeActivity";
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
        Button operateBtn = findViewById(R.id.btn_operate_anim);
        operateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLoading();
            }
        });
    }

    private void toggleLoading() {
        boolean isVisible = mLoadingView.getVisibility() == View.VISIBLE;
        mLoadingView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}

