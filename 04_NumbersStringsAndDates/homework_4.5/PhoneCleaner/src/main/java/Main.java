// тесты не менял

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

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

        // вторая цифра в российских номерах должна быть 9. Проверим, так ли это в нашем случае
        boolean isSecondNumberCorrect = false;
        char secondNum = result.charAt(1);
        if(secondNum == '9')
        {
          isSecondNumberCorrect = true;
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
          if(secondNum == '9')
          {
            isSecondNumberCorrect = true;
          }
        }
        if(hasEnoughNumbers & isFirstNumCorrect & isSecondNumberCorrect)
        {
          Pattern p = Pattern.compile("(\\d{1,1})(\\d{3,3})(\\d{3,3})(\\d{2,2})(\\d{2,2})");
          Matcher m = p.matcher(result);
          String niceFormat = m.replaceAll("+$1 ($2) $3 $4-$5");
          System.out.println(niceFormat);
        }
        else{
          System.out.println("Неверный формат номера");
        }
      }
    }
  }

}
