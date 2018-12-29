package grow.firstcode.binarytree;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flow.grow.R;

import java.util.Random;

import grow.base.BaseActivity;

public class BinaryTreeActivity extends BaseActivity {
    private static final String TAG = "BinaryTreeActivity";

    public static final int TEST_1 = 1;
    public static final int TEST_2 = 2;
    public static final int TEST_3 = 3;
    public static final int TEST_4 = 4;
    public static final int TEST_5 = 5;

    private TextView mQuestionTV;
    private String mQuestionText;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public static void launchActivity(Context context) {
        Log.d(TAG, "launchActivity: ");
        Intent intent = new Intent(context, BinaryTreeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_tree);
        initView();
        initHandle();
        loadData();
    }

    private void initHandle() {
        mHandlerThread = new HandlerThread(TAG);
        mHandlerThread.start();
        mHandler = new TestHandle(mHandlerThread.getLooper());
    }

    private void initView() {
        mQuestionTV = findViewById(R.id.tv_test_question);
        Button binaryBtn = findViewById(R.id.btn_binary_tree);
        binaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBinaryTree();
                sendMessage(TEST_5, 0);
            }
        });

        Button sendMsgBtn = findViewById(R.id.btn_send_msg);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testSend();
            }
        });
        Button msgBtn = findViewById(R.id.btn_msg_quit);
        msgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgQuit();
            }
        });
    }

    private void doBinaryTree() {

    }

    private void loadData() {
        mQuestionText = "";
    }

    private void testSend() {
        Random random = new Random();
        int what = random.nextInt(3) + 1;
        Log.i(TAG, "testSend: " + what);
        sendMessage(what, 0);
    }

    private void sendMessage(int what, long delay) {
        if (mHandler == null || mHandlerThread == null || !mHandlerThread.isAlive()) {
            Log.i(TAG, "sendMessage error: ");
            return;
        }

        Message message = Message.obtain();
        message.what = what;
        mHandler.sendMessageDelayed(message, delay);
    }

    private void msgQuit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mHandlerThread.getLooper().getQueue();
        }
        mHandlerThread.quit();
        Log.i(TAG, "msgQuit: ");
    }

    private void updateMsg(final String msg) {
        mQuestionTV.post(new Runnable() {
            @Override
            public void run() {
                mQuestionTV.setText(msg);
            }
        });
    }

    private class TestHandle extends Handler {
        public TestHandle(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "current thread: " + Thread.currentThread());
//            try {
//                if (msg.what == 5)
//                    Thread.sleep(10000);
//
//                Thread.sleep(3000);
            String text = "";
            switch (msg.what) {
                case TEST_1:
                    text = "what_1";
                    break;
                case TEST_2:
                    text = "what_2";
                    break;
                case TEST_3:
                    text = "what_3";
                    break;
                case TEST_4:
                    text = "what_4";
                    break;
                case TEST_5:
                    text = "what_5";
                    break;
            }
            Log.i(TAG, "handleMessage: " + text);
            updateMsg(text);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            super.handleMessage(msg);
        }
    }
}

