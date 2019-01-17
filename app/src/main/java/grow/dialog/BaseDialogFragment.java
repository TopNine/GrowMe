package grow.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.flow.grow.R;

public class BaseDialogFragment extends DialogFragment {
    private static final String TAG = "Flow.BaseDialogFragment";

    private static final String KEY_MSG = "msg";
    private Bundle mArgs;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.base_dialog_style);
            window.setGravity(Gravity.BOTTOM);
        }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            Log.i(TAG, "onStart width1111: " + width);
            width = getResources().getDisplayMetrics().widthPixels;
            Log.i(TAG, "onStart width222: " + width);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mArgs = getArguments();
        updateMsgView(view);
    }

    private void updateMsgView(View view) {
        TextView msgView = view.findViewById(R.id.dialog_msg);
        String msg = mArgs.getString(KEY_MSG);
        Log.i(TAG, "updateMsgView: " + msg);
        msgView.setText(TextUtils.isEmpty(msg) ? "none" : msg);
    }
}
