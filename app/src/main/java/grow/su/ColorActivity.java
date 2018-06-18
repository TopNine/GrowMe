package grow.su;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.flow.grow.R;

/**
 * Created by zhangliuyan on 2018/4/9.
 */

public class ColorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_activity);
        initView();
    }

    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, ColorActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
        findViewById(R.id.color1).setBackgroundColor(getResources().getColor(R.color.common_FF145dbb));
        findViewById(R.id.color2).setBackgroundColor(getResources().getColor(R.color.common_FF1668d0));
        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
