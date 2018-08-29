package grow.biu.entry;

public class MainEntry {
    public int mId;
    public String mName;
    public int mIcon;

    private MainEntry(Builder builder) {
        this.mName = builder.mName;
        this.mIcon = builder.mIcon;
        this.mId = builder.mId;
    }

    public static class Builder {
        private String mName;
        private int mIcon;
        private int mId;

        public Builder name(String name) {
            this.mName = name;
            return this;
        }

        public Builder icon(int icon) {
            this.mIcon = icon;
            return this;
        }

        public Builder id(int id) {
            this.mId = id;
            return this;
        }

        public MainEntry builder() {
            return new MainEntry(this);
        }
    }

    @Override
    public String toString() {
        return "MainEntry{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mIcon=" + mIcon +
                '}';
    }
}
