package grow;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

import grow.firstcode.anim.AnimActivity;
import grow.firstcode.binarytree.BinaryTreeActivity;
import grow.firstcode.seekbar.SeekBarActivity;

public class MainHomeActivity extends Activity {
    private static final String TAG = "MainHomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        initView();
    }

    private void initView() {
        Button binaryTreeBtn = findViewById(R.id.btn_binary_tree);
        binaryTreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BinaryTreeActivity.launchActivity(MainHomeActivity.this);
            }
        });

        Button animBtn = findViewById(R.id.btn_anim);
        animBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimActivity.launchActivity(MainHomeActivity.this);
            }
        });

        Button seekBarBtn = findViewById(R.id.btn_seek_bar);
        seekBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBarActivity.launchActivity(MainHomeActivity.this);
            }
        });
    }
}
