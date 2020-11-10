import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      //TODO:напишите ваш код тут, результат вывести в консоль.

      // сначала уберем все лишнее
      String rusPhoneNumber = input.replaceAll("\\D+", "");

      // узнаем, получили ли мы цифры
      // использовал конструкцию из задания с именами, ибо удобно :)
      // хотя может это и лишний ход, но так уберегает от ошибок, если при вводе получаем буквы
      boolean isNumber = false;
      for(char number : input.toCharArray())
      {
        if(Character.isDigit(number))
        {
          isNumber = true;
        }
      }
      if(!isNumber){
        System.out.println("Неверный формат номера");
      }
      else{

        // узнаем первую цифру номера и заменим ее, если нужно
        String result = null;
        boolean isFirstNumCorrect = false;
        char firstNum = rusPhoneNumber.charAt(0);
        if(firstNum == '7')
        {
          result = rusPhoneNumber;
          isFirstNumCorrect = true;
        }
        else if(firstNum == '8')
        {
          result = "7" + rusPhoneNumber.substring(1);
          isFirstNumCorrect = true;
        }

        // узнаем, хватает ли цифр для составления номера
        boolean hasEnoughNumbers = false;
        char[] countNumber = rusPhoneNumber.toCharArray();
        if(countNumber.length == 11)
        {
          hasEnoughNumbers = true;
        }
        else if(countNumber.length == 10)
        {
          result = "7" + rusPhoneNumber;
          hasEnoughNumbers = true;
          isFirstNumCorrect = true;
        }
        if(hasEnoughNumbers & isFirstNumCorrect)
        {
          System.out.println(result);
        }
        else{
          System.out.println("Неверный формат номера");
        }
      }
    }
  }

}
