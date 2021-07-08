import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class GeneratorWithStringBuilder2
{
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("res/numbers.txt");

        int regionCode = 199;
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int number = 1; number < 1000; number++) {
            StringBuilder builder = new StringBuilder();
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter);
                        builder.append(padNumber(number, 3));
                        builder.append(secondLetter);
                        builder.append(thirdLetter);
                        builder.append(padNumber(regionCode, 2));
                        builder.append("\n");

                    }
                }
            }
            writer.write(builder.toString().getBytes(StandardCharsets.UTF_8));
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.append('0').append(numberStr);
        }

        return numberStr.toString();
    }
}
