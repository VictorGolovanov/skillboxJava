import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {

    final int INDEX_NAME = 0;
    final int INDEX_SURNAME = 1;
    final int INDEX_EMAIL = 2;
    final int INDEX_PHONE = 3;

    final String COMPONENTS_ERROR = "Вы не указали все данные! "
            + "\nФормат ввода данных: команда Имя Фамилия e-mail номер телефона(через +7)."
            + "\nПРИМЕР:"
            + "\nadd Василий Петров vasily.petrov@gmail.com +79215637722";

    final String PHONE_FORMAT_ERROR = "Неверный формат номера телефона! "
            + "\nФормат ввода данных: Всего 11 цифр. начинается с +7"
            + "\nПРИМЕР:"
            + "\n+79215637722";

    final String EMAIL_FORMAT_ERROR = "Неверный формат email! "
            + "\nФормат ввода данных: vasily.petrov@gmail.com";

    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {

        String[] components = data.split("\\s+");
        componentsCheck(components);
        phoneCheck(components);
        emailCheck(components);
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        isStorageEmpty();
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        isStorageEmpty();
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    private void componentsCheck(String[] components){
        if(components.length != 4){
            throw new IllegalArgumentException(COMPONENTS_ERROR);
        }
    }

    private void phoneCheck(String[] components){
        if(!components[INDEX_PHONE].matches("(\\+{1})(7{1})(9{1})(\\d{2})(\\d{3})(\\d{4})")){
            throw new IllegalArgumentException(PHONE_FORMAT_ERROR);
        }
    }

    private void emailCheck(String[] components){
        components[INDEX_EMAIL].toLowerCase();
        // регулярка проверяет по сути на наличие хоть каких-то символов до @,
        // и каких-то символов после @, где должна быть точка и символы после нее
        Pattern pattern = Pattern.compile("[\\w]+(\\.[\\w]+)*@[\\w]+(\\.[\\w]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(components[INDEX_EMAIL]);
        if(!matcher.find()){
            throw new IllegalArgumentException(EMAIL_FORMAT_ERROR);
        }
    }

    private void isStorageEmpty(){
        if(storage.isEmpty()){
            throw new IllegalArgumentException("Нет клиентов в списке");
        }
    }
}