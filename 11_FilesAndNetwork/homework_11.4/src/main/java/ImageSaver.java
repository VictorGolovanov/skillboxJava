import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageSaver {

    private List<String> imagesLinks;

    public ImageSaver(String websiteUrl, String pathToSave) throws IOException {
        getImagesLinks(websiteUrl);
        downloadImages(pathToSave);
    }

    // получаем URL картинок
    private void getImagesLinks(String websiteUrl) throws IOException {
        imagesLinks = new ArrayList<>();
        Document doc = Jsoup.connect(websiteUrl).get();
        Elements media = doc.select("[src]");

        for(Element image : media){
            String link = image.attr("abs:src");
            if(link.contains(".jpg") || link.contains(".png")){
                imagesLinks.add(link);
            }
        }
    }

    // скачиваем картинки
    private void downloadImages(String pathToSave) throws IOException {
        for(String imageUrl : imagesLinks){
            URL url = new URL(imageUrl);
            String name = imageUrl.substring(imageUrl.lastIndexOf("/"));
            FileUtils.copyURLToFile(url, new File(pathToSave + name));
        }
        System.out.println("Копирование прошло успешно!");
    }

    public void printSavedImagesNames(){
        System.out.println("Имена скачанных файлов: ");
        for(String imageUrl : imagesLinks){
            String name = imageUrl.substring(imageUrl.lastIndexOf("/"));
            System.out.println("\t" + name.replaceAll("/", ""));
        }
    }
}
