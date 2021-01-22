import java.util.*;

public class Main {
    //public static CoolNumbers coolNumbers = new CoolNumbers();
    public static void main(String[] args) {


        System.out.println("Здравствуйте!");
        System.out.println("Подождите пожалуйста, идут вычисления... :)");

        // наверно лучше все-таки создавать в начале, чтобы потом не ждать, да и так точно время создания не помещает поиску
        List<String> coolNumbersData = CoolNumbers.generateCoolNumbers();
        Collections.sort(coolNumbersData);
        HashSet<String> hashSet = new HashSet<>(coolNumbersData);
        TreeSet<String> treeSet = new TreeSet<>(coolNumbersData);

        System.out.println("Количество номеров, сохраненных в базе данных: " + coolNumbersData.size() + ".");
        while(true)
        {
            // для тестов и замера времени поиска
            /*System.out.println("Первый элемент: " + coolNumbersData.get(0));
            System.out.println("Средний элемент: " + coolNumbersData.get(2770079));
            System.out.println("Последний элемент: " + coolNumbersData.get(5540159));*/

            System.out.println("Какой номер вы хотите найти?");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("STOP")) {
                break;
            }

            if(CoolNumbers.isCarNumberInString(input))
            {
                long tBrute = System.nanoTime();
                if(CoolNumbers.bruteForceSearchInList(coolNumbersData, input))
                {
                    System.out.println("Поиск перебором: номер найден, поиск занял: " + (System.nanoTime() - tBrute) + " нс.");
                }
                else{
                    System.out.println("Поиск перебором: номер не найден, поиск занял: " + (System.nanoTime() - tBrute) + " нс.");
                }

                long timeBinary = System.nanoTime();
                if(CoolNumbers.binarySearchInList(coolNumbersData, input))
                {
                    System.out.println("Бинарный поиск: номер найден, поиск занял: " + (System.nanoTime() - timeBinary) + " нс.");
                }
                else{
                    System.out.println("Бинарный поиск: номер не найден, поиск занял: " + (System.nanoTime() - timeBinary) + " нс.");
                }

                long timeHash = System.nanoTime();
                if(CoolNumbers.searchInHashSet(hashSet, input))
                {
                    System.out.println("Поиск в HashSet: номер найден, поиск занял: " + (System.nanoTime() - timeHash) + " нс.");
                }
                else{
                    System.out.println("Поиск в HashSet: номер не найден, поиск занял: " + (System.nanoTime() - timeHash) + " нс.");
                }

                long timeTree = System.nanoTime();
                if(CoolNumbers.searchInTreeSet(treeSet, input))
                {
                    System.out.println("Поиск в TreeSet: номер найден, поиск занял: " + (System.nanoTime() - timeTree) + " нс.");
                }
                else{
                    System.out.println("Поиск в TreeSet: номер не найден, поиск занял: " + (System.nanoTime() - timeTree) + " нс.");
                }
            }
            else{
                System.out.println("wrong format");
            }
        }

    }
}
