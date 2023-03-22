package Data;

import java.io.IOException;

public class FileWriter {

    public static void writeFile(String content, String fileName) {

        java.io.FileWriter file;
        try {
            file = new java.io.FileWriter(fileName, false);
            file.write(content);
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
