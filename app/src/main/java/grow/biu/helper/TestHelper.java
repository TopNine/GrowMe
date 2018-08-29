package grow.biu.helper;

import grow.biu.entry.Flow;

public class TestHelper {
    private static final String TAG = "TestHelper";

    public static TestHelper mInstance;

    private Flow mFlow;

    public static TestHelper getInstance() {
        if (mInstance == null)
            mInstance = new TestHelper();

        return mInstance;
    }

    public void reset() {
        mFlow = null;
    }

    public void setData(Flow flow) {
        mFlow = flow;
    }
}
