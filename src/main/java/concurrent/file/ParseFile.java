package concurrent.file;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    private synchronized String content(Predicate<Integer> predicate) {
        String output = "";
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (predicate.test(data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContent() {
        return content(data -> true);
    }

    public String getContentWithoutUnicode() {
        return content(data -> data < 0x80);
    }
}