public class Main {

  public static void main(String[] args) {
    Container container = new Container(); // раньше контейнер содержал объект класса Integer
    // теперь в контейнере примитив int и код хотя бы запустился и выдал 0
    container.count += 7843; // а это вообще зачем? Собственно как и класс Container?

    int sum = sumDigits(7843);

    System.out.println(sum);
    System.out.println(sumDigits(0));
  }

  /* Реализуйте метод sumDigits который возвращает сумму цифр числа, пример:
  передано 12345, метод должен вернуть 15
  если передано null, то должен вернуть -1

  Запустите тесты TestSumDigits для проверки корректности работы метода

  не меняйте название метода, его возвращаемое значение и модификаторы доступа (public).
  В противном случае тестовый метод не сможет проверить ваш код
   */


  public static int sumDigits(Integer number) {
    if(number == null)
    {
      return -1;
    }
    else {
      int result = 0;
      while (number > 0) {
        result += number % 10;
        number = number / 10;
      }
      return result;
    }
  }
}
