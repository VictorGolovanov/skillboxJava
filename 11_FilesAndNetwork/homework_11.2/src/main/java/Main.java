import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Укажите путь к исходной папке:");
            String sourcePath = scanner.nextLine();
            System.out.println("Укажите путь к папке клону:");
            String destinationPath = scanner.nextLine();
            try{
                FileUtils.copyFolder(sourcePath, destinationPath);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            if(sourcePath.equals("STOP") || destinationPath.equals("STOP")){
                break;
            }
        }
    }
}
