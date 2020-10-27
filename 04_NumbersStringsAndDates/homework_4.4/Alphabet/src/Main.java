public class Main
{
    public static void main(String[] args) {

        // берем массив с буквами из открытых источников
        char[] latinLettersSmall = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] latinLettersBig = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        for(int i = 0; i < latinLettersSmall.length; i++){
            System.out.println("Код: " + (int)latinLettersSmall[i] + "; Буква: " + latinLettersSmall[i]);
        }

        System.out.println("======================================");
        System.out.println("======================================");

        for(int i = 0; i < latinLettersBig.length; i++){
            System.out.println("Код: " + (int)latinLettersBig[i] + "; Буква: " + latinLettersBig[i]);
        }
    }
}
