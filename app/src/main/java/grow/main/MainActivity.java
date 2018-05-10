package grow.main;

import android.app.Activity;
import android.os.Bundle;

import com.flow.grow.R;

public class MainActivity extends Activity {
    private static final String TAG = "UI.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_home_activity);

    }


}
