import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int newWidth = 300;
    private static final int processorCount = Runtime.getRuntime().availableProcessors();

    private static final String srcFolder = "E:/images/src";
    private static final String dstFolder = "E:/images/dst";

    private static final int FLAG = 1; // 0 - hardResizer; 1 - softResizer

    public static void main(String[] args) {

        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();

        assert files != null;
        List<File[]> listFilesArray = splitFileList(files, processorCount);
        System.out.println("Number of arrays: " + listFilesArray.size());
        System.out.println("****");
        listFilesArray.forEach(l -> System.out.println("Array length: " + l.length));

        long start = System.currentTimeMillis();
        listFilesArray.forEach(f -> new Thread(new ImageResizer(f, newWidth, dstFolder, start, FLAG)).start());
    }

    // справляется с четным и нечетным количеством файлов в исходном массиве
    public static List<File[]> splitFileList(File[] files, int processorCount){
        List<File[]> listFilesArray = new ArrayList<>();

        int srcPos = 0; // отсчет места старта
        for(int i = 0; i < processorCount; i++){
            // проверяем, что мы не на последней итерации
            if(i != processorCount - 1){
                // размер массива - результат целочисленного деления
                File[] file = new File[files.length / processorCount];
                System.arraycopy(files, srcPos, file, 0, file.length);
                listFilesArray.add(file);
                srcPos += (files.length / processorCount); // следующий шаг
            }
            // на последней итерации нам нужно прибавить остаток
            else{
                srcPos = (files.length - (files.length / processorCount) - (files.length % processorCount));
                File[] file = new File[(files.length / processorCount) + (files.length % processorCount)];
                System.arraycopy(files, srcPos, file, 0, file.length);
                listFilesArray.add(file);
            }
        }
        return listFilesArray;
    }
}