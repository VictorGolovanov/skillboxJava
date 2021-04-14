import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        imagesLinks = media.stream()
                .map(m -> m.attr("abs:src"))
                .filter(m -> m.contains(".jpg") || m.contains(".png") || m.contains(".jpeg"))
                .collect(Collectors.toList());
    }

    // скачиваем картинки
    private void downloadImages(String pathToSave) {
        imagesLinks.stream()
                .forEach(i -> { // i -> элемент списка imagesLinks
                    try {
                        FileUtils.copyURLToFile(new URL(i), new File(pathToSave + i.substring(i.lastIndexOf("/"))));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
        System.out.println("Копирование прошло успешно!");
    }

    public void printSavedImagesNames(){
        System.out.println("Имена скачанных файлов: ");
        imagesLinks.stream()
                .map(i -> i.substring(i.lastIndexOf("/")).replaceAll("/", ""))
                .forEach(i -> System.out.println("=> " + i));
    }
}
