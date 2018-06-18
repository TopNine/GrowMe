package grow.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.List;

import grow.widget.MainPagerIndicator;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "UI.MainActivity";

    private static final int TAB_MAIN = 0;
    private ViewPager mViewPager;
    private MainPagerAdapter mPagerAdapter;
    private MainPagerIndicator mIndicator;
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

        mIndicator = new MainPagerIndicator(this);
        mIndicator.addIndicator(R.mipmap.ic_launcher_round, R.string.tab_main);

        mIndicator.setCurrentItem(TAB_MAIN);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainTabHomeFragment());
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mPagerAdapter);

        mIndicator.setOnIndicatorClickListener(new MainPagerIndicator.OnIndicatorClickListener() {
            @Override
            public void onIndicatorClick(View view, int position) {
                mViewPager.setCurrentItem(position);
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
