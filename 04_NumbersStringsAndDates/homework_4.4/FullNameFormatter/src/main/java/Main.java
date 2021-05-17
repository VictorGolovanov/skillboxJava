import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      //При невалидном ФИО вывести в консоль: Введенная строка не является ФИО

      input = input.trim();
      // посчитаем количество слов используя пробелы
      // 2 слова - три пробела, если пробелов больше - слов больше
      // договоримся: если между словами больше 1 пробела - неверный формат ввода
      int spaceCount = 0;
      for(char space : input.toCharArray())
      {
        if(space == ' ')
        {
          spaceCount++;
        }
      }

      // теперь проверим, есть ли цифры в строке
      boolean isNumber = false;
      for(char number : input.toCharArray())
      {
        if(Character.isDigit(number))
        {
          isNumber = true;
        }
      }

      if(isNumber == true || spaceCount != 2)
      {
        System.out.println("Введенная строка не является ФИО");
      }
      else
        {
          String surname = input.substring(0, input.indexOf(" "));
          String withoutSurname = input.substring(input.indexOf(" ") + 1);
          String name = withoutSurname.substring(0, withoutSurname.indexOf(" "));
          String fatherName = withoutSurname.substring(withoutSurname.indexOf(" ") + 1);
          System.out.println("Фамилия: " + surname);
          System.out.println("Имя: " + name);
          System.out.println("Отчество: " + fatherName);
        }

    }
  }

}