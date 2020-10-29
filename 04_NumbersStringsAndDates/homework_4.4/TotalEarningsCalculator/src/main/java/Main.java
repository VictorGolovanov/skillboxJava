public class Main {

  public static void main(String[] args) {

    String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
    //TODO: напишите ваш код, результат вывести в консоль

    // было лень самому считать индексы, к тому же java сама это прекрасно делает :)
    String vasyaIncomeString = text.substring(text.indexOf("5000"), text.indexOf("5000") + 4);
    String petyaIncomeString = text.substring(text.indexOf("7563"), text.indexOf("7563") + 4);
    String mashaIncomeString = text.substring(text.indexOf("30000"), text.indexOf("30000") + 5);

    int vasyaIncome = Integer.parseInt(vasyaIncomeString);
    int petyaIncome = Integer.parseInt(petyaIncomeString);
    int mashaIncome = Integer.parseInt(mashaIncomeString);

    int totalFriendIncome = vasyaIncome + petyaIncome + mashaIncome;
    System.out.println(totalFriendIncome);
  }
}