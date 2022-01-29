package concurrent.console;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r load: \\ ");
                Thread.sleep(500);
                System.out.print("\r load:  | ");
                Thread.sleep(500);
                System.out.print("\r load:   / ");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}