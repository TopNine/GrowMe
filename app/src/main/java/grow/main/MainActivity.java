package grow.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

public class MainActivity extends Activity {
    private static final String TAG = "UI.MainActivity";

    private ViewPager mViewPager;
    private MainPagerAdapter mPagerAdapter;
    private Button mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
        Log.i(TAG, "onCreate: ");
    }

    private void initView() {
        mViewPager = findViewById(R.id.main_viewpager);
        mTitleView = findViewById(R.id.main_title);
        mTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainTabHomeActivity.launchActivity(MainActivity.this);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
