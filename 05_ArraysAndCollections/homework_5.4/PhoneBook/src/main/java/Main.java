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
            String input = scanner.nextLine();
            if (input.equals(commands[1])) {
                break;
            }

            if(input.equals(commands[0]))
            {
                PhoneBook.getAllContacts();
                //continue;
            }

            if((PhoneBook.isLatin(input) || PhoneBook.isRussian(input)) && !input.equals(commands[0])) // if name
            {
                // если сначала введем имя (сырая версия)
                // проверяем, есть ли в книге контакт
                if(PhoneBook.phoneBook.containsKey(input)) // выглядит некрасиво, подумаю, как исправить
                {
                    PhoneBook.getPhonesByName(input);
                }
                else if(PhoneBook.isLatin(input) || PhoneBook.isRussian(input))
                {
                    String name = input;
                    // и еще проверить, есть ли уже этот контакт в книге
                    System.out.println("Введите номер телефона для абонента " + name + ":");
                    String phoneInput = scanner.nextLine();
                    if(PhoneBook.isContainNumber(phoneInput))
                    {
                        String phoneNumber = phoneInput;
                        PhoneBook.addContact(phoneNumber, name);
                    }
                    else{
                        System.out.println("Неверный формат ввода");
                    }
                }
            }

            if((PhoneBook.isContainNumber(input)) && !input.equals(commands[0])) // if numbers
            {
                //todo
            }




        }
    }
}
