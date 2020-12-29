import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
        // TODO: написать консольное приложение для работы со списком дел todoList

        // обозначим список возможных команд
        String[] commands = new String[5];
        commands[0] = "LIST";
        commands[1] = "ADD";
        commands[2] = "EDIT";
        commands[3] = "DELETE";
        commands[4] = "STOP";

        // для работы с вводом из консоли
        Scanner scanner = new Scanner(System.in);

        // повторяем запрос, пока не надоест :)
        while(true){
            System.out.println("Введите команду:");
            String consoleCommand = scanner.nextLine();
            if (consoleCommand.equals(commands[4])) {
                System.out.println("Спасибо за внимание!");
                break;
                //
            }

            String[] array = consoleCommand.split(" ");


            // command LIST
            if(array[0].equals(commands[0]))
            {
                todoList.getTodos();
            }

            // command ADD (with and without index)
            if(array[0].equals(commands[1])) // есть ли нужная команда
            {
                // есть ли указание на индекс в команде
                if(todoList.isNumberInCommand(consoleCommand))
                {
                    // если нулевой и первый элементы - команда и индекс, то само дело начинается с элемента № 2
                    String ourDeal = (todoList.stringConstructor(2, consoleCommand)).toString();
                    int num = Integer.parseInt(array[1]);
                    todoList.add(num, ourDeal);
                }
                // если нет указания на индекс, то просто добавляем дело в конец списка
                else{
                    // т.к. нулевой элемент - это команда, то само дело начинается с элемента № 1
                    String ourDeal = (todoList.stringConstructor(1, consoleCommand)).toString();
                    todoList.add(ourDeal);
                }
            }

            // command EDIT
            if(array[0].equals(commands[2])) // есть ли нужная команда
            {
                // есть ли указание на индекс в команде
                if(todoList.isNumberInCommand(consoleCommand))
                {
                    // если нулевой и первый элементы - команда и индекс, то само дело начинается с элемента № 2
                    String ourDeal = (todoList.stringConstructor(2, consoleCommand)).toString();
                    int num = Integer.parseInt(array[1]);
                    todoList.edit(ourDeal, num);
                }
            }

            // command DELETE
            if(array[0].equals(commands[3])) // есть ли нужная команда
            {
                // есть ли указание на индекс в команде
                if(todoList.isNumberInCommand(consoleCommand))
                {
                    // если нулевой и первый элементы - команда и индекс, то само дело начинается с элемента № 2
                    String ourDeal = (todoList.stringConstructor(2, consoleCommand)).toString();
                    int num = Integer.parseInt(array[1]);
                    todoList.delete(num);
                }
            }
        }
    }
}
