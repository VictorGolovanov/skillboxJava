import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws Exception {
        String[] paths = {
                "res/numbers0.txt",
                "res/numbers1.txt",
                "res/numbers2.txt",
                "res/numbers3.txt"
        };

        int processorCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processorCount);

        // Executor start
        System.out.println("Executor started");
        for(int i = 0; i < paths.length; i++){
            long start = System.currentTimeMillis();
            executor.submit(new NumGenerator(paths[i], start));
            System.out.println(System.currentTimeMillis());
        }
        executor.shutdown();
    }
}