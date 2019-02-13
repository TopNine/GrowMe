package grow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flow.grow.R;

import grow.base.BaseActivity;
import grow.firstcode.anim.AnimActivity;
import grow.firstcode.binarytree.BinaryTreeActivity;
import grow.firstcode.intent.IntentActivity;
import grow.firstcode.seekbar.SeekBarActivity;
import grow.test.TestCodeActivity;
import grow.utils.ToastUtil;

public class MainHomeActivity extends BaseActivity {
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

        Button testCodeBtn = findViewById(R.id.btn_test_code);
        testCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestCodeActivity.launchActivity(MainHomeActivity.this);
            }
        });

        Button testIntentBtn = findViewById(R.id.btn_test_intent);
        testIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity.MY_ACTION);
                intent.addCategory(IntentActivity.MY_CATEGORY);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                ToastUtil.maskText(this, "You clicked add", Toast.LENGTH_SHORT);
                break;
            case R.id.remove_item:
                ToastUtil.maskText(this, "You clicked remove", Toast.LENGTH_SHORT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
