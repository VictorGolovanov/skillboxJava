public class Main {

  public static void main(String[] args) {
    Container container = new Container();

    container.count += 7843;

    int sum = sumDigits(7843);

    System.out.println(sum);
  }

  /* Реализуйте метод sumDigits который возвращает сумму цифр числа, пример:
  передано 12345, метод должен вернуть 15
  если передано null, то должен вернуть -1

  Запустите тесты TestSumDigits для проверки корректности работы метода

  не меняйте название метода, его возвращаемое значение и модификаторы доступа (public).
  В противном случае тестовый метод не сможет проверить ваш код
   */

  public static int sumDigits(Integer number)
  {
    int result = 0;
    if(number == null)
    {
      return -1;
    }
    else
      {
      // создадим строку из полученного числа,
      String numString = Integer.toString(number);

      // теперь создадим массив из char элементов
      char[] numChar = numString.toCharArray();
      // теперь с помощью цикла будем переводить каждый элемент массива обратно в int
      // и суммировать их в result
      int i = 0; // счетчик и индекс по которому будем получать целочисленные значения из char
      while (i < numChar.length)
      {
        result += Character.getNumericValue(numChar[i]); // здесь делаем опять числа
        i++;
      }
      }
    return result;
  }
}
