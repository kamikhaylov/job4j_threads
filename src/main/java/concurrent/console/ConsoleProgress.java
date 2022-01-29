package concurrent.console;

public class ConsoleProgress implements Runnable {
    private char[] process = {'\\', '|', '/'};

    @Override
    public void run() {
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
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}