package grow.su;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class BaseDialogFragment extends DialogFragment {
    private static final String TAG = "BaseDialogFragment";

    private boolean mFullScreen = true;
    protected String mTag = null;

    public interface ConfirmListener {
        void onOk();

        void onCancel();
    }

    private ConfirmListener mListener;

    public void setConfirmListener(ConfirmListener listener) {
        this.mListener = listener;
    }

    public void onOk() {
        if (mListener != null)
            mListener.onOk();
    }

    public void onCancel() {
        if (mListener != null)
            mListener.onCancel();
    }

    public void setFullScreen(boolean value) {
        mFullScreen = value;
    }

    @Override
    public void onStart() {
        try {
            super.onStart();
        } catch (Exception e) {
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
        } else {
            setShowsDialog(false);
        }
        // http://stackoverflow.com/questions/12265611/dialogfragment-nullpointerexception-support-library
        // Tells DialogFragment to not use the fragment as a dialog, and so won't try to use mDialog
        try {
            super.onActivityCreated(savedInstanceState);
        } catch (NullPointerException e) {
        }

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            mTag = tag;
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            Log.e(TAG, "show dialog exception ", e);
        }
    }

    public boolean safeShow(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
            return true;
        } catch (IllegalStateException e) {
            Log.e(TAG, "safe show dialog exception ", e);
            return false;
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
        }
    }
}
