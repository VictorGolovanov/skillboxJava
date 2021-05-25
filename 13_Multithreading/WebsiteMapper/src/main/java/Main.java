import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String URL = "https://skillbox.ru/";
    private static final String PATH = "data/map.txt";

    public static void main(String[] args) {
        WebPage parentPage = new WebPage(URL);
        LinkedHashSet<WebPage> websiteMap = new ForkJoinPool().invoke(new SiteMapper(parentPage));

        try{
            PrintWriter writer = new PrintWriter(PATH);
            for(WebPage page : websiteMap){
                writer.write(page + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}



