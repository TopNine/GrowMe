package grow.biu.session;

public class TransItem {
    public int id;
    public String mName;
    public int mProgress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransItem item = (TransItem) o;

        return id == item.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
