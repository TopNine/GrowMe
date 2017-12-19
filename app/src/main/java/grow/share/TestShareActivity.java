package grow.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flow.grow.R;

import java.util.List;


public class TestShareActivity extends Activity {
    private static final String TAG = "UI.TestShareActivity";

    private Button mStartBtn;
    private TextView mResultView;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TestShareActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_it_activity);
        initView();
        loadData();
    }

    private void loadData() {

    }

    private void initView() {
        mStartBtn = (Button) findViewById(R.id.share_start);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testTransactionTooLargeException();
            }
        });
        mResultView = (TextView) findViewById(R.id.test_result);
    }

    private void testTransactionTooLargeException() {
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    int count = 0;
                    List<PackageInfo> list = getPackageManager()
                            .getInstalledPackages(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
                    for (PackageInfo info : list) {
                        if (count >= 1000) {
                            break;
                        }
                        try {
//                            synchronized (TestShareActivity.class) {
                                PackageInfo pi = getPackageManager()
                                        .getPackageInfo(info.packageName,
                                                PackageManager.GET_ACTIVITIES);
                                Log.e(TAG, "yanchen threadid:" + Thread.currentThread().getId()
                                        + ",i:" + count++);
//                            }

                        } catch (Exception e) {
                            Log.e(TAG, "get package info failed: ", e);
                        }
                    }
                }
            }.start();
        }
    }
}
