import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileUtils {

    static final String WRONG_PATH_MESSAGE = "Путь не существует или указан неверно";
    static final String FILE_NOT_DIRECTORY_MESSAGE = "Указан файл, а не папка";

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {

        //методы для проверки чуть поменял
        directoryCheck(sourceDirectory);
        directoryCheck(destinationDirectory);

        fileCheck(sourceDirectory);
        fileCheck(destinationDirectory);

        Path source = Paths.get(sourceDirectory);
        Path target = Paths.get(destinationDirectory);

        try{
            Files.walk(source)
                    .forEach(p -> {
                        try {
                            Path targetPath = target.resolve(source.relativize(p));
                            Files.copy(p, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("Копирование прошло успешно!");
    }

    private static void directoryCheck(String directory){
        File dir = new File(directory);
        if(!dir.exists()){
            throw new IllegalArgumentException(WRONG_PATH_MESSAGE + ": " + directory);
        }
    }

    private static void fileCheck(String directory){
        File dir = new File(directory);
        if(dir.isFile()){
            throw new IllegalArgumentException(FILE_NOT_DIRECTORY_MESSAGE + ": " + directory);
        }
    }

}
