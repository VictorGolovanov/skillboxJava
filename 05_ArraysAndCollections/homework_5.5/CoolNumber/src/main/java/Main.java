import java.util.*;

public class Main {
    //public static CoolNumbers coolNumbers = new CoolNumbers();
    public static void main(String[] args) {


        System.out.println("Здравствуйте!");
        System.out.println("Подождите пожалуйста, идут вычисления... :)");
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

            if (input.equals("0")) {
                break;
            }

            if(CoolNumbers.isCarNumberInString(input))
            {
                CoolNumbers.bruteForceSearchInList(coolNumbersData, input);
                CoolNumbers.binarySearchInList(coolNumbersData, input);
                CoolNumbers.searchInHashSet(hashSet, input);
                CoolNumbers.searchInTreeSet(treeSet, input);
            }
            else{
                System.out.println("wrong format");
            }
        }

    }
}
