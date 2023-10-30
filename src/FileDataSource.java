import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileDataSource implements DataSource{
    @Override
    public String read(String filePath) {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public void write(String filePath, String content) {
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
