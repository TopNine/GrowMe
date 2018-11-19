package grow.firstcode.seekbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flow.grow.R;

public class SeekBarActivity extends AppCompatActivity {
    private static final String TAG = "SeekBarActivity";

    private TextView mSeekChangeView;
    private TextView mSeekEndView;

    public static void launchActivity(Context context) {
        Log.d(TAG, "launchActivity: ");
        Intent intent = new Intent(context, SeekBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        initView();
        loadData();
    }

    private void initView() {
        mSeekChangeView = findViewById(R.id.tv_seek_change);
        mSeekEndView = findViewById(R.id.tv_seek_end);
        SeekBar seekBar = findViewById(R.id.sb_test);
        seekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
    }

    private void loadData() {
    }

    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.d(TAG, "onProgressChanged: " + progress);
            mSeekChangeView.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int progress = seekBar.getProgress();
            Log.d(TAG, "onStopTrackingTouch: " + progress);
            mSeekEndView.setText(String.valueOf(progress));
        }
    };
}

