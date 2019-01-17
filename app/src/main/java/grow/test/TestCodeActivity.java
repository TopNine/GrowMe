package grow.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import grow.base.BaseActivity;
import grow.dialog.BaseDialogFragment;

public class TestCodeActivity extends BaseActivity {
    private static final String TAG = "Flow.TestCodeActivity";

    public static void launchActivity(Context context) {
        Log.d(TAG, "launchActivity: ");
        Intent intent = new Intent(context, TestCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        initView();
    }

    private void initView() {
        View detachListView = findViewById(R.id.test_detach_list);
        detachListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDetachList();
            }
        });
    }

    /**
     * 自定义分组,将一个集合分成n个集合
     */
    private void testDetachList() {
        List<String> datas = signalDetachDatas();
        double count = getRandomCount();
        int size = datas.size();
        int num = (int) Math.ceil(size / count);

        List<String> showList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            showList.add("<---- test list i==" + i + "---> ");
            int t = Math.min((i + 1) * num, size);
            List<String> nums = new ArrayList<>();
            for (int j = i * num; j < t; j++) {
                nums.add(datas.get(j));
            }
            showList.addAll(nums);
        }
        showDialog(Arrays.toString(showList.toArray()));
    }

    private List<String> signalDetachDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            datas.add(String.valueOf(i));
        }

        return datas;
    }

    private double getRandomCount() {
        return new Random().nextInt(4) + 1;
    }

    private void showDialog(String msg) {
        BaseDialogFragment dialogFragment = new BaseDialogFragment();
        Bundle args = new Bundle();
        args.putString("msg", msg);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "test_code");
    }
}
