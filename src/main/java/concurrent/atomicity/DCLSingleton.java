package concurrent.atomicity;

public final class DCLSingleton {
    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static synchronized DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }
}