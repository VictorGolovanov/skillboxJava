import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true){
            System.out.println("Укажите путь до папки:");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();
            if(path.equals("STOP")){
                break;
            }
            try{
                long directorySize = FileUtils.calculateFolderSize(path);
                System.out.println(convertResult(directorySize));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static String convertResult(long directorySize) {
        // 1 Kb = 1024 b = 2^10 b; Mb = 2^10 Kb etc...
        String[] memoryUnits = {"байт.", "Кб.", "Мб.", "Гб.", "Тб."};
        DecimalFormat decimalFormat = new DecimalFormat("###.####");
        for (int i = memoryUnits.length - 1; i >= 0; i--) {
            // идем с последнего элемента
            double convertedResult = directorySize / Math.pow(2, i * 10);
            if ((long) convertedResult > 0) { // если результат целочисленного деления больше нуля, то все ок,
                // если нет, то понижаем разрядность.
                return "Размер папки составляет " + decimalFormat.format(convertedResult) + " " + memoryUnits[i];
            }
        }
        return "0";
    }

}
