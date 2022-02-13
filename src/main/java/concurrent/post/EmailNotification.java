package concurrent.post;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(() -> {
            try {
                send(String.format("Notification %s to email %s",
                                user.getUserName(), user.getEmail()),
                        String.format("Add new event to %s", user.getUserName()),
                        user.getEmail());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isShutdown()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void send(String subject, String body, String email) throws InterruptedException {

    }

    public static void main(String[] args) throws InterruptedException {
        EmailNotification notification = new EmailNotification();
        Thread thread = new Thread(
                () -> {
                    User user = new User("User", "user@mail.com");
                    notification.emailTo(user);
                }
        );
        thread.start();
        thread.join();
        notification.close();
    }
}
