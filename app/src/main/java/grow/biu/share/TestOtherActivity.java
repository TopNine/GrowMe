package grow.biu.share;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.flow.grow.R;

public class TestOtherActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_other_activity);
        initView();
    }

    private void initView() {

    }
}
