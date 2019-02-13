package grow.firstcode.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flow.grow.R;

import grow.base.BaseActivity;

public class IntentSecondActivity extends BaseActivity {
    private static final String TAG = "IntentSecondActivity";

    public static final int REQUEST_CODE = 1;

    public static final String MSG = "msg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_second_activity);
        initView();
    }

    public static void launch(Activity activity, String msg) {
        Intent intent = new Intent(activity, IntentSecondActivity.class);
        intent.putExtra(MSG, msg);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    private void initView() {
        findViewById(R.id.intent_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MSG, "this is a good day");
                setResult(RESULT_OK, intent);
                finish();
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

        TextView textView = findViewById(R.id.intent_text);
        String msg = getIntent().getStringExtra(MSG);
        Log.i(TAG, "initView: msg== " + msg);
        textView.setText(msg);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MSG, "hello intent first.");
        setResult(RESULT_OK, intent);
        finish();
    }
}
