import java.util.Map;
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

        if(isContainNumber(phone))
                //phone.matches("(7{1})(9{1})(\\d{2})(\\d{3})(\\d{4})"))
        {
            isNumber = true;
            System.out.println("ok! - phone is phone :)");
        }
        if(isRussian(name))
                //name.matches("[а-яА-ЯёЁ]+"))
        {
            isName = true;
            System.out.println("ok! - name has russian letters");
        }
        else if(isLatin(name))
                //name.matches("[a-zA-Z]+"))
        {
            isName = true;
            System.out.println("ok! - name has latin letters");
        }
        if(isNumber && isName)
        {
            System.out.println("Contact added! :)");
            phoneBook.put(name, phone);
        }


    }

    //not ok
    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if(!phoneBook.containsValue(phone))
        {
            System.out.println("В базе нет контакта с номером: " + phone);
            return "";
        }
        else
            {
                // как получить ключ по значению?..
                return "";
            }
    }

    //ok
    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        if(!phoneBook.containsKey(name))
        {
            return new TreeSet<>();
        }
        else
            {
                TreeSet<String> oneContact = new TreeSet<>();
                oneContact.add(name + " - " + phoneBook.get(name));
                oneContact.forEach(System.out::println);
                return oneContact;
            }
    }

    //ok
    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        if(phoneBook.isEmpty())
        {
            return new TreeSet<>();
        }
        else
            {
                TreeSet<String> allContacts = new TreeSet<>();
                for(Map.Entry<String, String> entry : phoneBook.entrySet())
                {
                    allContacts.add(entry.getKey() + " - " + entry.getValue());
                }
                allContacts.forEach(System.out::println);
                return allContacts;
            }
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