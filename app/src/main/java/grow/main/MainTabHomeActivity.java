package grow.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.List;

import grow.entry.MainEntry;
import grow.listener.OnItemClickListener;
import grow.photo.DrawBitmapActivity;
import grow.photo.PhotoActivity;
import grow.share.TestShareActivity;
import grow.su.ColorActivity;
import session.SessionActivity;

public class MainTabHomeActivity extends Activity {
    private static final String TAG = "UI.MainTabHomeActivity";

    private RecyclerView mRecycleView;
    private MainAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_home_activity);
        initView();
        initData();
    }

    private void initData() {
        List<MainEntry> items = new ArrayList<>();
        items.add(new MainEntry.Builder().name("glide load").icon(R.mipmap.ic_launcher).id(1).builder());
        items.add(new MainEntry.Builder().name("draw bitmap").icon(R.mipmap.ic_launcher).id(2).builder());
        items.add(new MainEntry.Builder().name("Test Code").icon(R.mipmap.ic_launcher).id(3).builder());
        items.add(new MainEntry.Builder().name("RecycleView Update").icon(R.mipmap.ic_launcher).id(4).builder());
        items.add(new MainEntry.Builder().name("Listener").icon(R.mipmap.ic_launcher).id(5).builder());
        items.add(new MainEntry.Builder().name("Color").icon(R.mipmap.ic_launcher).id(6).builder());
        Log.i(TAG, "initData: " + items);
        mAdapter.setItems(items);
    }

    private void initView() {
        mRecycleView = findViewById(R.id.main_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        mAdapter = new MainAdapter();
        mRecycleView.setAdapter(mAdapter);
        mContext = MainTabHomeActivity.this;
        mAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                if (!(item instanceof MainEntry))
                    return;

                MainEntry mainEntry = (MainEntry) item;

                switch (mainEntry.mId) {
                    case 1:
                        PhotoActivity.startActivity(mContext);
                        break;
                    case 2:
                        DrawBitmapActivity.startDrawActivity(mContext);
                        break;
                    case 3:
                        TestShareActivity.startActivity(mContext);
                        break;
                    case 4:
                        SessionActivity.startActivity(mContext);
                        break;
                    case 6:
                        ColorActivity.launchActivity(mContext);
                        break;
                }
            }
        });
    }
}
