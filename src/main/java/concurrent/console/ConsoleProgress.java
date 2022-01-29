package concurrent.console;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = {'\\', '|', '/'};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (int i = 0; i < process.length; i++) {
                    System.out.print("\r load: " + process[i]);
                    Thread.sleep(500);
                    if (i == process.length) {
                        i = 0;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}