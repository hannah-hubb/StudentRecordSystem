import java.io.File;
import java.util.Date;

public class FileManager {
    public static void checkFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            System.out.println("File Name: " + file.getName());
            System.out.println("Path: " + file.getAbsolutePath());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Last Modified: " + new Date(file.lastModified()));
        } catch (Exception e) {
            System.out.println("Error handling file: " + e.getMessage());
        }
    }
}
