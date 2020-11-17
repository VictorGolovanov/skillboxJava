import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008> 1234 <5678> 8912", "***");
        System.out.println(safe);
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        // так проходит все тесты, за исключением более сложного варианта
        String changedString = text.replaceAll("<.*>", placeholder);
        /*
        так не работает
        Pattern p = Pattern.compile("<.*>");
        Matcher m = p.matcher(text);
        while (m.find())
        {
            changedString = text.replaceAll("<.*>", placeholder);
        }
        */
        return changedString;
    }

}