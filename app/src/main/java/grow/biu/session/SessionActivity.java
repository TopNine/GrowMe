package grow.biu.session;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.List;

import grow.biu.listener.OnItemClickListener;

public class SessionActivity extends Activity {
    private static final String TAG = "UI.MainActivity";

    private RecyclerView mRecycleView;
    private SessionAdapter mAdapter;
    private int mCount;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SessionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_activity);
        initView();
        initData();
    }

    private void initData() {
        List<TransItem> items = new ArrayList<>();
        TransItem item = new TransItem();
        item.id = mRecycleView.getId();
        item.mName = "进度";
        item.mProgress = 10;
        items.add(item);
        mAdapter.setItems(items);
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.session_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandle.sendEmptyMessageDelayed(1, 1000);
            }
        });
        mRecycleView = (RecyclerView) findViewById(R.id.session_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        mAdapter = new SessionAdapter();
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {

            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (mCount > 100)
                        return;
                    TransItem item = new TransItem();
                    item.id = mRecycleView.getId();
                    item.mName = "进度: " + mCount;
                    item.mProgress = mCount;
                    mAdapter.updateProgress(item);
                    mCount += 10;
                    mHandle.sendEmptyMessageDelayed(1, 1000);
                    break;
            }
        }
    };
}
