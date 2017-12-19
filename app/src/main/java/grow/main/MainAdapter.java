package grow.main;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.List;

import grow.entry.MainEntry;
import grow.glide.ImageLoadHelper;
import grow.listener.OnItemClickListener;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private static final String TAG = "MainAdapter";

    private OnItemClickListener mListener;
    private List<MainEntry> mItems = new ArrayList<>();

    public void setItems(List<MainEntry> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        MainEntry item = mItems.get(position);
        holder.bindModel(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public ImageView iconView;

        public MainViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            iconView = (ImageView) itemView.findViewById(R.id.item_icon);
            nameView = (TextView) itemView.findViewById(R.id.item_name);
        }

        public void bindModel(final MainEntry item) {
            if (item == null)
                return;

            ImageLoadHelper.loadRes(itemView.getContext(), item.mIcon, iconView);
            nameView.setText(item.mName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItemClick(item);
                }
            });
        }
    }
}
