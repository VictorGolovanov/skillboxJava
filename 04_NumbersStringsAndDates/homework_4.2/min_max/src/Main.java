public class Main
{
    public static void main(String[] args) {

        /**
         сначала задача показалась слишком уж простой, но когда я вывел значения, оказалось,
         что для чисел с плавающей точкой минимальное отрицательное значение
         не получается при использовании MIN_VALUE - результатом служит минимальное положительное число.
         Поэтому взял максимальное значение числа со знаком минус и результат показался близким к правде
         Ну а почитав доп. материалы - понял, что я оказался прав.
         */

        System.out.println("byte min value is " + Byte.MIN_VALUE);
        System.out.println("byte max value is " + Byte.MAX_VALUE);
        System.out.println();

        System.out.println("int min value is " + Integer.MIN_VALUE);
        System.out.println("int max value is " + Integer.MAX_VALUE);
        System.out.println();

        System.out.println("short min value is " + Short.MIN_VALUE);
        System.out.println("short max value is " + Short.MAX_VALUE);
        System.out.println();

        System.out.println("long min value is " + Long.MIN_VALUE);
        System.out.println("long max value is " + Long.MAX_VALUE);
        System.out.println();

        System.out.println("float min value is " + -(Float.MAX_VALUE)); // скобки для красоты
        System.out.println("float max value is " + Float.MAX_VALUE);
        System.out.println();

        System.out.println("double min value is " + -(Double.MAX_VALUE)); // скобки для красоты
        System.out.println("double max value is " + Double.MAX_VALUE);
    }
}
