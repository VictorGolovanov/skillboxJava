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
        String changedString = text.replaceAll("<(.*?)>", placeholder); // честно говоря, просто поставил скобки и получилось
        // прокомментируйте пожалуйста результат

        return changedString;
    }

}