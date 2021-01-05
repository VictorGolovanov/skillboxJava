import java.util.Scanner;

public class Main {

    public static PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] commands = new String[2];
        commands[0] = "LIST";
        commands[1] = "STOP";

        while(true)
        {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals(commands[1])) {
                break;
            }

            if(input.equals(commands[0]))
            {
                // еще не работает
                phoneBook.getAllContacts();
            }

            // если сначала введем имя (сырая версия)
            // еще не написал проверку, есть ли имя в телефонной книге
            if(phoneBook.isLatin(input) || phoneBook.isRussian(input))
            {
                String name = input;
                // и еще проверить, есть ли уже этот контакт в книге
                System.out.println("Введите номер телефона для абонента " + name + ":");
                String phoneInput = scanner.nextLine();
                if(phoneBook.isContainNumber(phoneInput))
                {
                    String phoneNumber = phoneInput;
                    phoneBook.addContact(phoneNumber, name);
                }
                else{
                    System.out.println("Неверный формат ввода");
                }
            }
            // ...
            // а потом примерно также, если сначала введем номер телефона
            // или я иду принципиально не так
            // и вся основная работа по анализу ввода должна быть реализована в методах PhoneBook.java ?
        }
    }
}
