public class Main {

  public static void main(String[] args) {
    System.out.println(splitTextInToWords("qwerty, ... 12?3 qwerty; 456. Abrakadabra 789 djn!"));
  }

  public static String splitTextInToWords(String text) {
    //TODO реализуйте метод
    String clearTextNum = text.replaceAll("\\d+", ""); // сначала цифры
    String clearTextAll = clearTextNum.replaceAll("[.,;:`!?\\-]", ""); // потом знаки препинания
    // Заменим все пробелы между словами (сколько бы их ни было) на один пробел между словами
    String goodString = clearTextAll.replaceAll("\\s+", " ");
    String[] onlyWords = goodString.split(" ");
    // а как вывести наш массив через return?
    /*for(int i = 0; i < onlyWords.length; i++)
    {
      System.out.println(onlyWords[i]);
    }*/
    return onlyWords;
  }

}