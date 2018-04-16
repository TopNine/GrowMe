package grow.su;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.flow.grow.R;

/**
 * Created by zhangliuyan on 2018/4/9.
 */

public class ListenerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listener_activity);
        initView();
    }

    private void initView() {
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
