package grow.utils;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private static final String TAG = "Grow_ActivityCollector";

    private static List<Activity> mActivities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        Log.i(TAG, "addActivity: " + activity);
        mActivities.add(activity);
    }

    public static void finishActivity(Activity activity) {
        mActivities.remove(activity);
    }

    public static void finishAll() {
        Log.e(TAG, "finishAll: mActivities== " + mActivities.toString());
        for (Activity activity : mActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
