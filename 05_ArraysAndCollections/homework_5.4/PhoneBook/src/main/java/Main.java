import java.util.Scanner;

public class Main {

    public static PhoneBook PhoneBook = new PhoneBook();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] commands = new String[2];
        commands[0] = "LIST";
        commands[1] = "STOP";

        while(true)
        {
            System.out.println("Введите номер, имя или команду:");
            System.out.println("Формат номера телефона: 79991234567");
            String input = scanner.nextLine();
            if (input.equals(commands[1])) {
                break;
            }

            // если сначала введем имя
            if((PhoneBook.isLatin(input) || PhoneBook.isRussian(input)) && !input.equals(commands[0])) // if name
            {
                // проверяем, есть ли в книге контакт
                if(PhoneBook.phoneBook.containsValue(input)) //
                {
                    PhoneBook.getPhonesByName(input);
                }
                else if(PhoneBook.isLatin(input) || PhoneBook.isRussian(input))
                {
                    // и еще проверить, есть ли уже этот контакт в книге
                    System.out.println("Введите номер телефона для абонента " + input + ":");
                    String phoneInput = scanner.nextLine();
                    if(PhoneBook.isContainNumber(phoneInput))
                    {
                        PhoneBook.addContact(phoneInput, input);
                    }
                    else{
                        System.out.println("Неверный формат ввода");
                    }
                }
            }

            // если введем номер телефона
            if((PhoneBook.isContainNumber(input)) && !input.equals(commands[0])) // if numbers
            {
                // проверяем, есть ли в книге контакт
                if(PhoneBook.phoneBook.containsKey(input)) //
                {
                    PhoneBook.getNameByPhone(input);
                }
                else
                {
                    System.out.println("У вас не сохранен контакт с номером: " + input + ". Введите имя, чтобы записать контакт.");
                    String nameInput = scanner.nextLine();
                    if(PhoneBook.isRussian(nameInput) || PhoneBook.isLatin(nameInput))
                    {
                        PhoneBook.addContact(input, nameInput);
                    }
                }
            }

            // LIST
            if(input.equals(commands[0]))
            {
                PhoneBook.getAllContacts();
            }

            // for bad input
            else if (!PhoneBook.isContainNumber(input) && !PhoneBook.isLatin(input) && !PhoneBook.isRussian(input) && !input.equals(commands[0])){
                System.out.println("Неверный формат ввода!");
            }
        }
    }
}
