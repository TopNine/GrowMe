package grow.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import grow.utils.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "Flow.BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName() + "---onCreate: ");
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, getClass().getSimpleName() + "---onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, getClass().getSimpleName() + "---onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, getClass().getSimpleName() + "---onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, getClass().getSimpleName() + "---onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, getClass().getSimpleName() + "---onStop: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, getClass().getSimpleName() + "---onDestroy: ");
        super.onDestroy();
    }
}
