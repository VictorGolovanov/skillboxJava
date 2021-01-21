import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {

        // добавляем в несколько этапов
        // так как красота - дело субъективное, то будет так:

        ArrayList<String> carLicensePlates = new ArrayList<>(); // сюда сохраним наши номера

        // условность в том, что не все числа из диапазона являются номерами регионов, но по условию задачи от [1 ; 199]
        ArrayList<Integer> regionInt = new ArrayList<>();
        for(int i = 1; i <= 199; i++)
        {
            regionInt.add(i);
        }

        // переделаем в String, чтобы были красивые нули в регионах от 1 до 9
        ArrayList<String> regionString = new ArrayList<String>();
        for (Integer integer : regionInt) {
            if (integer < 10) {
                regionString.add("0" + integer);
            } else {
                regionString.add(Integer.toString(integer));
            }
        }

        // массив разрешенных букв для номеров
        String[] allowedLetters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // 1) номера с одинаковыми буквами и одинаковыми цифрами: А111АА.region etc.
        // дает 23880 автомобильных номеров
        // по регионам: все многообразие сгенерированных номеров умножается на количество регионов
        for (String reg : regionString) {
            for (String allowedLetter : allowedLetters) { // по буквам
                for (int number : numbers) { // по цифрам
                    String oneCarPlate = String.format("%s%d%d%d%s%s%s", allowedLetter,
                            number, number, number,
                            allowedLetter, allowedLetter,
                            reg);
                    carLicensePlates.add(oneCarPlate);
                }
            }
        }

        // 2) номера с одинаковыми буквами и не красивыми цифрами цифрами Х793ХХ192.
        // дает 2 149 200 автомобильных номеров
        for (String reg : regionString) { // по регионам
            for (String allowedLetter : allowedLetters) { // по буквам
                for (int k1 : numbers) { // по цифрам: так как три цифры, то k1 => сотни
                    for (int k2 : numbers) { // k2 => десятки
                        for (int k3 : numbers) { // k3 => единицы
                            String oneCarPlate = String.format("%s%d%d%d%s%s%s", allowedLetter,
                                    k1, k2, k3,
                                    allowedLetter, allowedLetter,
                                    reg);
                            // хотим исключить номера типа 000 111 222 333, которые уже добавили
                            if (k1 != k3) // также не попадут номера 010 101 202 и т.д., но мы их можем добавить отдельно
                            {
                                carLicensePlates.add(oneCarPlate);
                            }
                        }
                    }
                }
            }
        }
        // 3) с не одинаковыми буквами и одинаковыми цифрами
        // дает 3 152 160 автомобильных номеров
        for (String reg : regionString){
            // по буквам. теперь они разные - одинаковые буквы попробуем исключить
            for(String letter1 : allowedLetters){ // так как и с цифрами - всего три буквы
                for (String letter2 : allowedLetters){
                    for (String letter3 : allowedLetters){
                        for (int number : numbers) {
                            String oneCarPlate = String.format("%s%d%d%d%s%s%s", letter1,
                                    number, number, number,
                                    letter2, letter3,
                                    reg);
                            if(!letter1.equals(letter3)) // пропустим ААА ВВВ ... ХХХ и АВА АХА и т.д.
                            {
                                carLicensePlates.add(oneCarPlate);
                            }
                        }
                    }
                }
            }
        }

        // 4) с одинаковми буквами и особыми цифрами: 101 010 212 121 123 234 ... 987 876 787 878 и т.д.
        // дает 214 920 автомобильных номеров
        for (String reg : regionString) { // по регионам
            for (String allowedLetter : allowedLetters) { // по буквам
                for (int k1 : numbers) { // по цифрам: так как три цифры, то k1 => сотни
                    for (int k2 : numbers) { // k2 => десятки
                        for (int k3 : numbers) { // k3 => единицы
                            String oneCarPlate = String.format("%s%d%d%d%s%s%s", allowedLetter,
                                    k1, k2, k3,
                                    allowedLetter, allowedLetter,
                                    reg);
                            // будем добавлять только красивые номера: 101 010 707 070 717 (k1==k3) != k2
                            if (k1 == k3 && k1 != k2)
                            {
                                carLicensePlates.add(oneCarPlate);
                            }
                        }
                    }
                }
            }
        }
        return carLicensePlates;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        boolean isFoundBrute = false;
        long tBrute = System.nanoTime();
        if (list.contains(number)) {
            System.out.println("Поиск перебором: номер найден, поиск занял: " + (System.nanoTime() - tBrute) + " нс.");
            isFoundBrute = true;
        } else {
            System.out.println("Поиск перебором: номер не найден, поиск занял: " + (System.nanoTime() - tBrute) + " нс.");
        }
        return isFoundBrute;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        boolean isFoundBinary = false;
        long timeBinary = System.nanoTime();
        if (Collections.binarySearch(sortedList, number) >= 0) {
            System.out.println("Бинарный поиск: номер найден, поиск занял: " + (System.nanoTime() - timeBinary) + " нс.");
            isFoundBinary = true;
        } else {
            System.out.println("Бинарный поиск: номер не найден, поиск занял: " + (System.nanoTime() - timeBinary) + " нс.");
        }
        return isFoundBinary;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        boolean isFoundInHash = false;
        long timeHash = System.nanoTime();
        if (hashSet.contains(number)) {
            System.out.println("Поиск в HashSet: номер найден, поиск занял: " + (System.nanoTime() - timeHash) + "нс");
            isFoundInHash = true;
        } else {
            System.out.println("Поиск в HashSet: номер не найден, поиск занял: " + (System.nanoTime() - timeHash) + "нс");
        }
        return isFoundInHash;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        boolean isFoundInTree = false;
        long timeTree = System.nanoTime();
        if (treeSet.contains(number)) {
            System.out.println("Поиск в TreeSet: номер найден, поиск занял: " + (System.nanoTime() - timeTree) + " нс.");
            isFoundInTree = true;
        } else {
            System.out.println("Поиск в TreeSet: номер не найден, поиск занял: " + (System.nanoTime() - timeTree) + " нс.");
        }
        return isFoundInTree;
    }

    public static boolean isCarNumberInString(String curNumber){
        boolean isNumber = false;
        String format = "[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,}";
        if(curNumber.matches(format))
        {
            isNumber = true;
        }
        return isNumber;
    }
}
