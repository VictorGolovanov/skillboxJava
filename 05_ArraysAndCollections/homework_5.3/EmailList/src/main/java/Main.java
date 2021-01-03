import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email"; //  а что делать с этой строчкой?
    private static EmailList emailList = new EmailList();


    public static void main(String[] args) {
        // обозначим список возможных команд
        String[] commands = new String[2];
        commands[0] = "LIST";
        commands[1] = "ADD";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] array = input.split(" ");

            // command LIST
            if(array[0].equals(commands[0]))
            {
                emailList.getSortedEmails();
            }

            // command ADD
            if(array[0].equals(commands[1])){
                emailList.add(array[1]);
            }
        }
    }
}
