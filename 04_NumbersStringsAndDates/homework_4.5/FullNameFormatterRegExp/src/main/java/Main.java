import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      input = input.trim();

      // проверим, есть ли цифры в строке
      boolean isNumber = false;
      for(char number : input.toCharArray())
      {
        if(Character.isDigit(number))
        {
          isNumber = true;
        }
      }

      // разобьем строку на массив строк
      String[] fullName = input.split(" ");

      // проверим, сколько слов в получившемся массиве
      boolean hasEnoughWords = false;
      if(fullName.length == 3)
      {
        hasEnoughWords = true;
      }

      // теперь результат
      if(isNumber || !hasEnoughWords)
      {
        System.out.println("Введенная строка не является ФИО");
      }
      else
        {
          System.out.println("Фамилия: " + fullName[0]);
          System.out.println("Имя: " + fullName[1]);
          System.out.println("Отчество: " + fullName[2]);
        }
    }
  }

}