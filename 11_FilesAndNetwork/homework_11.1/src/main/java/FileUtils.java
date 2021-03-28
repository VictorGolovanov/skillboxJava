import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

    private static final long Kb = 1024;
    private static final long Mb = Kb * Kb;
    private static final long Gb = 1024 * Mb;
    private static final long Tb = 1024 * Gb;

    static final String WRONG_PATH_MESSAGE = "Путь не существует или указан неверно!";
    static final String FILE_NOT_DIRECTORY_MESSAGE = "Указан файл, а не папка!";

    public static long calculateFolderSize(String path) {
        File directory = new File(path);
        // проверяем, существует ли путь
        directoryCheck(directory);
        // указан ли путь к файлу или папке
        fileCheck(directory);

        long size = 0;
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            size = walk
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            return 0;
                        }
                    })
                    .sum();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return size;
    }

    private static void directoryCheck(File directory){
        if(!directory.exists()){
            throw new IllegalArgumentException(WRONG_PATH_MESSAGE);
        }
    }

    private static void fileCheck(File directory){
        if(directory.isFile()){
            throw new IllegalArgumentException(FILE_NOT_DIRECTORY_MESSAGE);
        }
    }
}
