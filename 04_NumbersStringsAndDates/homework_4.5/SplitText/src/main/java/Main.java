public class Main {

  public static void main(String[] args) {
    System.out.println(splitTextInToWords("qwerty, ... 12?3 QWERTY; 456. Abra-kadabra 789 d-jn!"));
  }

  public static String splitTextInToWords(String text) {
    String clearTextNum = text.replaceAll("\\d+", ""); // сначала цифры
    String clearTextAll = clearTextNum.replaceAll("[.,;:!?]", ""); // потом знаки препинания
    String clearText = clearTextAll.replaceAll("\\-+", " ");
    // Заменим все пробелы между словами (сколько бы их ни было) на один пробел между словами
    String goodString = clearText.replaceAll("\\s+", " ");
    String[] onlyWords = goodString.split(" ");

    // так как метод требует String, попробовал сделать строку

    StringBuffer stringBuffer = new StringBuffer();
    for(int i = 0; i < onlyWords.length; i++)
    {
      stringBuffer.append(onlyWords[i] + "\n");
    }
    String s = stringBuffer.toString().trim(); // чтобы убрать лишний перенос строки и получить строку
    return s;
  }

}