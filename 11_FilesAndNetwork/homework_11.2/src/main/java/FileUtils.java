import java.io.File;

public class FileUtils {

    static final String WRONG_PATH_MESSAGE = "Путь не существует или указан неверно";
    static final String FILE_NOT_DIRECTORY_MESSAGE = "Указан файл, а не папка";

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        // TODO: write code copy content of sourceDirectory to destinationDirectory

        File sourceDir = new File(sourceDirectory);
        File destinationDir = new File(destinationDirectory);

        directoryCheck(sourceDir);
        directoryCheck(destinationDir);

        fileCheck(sourceDir);
        fileCheck(destinationDir);

        try{
            org.apache.commons.io.FileUtils.copyDirectory(sourceDir, destinationDir);
            System.out.println("Копирование прошло успешно!");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static void directoryCheck(File directory){
        if(!directory.exists()){
            throw new IllegalArgumentException(WRONG_PATH_MESSAGE + ": " + directory);
        }
    }

    private static void fileCheck(File directory){
        if(directory.isFile()){
            throw new IllegalArgumentException(FILE_NOT_DIRECTORY_MESSAGE + ": " + directory);
        }
    }

}
