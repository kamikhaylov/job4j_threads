package concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload implements Runnable {
    private final String url;
    private final int speed;

    public FileDownload(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesRead >= speed) {
                    long stop = System.currentTimeMillis();
                    long time = stop - start;
                    if (time < 1000) {
                        Thread.sleep(1000 - time);
                        start = System.currentTimeMillis();
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread fileDowloand = new Thread(new FileDownload(url, speed));
        fileDowloand.start();
        fileDowloand.join();
    }
}