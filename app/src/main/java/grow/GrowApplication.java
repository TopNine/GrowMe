package grow;

import android.app.Application;

import grow.utils.Logger;

public class GrowApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        Logger.instance(getApplicationContext());
    }
}
