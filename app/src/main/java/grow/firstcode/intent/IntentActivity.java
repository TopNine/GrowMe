package grow.firstcode.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.flow.grow.R;

import grow.base.BaseActivity;

public class IntentActivity extends BaseActivity {
    private static final String TAG = "IntentActivity";

    public static final String MY_ACTION = "com.flow.grow.testactivity.ACTION_START";
    public static final String MY_CATEGORY = "com.flow.grow.testactivity.MY_CATEGORY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_activity);
        initView();
    }

    private void initView() {
        findViewById(R.id.intent_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://time.geekbang.org/column/article/80921"));
                startActivity(intent);
            }
        });

        findViewById(R.id.intent_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }
}
