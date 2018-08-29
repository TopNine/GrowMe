package grow.biu.session;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flow.grow.R;

import java.util.ArrayList;
import java.util.List;

import grow.biu.listener.OnItemClickListener;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewHolder> {
    private static final String TAG = "MainAdapter";

    private OnItemClickListener mListener;
    private List<TransItem> mItems = new ArrayList<>();

    public void setItems(List<TransItem> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void updateProgress(TransItem item) {
        if (mItems.contains(item)) {
            int index = mItems.indexOf(item);
            notifyItemChanged(index, item);
        }
    }

    public void setOnItemClick(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public SessionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_item, parent, false);
        return new SessionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position) {
        TransItem item = mItems.get(position);
        holder.bindModel(item);
    }

    @Override
    public void onBindViewHolder(SessionViewHolder holder, int position, List<Object> payloads) {
        Log.i(TAG, "-------payloads: " + payloads.size());
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            TransItem item = (TransItem) payloads.get(0);
            updateProgress(holder, item);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class SessionViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public ProgressBar mProgressView;

        public SessionViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            nameView = (TextView) itemView.findViewById(R.id.session_item_name);
            mProgressView = (ProgressBar) itemView.findViewById(R.id.session_progress_bar);
        }

        public void bindModel(final TransItem item) {
            if (item == null)
                return;

            nameView.setText(item.mName);
            mProgressView.setProgress(item.mProgress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItemClick(item);
                }
            });
        }
    }

    private void updateProgress(SessionViewHolder holder, TransItem item) {
        holder.mProgressView.setProgress(item.mProgress);
    }
}
