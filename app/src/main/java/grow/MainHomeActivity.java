package grow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flow.grow.R;

import grow.base.BaseActivity;
import grow.firstcode.anim.AnimActivity;
import grow.firstcode.binarytree.BinaryTreeActivity;
import grow.firstcode.seekbar.SeekBarActivity;

public class MainHomeActivity extends BaseActivity {
    private static final String TAG = "MainHomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        initView();
        testMath();
    }

    private void initView() {
        Button binaryTreeBtn = findViewById(R.id.btn_binary_tree);
        binaryTreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BinaryTreeActivity.launchActivity(MainHomeActivity.this);
            }
        });

        Button animBtn = findViewById(R.id.btn_anim);
        animBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimActivity.launchActivity(MainHomeActivity.this);
            }
        });

        Button seekBarBtn = findViewById(R.id.btn_seek_bar);
        seekBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBarActivity.launchActivity(MainHomeActivity.this);
            }
        });
    }

    private String ab = "10";
    private String de;

    private Book mBook;
    private Book mBook2;

    private void testMath() {
        String cd = ab;
        de = ab;
        ab = "20";
        Log.d(TAG, "testMath: ab: " + ab);
        Log.d(TAG, "testMath: cd: " + cd);
        Log.d(TAG, "testMath: de: " + de);

        mBook = new Book();
        mBook.setPage(10);
        mBook.setPrice("100");

        Book book1 = mBook;
        mBook2 = mBook;
        mBook.setPage(20);
        Log.i(TAG, "testMath: book1: " + book1);
        Log.i(TAG, "testMath: book2: " + mBook2);
    }

    private class Book {
        String price;
        int page;

        public String getPrice() {
            return price == null ? "" : price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "price='" + price + '\'' +
                    ", page=" + page +
                    '}';
        }
    }
}
