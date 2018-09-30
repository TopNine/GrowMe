package grow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

import grow.firstcode.binarytree.BinaryTreeActivity;

public class MainHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        initView();
    }

    private void initView() {
        Button binaryTreeBtn = findViewById(R.id.tv_binary_tree);
        binaryTreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBinaryTree();
            }
        });
    }

    private void doBinaryTree() {
        BinaryTreeActivity.launchActivity(this);
    }
}
