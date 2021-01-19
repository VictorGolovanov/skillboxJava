import java.time.LocalTime;
import java.util.*;

public class PhoneBook {

    TreeMap<String, String> phoneBook = new TreeMap<>();

    private static final LocalTime DAY_TIME = LocalTime.of(11,0);
    private static final LocalTime EVENING_TIME = LocalTime.of(18,0);
    private static final LocalTime NIGHT_TIME = LocalTime.of(23,0);

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        boolean isNumber = false;
        boolean isName = false;

        if(isContainNumber(phone))
        {
            isNumber = true;
        }
        if(isRussian(name))
        {
            isName = true;
        }
        else if(isLatin(name))
        {
            isName = true;
        }
        if(isNumber && isName)
        {
            System.out.println("Контакт сохранен! :)");
            phoneBook.put(phone, name); // здесь мы и определяем, что ключ=>номер телефона, а что значение=>имя
        }


    }

    //ok
    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if(!phoneBook.containsKey(phone))
        {
            System.out.println("В базе нет контакта с номером: " + phone);
            return "";
        }
        else
            {
                // как получить ключ по значению?..
                String result = phoneBook.get(phone) + " - " + phone;
                System.out.println(result);
                return result;
            }
    }

    //ok
    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        if(!phoneBook.containsValue(name))
        {
            return new TreeSet<>();
        }
        else
            {
                TreeSet<String> oneContact = new TreeSet<>();
                for(Map.Entry<String, String> entry : phoneBook.entrySet())
                {
                    if(name.equals(entry.getValue()))
                    {
                        oneContact.add(entry.getValue() + " - " + entry.getKey());
                    }
                }
                System.out.println(oneContact);
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
                Map<String, List<String>> contactsByName = new TreeMap<>();
                for(Map.Entry<String, String> entry : phoneBook.entrySet())
                {
                    if(contactsByName.get(entry.getValue()) == null)
                    {
                        contactsByName.put(entry.getValue(), new ArrayList<>());
                    }
                    contactsByName.get(entry.getValue()).add(entry.getKey());
                }
                TreeSet<String> allContacts = new TreeSet<>();
                for(Map.Entry<String, List<String>> entry : contactsByName.entrySet())
                {
                    allContacts.add(entry.getKey() + " - " + String.join(", ", entry.getValue()));
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
        return name.matches("[a-zA-Z\\s]+");
    }

    public boolean isRussian(String name) {
        return name.matches("[а-яА-ЯёЁ\\s]+");
    }
    public String getHelloString(LocalTime curTime)
    {
        if(curTime.isBefore(DAY_TIME))
        {
            return "Доброе утро!";
        }
        else if (curTime.isBefore(EVENING_TIME)) {
            return "Добрый день!";
        }
        else if(curTime.isBefore(NIGHT_TIME)){
            return "Добрый вечер!";
        }
        else{
            return "Доброй ночи!";
        }
    }
}