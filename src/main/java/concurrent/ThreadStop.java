package concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            System.out.println("Start... ");
                            Thread.sleep(10000);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().isInterrupted();
                        System.out.println(Thread.currentThread().isInterrupted());
                        System.out.println(Thread.currentThread().getState());
                    }

                }
        );
        thread.start();
        Thread.sleep(10000);
        thread.interrupt();
        thread.join();
    }
}
