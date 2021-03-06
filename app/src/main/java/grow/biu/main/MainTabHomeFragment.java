package grow.biu.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.List;

import grow.biu.photo.PhotoActivity;
import grow.biu.session.SessionActivity;
import grow.biu.share.TestShareActivity;
import grow.biu.su.ColorActivity;
import grow.biu.entry.MainEntry;
import grow.biu.listener.OnItemClickListener;
import grow.biu.photo.DrawBitmapActivity;

public class MainTabHomeFragment extends Fragment {
    private static final String TAG = "UI.MainTabHomeFragment";

    private RecyclerView mRecycleView;
    private MainAdapter mAdapter;

    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, MainTabHomeFragment.class);
        context.startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_home_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
    }

    private void initData() {
        List<MainEntry> items = new ArrayList<>();
        items.add(new MainEntry.Builder().name("glide load").icon(R.mipmap.ic_launcher).id(1).build());
        items.add(new MainEntry.Builder().name("draw bitmap").icon(R.mipmap.ic_launcher).id(2).build());
        items.add(new MainEntry.Builder().name("Test Code").icon(R.mipmap.ic_launcher).id(3).build());
        items.add(new MainEntry.Builder().name("RecycleView Update").icon(R.mipmap.ic_launcher).id(4).build());
        items.add(new MainEntry.Builder().name("Listener").icon(R.mipmap.ic_launcher).id(5).build());
        items.add(new MainEntry.Builder().name("Color").icon(R.mipmap.ic_launcher).id(6).build());
        items.add(new MainEntry.Builder().name("Scroll").icon(R.mipmap.ic_launcher).id(7).build());
        Log.i(TAG, "initData: " + items);
        mAdapter.setItems(items);
    }

    private void initView(View view) {
        mRecycleView = view.findViewById(R.id.main_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        mAdapter = new MainAdapter();
        mRecycleView.setAdapter(mAdapter);
        final Context context = getActivity();
        mAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(Object item) {
                if (!(item instanceof MainEntry))
                    return;

                MainEntry mainEntry = (MainEntry) item;

                switch (mainEntry.mId) {
                    case 1:
                        PhotoActivity.startActivity(context);
                        break;
                    case 2:
                        DrawBitmapActivity.startDrawActivity(context);
                        break;
                    case 3:
                        TestShareActivity.startActivity(context);
                        break;
                    case 4:
                        SessionActivity.startActivity(context);
                        break;
                    case 6:
                        ColorActivity.launchActivity(context);
                        break;
                    case 7:
                        ColorActivity.launchActivity(context);
                        break;
                }
            }
        });
    }
}
