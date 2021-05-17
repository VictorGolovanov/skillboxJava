import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    System.out.println(calculateSalarySum("Дядя Федор заработал 100 рублей, кот Матроскин заработал 200 рублей, а Шарик только 50 рублей."));

  }

  public static int calculateSalarySum(String text){

    int result = 0; // если числа не будут найдены, то вывод будет 0
    Matcher m = Pattern.compile("\\d+").matcher(text); // вроде бы так
    while (m.find()) { // а тут собственно и происходит поиск подходящей подстроки внутри всей строки?
      // т.к. цикл, то после первого результата ищется второй и т.д.?
      int findInt = Integer.parseInt(m.group()); // тогда каждое вхождение заново записываем в findInt
      // System.out.println(findInt); // проверка вывода: действительно по очереди печатает найденные числа (100 200 50)
      result += findInt; // и прибавляем к result
    }
    return result;
  }
}