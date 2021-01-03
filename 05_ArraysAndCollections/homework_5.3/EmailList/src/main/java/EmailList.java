import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {

    TreeSet<String> emailList = new TreeSet<>();

    public void add(String email) {
        // регулярка проверяет по сути на наличие хоть каких-то символов до @,
        // и каких-то символов после @, где должна быть точка и символы после нее
        email = email.toLowerCase();
        Pattern pattern = Pattern.compile("[\\w]+(\\.[\\w]+)*@[\\w]+(\\.[\\w]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if(matcher.find())
        {
            emailList.add(email);
        }
        else{
            System.out.println("Неверный формат email");
        }

    }

    public List<String> getSortedEmails() { // Сначала заменил на TreeSet. но тесты не проходит, хотя содержание было идентично
        List sortedEmails = new ArrayList(emailList); // преобразуем в List
        sortedEmails.forEach(System.out::println);

        return sortedEmails;
    }

}
