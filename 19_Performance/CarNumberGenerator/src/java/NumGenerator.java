import java.io.PrintWriter;

public class NumGenerator implements Runnable
{
    private String path;
    private long start;

    public NumGenerator(String path, long start) {
        this.path = path;
        this.start = start;
    }


    @Override
    public void run() {
        try{
            PrintWriter writer = new PrintWriter(path);
            // char letters[] => C-style
            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for(int regionCode = 1; regionCode < 100; regionCode++){
                StringBuilder builder = new StringBuilder();
                for (int number = 1; number < 1000; number++) {
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
                }
                writer.write(builder.toString());
            }

            writer.flush();
            writer.close();

            System.out.println((System.currentTimeMillis() - start) + " ms");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String padNumber(int number, int numberLength) {
        //StringBuilder здесь не дал прироста производительности, скорее наоборот
        // создается новый объект, туда помещается строковое значение целого числа,
        // что опять же требует времени дополнительного
        // кажется неоправданным решением использовать StringBuilder в таком простом методе.
        /*StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr.append('0').append(numberStr);
        }

        return numberStr.toString();*/

        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }

        return numberStr;
    }
}
