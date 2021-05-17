import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int newWidth = 300;
    private static final int processorCount = Runtime.getRuntime().availableProcessors();

    private static final String srcFolder = "E:/images/src";
    private static final String dstFolder = "E:/images/dst";

    public static void main(String[] args) {

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        ExecutorService executor = Executors.newFixedThreadPool(processorCount);
        long start = System.currentTimeMillis();
        assert files != null;
        for(File file : files){
            executor.submit(new SoftImageResizer(file, newWidth, dstFolder, start));
        }
        executor.shutdown();
    }
}