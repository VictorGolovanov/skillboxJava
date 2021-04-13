import java.io.IOException;

public class Main {
    private static final String WEBSITE_URL = "https://lenta.ru/";
    private static final String pathToSave = "images";

    public static void main(String[] args) throws IOException {
        ImageSaver imageSaver = new ImageSaver(WEBSITE_URL, pathToSave);
        imageSaver.printSavedImagesNames();
    }
}
