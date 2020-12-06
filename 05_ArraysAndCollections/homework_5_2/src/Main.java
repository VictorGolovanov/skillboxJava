// Если я правильно понимаю, то программа имитирует работу консоли
// если введена отсутсвующая команда, то ничего не происходит

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // обозначим список возможных команд
        String[] commands = new String[4];
        commands[0] = "LIST";
        commands[1] = "ADD";
        commands[2] = "EDIT";
        commands[3] = "DELETE";

        ArrayList<String> todoList = new ArrayList<>();

        // Это просто приветственное сообщение, разъясняющее что происходит
        String progName = "Планировщик-из-консоли";
        System.out.println("Вас приветствует " + progName + "!");
        System.out.println("Это простая программа, которая поможет Вам управлять Вашими делами." +
                "\nОзнакомьтесь со списком возможных команд:");
        System.out.println("\t" + commands[0] + " - Выводит список Ваших дел." +
                "\n\t" + commands[1] + " - Добавляет дело в список. Можно указывать индекс. " +
                "\n\t" + commands[2] + " - Редактирует существующее дело. Указывайте индекс дела." +
                "\n\t" + commands[3] + " - Удаляет существующее дело. Указывайте индекс дела." +
                // это дописал позднее, так как подумал, что надо выходить из программы
                "\n\tSTOP - специальная команда, которая завершает работу программы и удаляет все данные, так как я еще не умею их хранить.");

        System.out.println();

        // основной код ниже
        Scanner scanner = new Scanner(System.in);
        // Подобная конструкция была в задании про форматирование номера телефона.
        // позволяет постоянно считывать информацию с консоли, пока не ввести STOP
        while (true) {
            System.out.println("Введите команду:");
            String consoleCommand = scanner.nextLine();
            if (consoleCommand.equals("STOP")) {
                System.out.println("Спасибо за внимание!");
                break;
            }

            // ***********************************************
            // команда LIST
            if (consoleCommand.equals(commands[0])) {
                // На случай, если команда LIST вызывается до того, как добавили что-то в список
                if (todoList.size() == 0) {
                    System.out.println("Вы еще не добавили дела в список!");
                } else {
                    // если дела все-таки есть.
                    System.out.println("Список добавленных дел:");
                    for(int i = 0; i < todoList.size(); i++)
                    {
                        System.out.println("\t" + i + " " + todoList.get(i));
                    }
                }
            }

            // ***********************************************
            // чтобы можно было проверить, соответствует ли первое слово допустимой команде - разделил строку
            String[] array = consoleCommand.split(" ");

            // ***********************************************
            // команда ADD (+ num)
            if(array[0].equals(commands[1])){ // тут проверяем введена ли команда ADD
                StringBuffer toWrite = new StringBuffer(); // значение, которое мы будем сохранять и записывать в список

                // проверим, является ли второй элемент цифрой и частью команды ADD
                boolean isNumber = false;
                for(char number : array[1].toCharArray())
                {
                    if(Character.isDigit(number))
                    {
                        isNumber = true;
                    }
                }

                if(isNumber){
                    int num = Integer.parseInt(array[1]);

                    for(int i = 2; i < array.length; i++) // пропускаем команду и число
                    {
                        // надо собрать строку заново
                        toWrite.append(array[i] + " ");
                    }


                    // проверка (учитывает и ввод отрицательных чисел)
                    if(num < todoList.size())
                    {
                        if(num < 0)
                        {
                            System.out.println("Отрицательный индекс не учитывается программой");
                            todoList.add(toWrite.toString().trim());
                        }
                        else{
                            todoList.add(Math.abs(num), toWrite.toString().trim());
                        }
                    }
                    else {
                        todoList.add(toWrite.toString().trim());
                    }
                }
                else{
                    for(int i = 1; i < array.length; i++)
                    {
                        // надо собрать строку заново
                        toWrite.append(array[i] + " ");
                    }
                    todoList.add(toWrite.toString().trim());
                }
            }

            //***********************************************
            // Команда EDIT
            if(array[0].equals(commands[2])) { // тут проверяем введена ли команда EDIT
                StringBuffer toWrite = new StringBuffer();

                // проверим, является ли второй элемент цифрой и частью команды EDIT
                // эта часть аналогична как и в команде ADD
                boolean isNumber = false;
                for(char number : array[1].toCharArray())
                {
                    if(Character.isDigit(number))
                    {
                        isNumber = true;
                    }
                }

                if(isNumber)
                {
                    int num = Integer.parseInt(array[1]);

                    for(int i = 2; i < array.length; i++) // пропускаем команду и число
                    {
                        // надо собрать строку заново
                        toWrite.append(array[i] + " ");
                    }

                    // проверка (учитывает и ввод отрицательных чисел)
                    if(num < todoList.size())
                    {
                        if(num < 0)
                        {
                            // в случае отрицательного указателя, мы просто предупредим пользователя, но добавлять ничего не будем
                            System.out.println("Отрицательный индекс не учитывается программой");
                        }
                        else{
                            // если же ввод удовлетворяет нашим требованиям, то меняем элемент в списке с помощью set()
                            todoList.set(Math.abs(num), toWrite.toString().trim());
                        }
                    }
                    else{
                        System.out.println("У вас не записано дело № " + num + ", чтобы его редактировать!");
                    }
                }
            }

            //***********************************************
            // Команда DELETE
            if(array[0].equals(commands[3])) { // тут проверяем введена ли команда DELETE
                StringBuffer toWrite = new StringBuffer(); // значение, которое мы будем сохранять и записывать в список

                // проверим, является ли второй элемент цифрой и частью команды DELETE
                // эта часть аналогична как и в команде ADD
                boolean isNumber = false;
                for(char number : array[1].toCharArray())
                {
                    if(Character.isDigit(number))
                    {
                        isNumber = true;
                    }
                }

                if(isNumber)
                {
                    int num = Integer.parseInt(array[1]);

                    for(int i = 2; i < array.length; i++) // пропускаем команду и число
                    {
                        // надо собрать строку заново
                        toWrite.append(array[i] + " ");
                    }

                    // проверка (учитывает и ввод отрицательных чисел)
                    if(num < todoList.size())
                    {
                        if(num < 0)
                        {
                            // в случае отрицательного указателя, мы просто предупредим пользователя, но добавлять ничего не будем
                            System.out.println("Отрицательный индекс не учитывается программой");
                        }
                        else{
                            // если же ввод удовлетворяет нашим требованиям, то удаляем элемент по указанному индексу с помощью remove()
                            todoList.remove(Math.abs(num));
                        }
                    }
                    else{
                        System.out.println("У вас не записано дело № " + num + ", чтобы его удалять!");
                    }
                }
            }
        }
    }
}
