public class Main {

    public static void main(String[] args) {
        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008> 1234 <5678> 8912", "***");
        System.out.println(safe);
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений
        String changedString = null;
        // узнаем сколько скобок есть в строке
        int openDiamondCount = 0;
        for(char openDiamond : text.toCharArray())
        {
            if(openDiamond == '<')
            {
                openDiamondCount++;
            }
        }
        int closeDiamondCount = 0;
        for(char closeDiamond : text.toCharArray())
        {
            if(closeDiamond == '>')
            {
                closeDiamondCount++;
            }
        }
        // если скобок вообще нет, либо нет какой-то конкретной скобки
        // => строка не содержит защищеной информации
        if(openDiamondCount == 0 || closeDiamondCount == 0)
        {
            changedString = text;
        }
        else if(openDiamondCount != closeDiamondCount)
        {
            changedString = text;
        }
        else if(openDiamondCount == 1 & closeDiamondCount == 1)
        {
            int firstDiamond; // индекс открывающей скобки
            int lastDiamond;  // индекс закрывающей скобки

            firstDiamond = text.indexOf("<");
            lastDiamond = text.indexOf(">");
            changedString = text.substring(0, firstDiamond) + placeholder + text.substring(lastDiamond + 1);
        }
        // честно говоря выглядит немного как костыль
        else if(openDiamondCount == 2 & closeDiamondCount == 2)
        {
            int firstDiamond; // индекс первой открывающей скобки
            int lastDiamond;  // индекс первой закрывающей скобки
            int firstDiamond_2; // индекс первой открывающей скобки
            int lastDiamond_2;  // индекс первой закрывающей скобки

            firstDiamond = text.indexOf("<");
            lastDiamond = text.indexOf(">");
            firstDiamond_2 = text.lastIndexOf("<");
            lastDiamond_2 = text.lastIndexOf(">");

            changedString = text.substring(0, firstDiamond)
                    + placeholder
                    + text.substring(lastDiamond + 1, firstDiamond_2)
                    + placeholder + text.substring(lastDiamond_2 + 1);
        }
        return changedString;
    }

}