import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {

    TreeMap<String, String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        boolean isNumber = false;
        boolean isName = false;

        if(phone.matches("(7{1})(9{1})(\\d{2})(\\d{3})(\\d{4})"))
        {
            isNumber = true;
            System.out.println("ok!");
        }
        if(name.matches("[а-яА-ЯёЁ]+"))
        {
            isName = true;
            System.out.println("ok!");
        }
        else if(name.matches("[a-zA-Z]+"))
        {
            isName = true;
            System.out.println("ok!");
        }
        if(isNumber && isName)
        {
            phoneBook.put(name, phone);
        }


    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        return new TreeSet<>();
    }

    // для проверки ввода
    public boolean isContainNumber(String toCheck)
    {
        // исходим из формата 79998761234
        return toCheck.matches("(7{1})(9{1})(\\d{2})(\\d{3})(\\d{4})");
    }

    public boolean isLatin(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public boolean isRussian(String name) {
        return name.matches("[а-яА-ЯёЁ]+");
    }

}