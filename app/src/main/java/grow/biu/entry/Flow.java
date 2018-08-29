package grow.biu.entry;

public class Flow {
    private String mId;
    private String mName;

    public Flow(String id, String name) {
        mId = id;
        mName = name;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
