import java.io.IOException;

public class Main {

    public static final String LINK = "https://www.moscowmap.ru/metro.html#lines";
    public static final String PATH = "src/main/resources/mosMetroMap.json";

    public static void main(String[] args) throws IOException {
        MetroParser metroParser = new MetroParser(LINK, PATH);
        System.out.println("OK");

        try{
            metroParser.printResult();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
