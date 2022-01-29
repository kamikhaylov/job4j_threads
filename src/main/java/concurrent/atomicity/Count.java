package concurrent.atomicity;

public class Count {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
