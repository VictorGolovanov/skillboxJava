import java.util.Scanner;

public class Main
{
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 27017;
    private static final String DB = "test"; //"logistics"

    public static void main(String[] args) {
        SaleLogistic logistic = SaleLogistic.getInstance(HOST, PORT, DB);

        System.out.println("Command example:");
        System.out.println(
                "add_store <storeName>" +
                "\nadd_product <productName> <productPrice>" +
                "\nexhibit <productName> <storeName>" +
                "\nlist" +
                "\nexit");
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine();
            if(logistic.isCommandExit(command)){
                System.out.println("Goodbye!");
                break;
            }
            try{
                if(logistic.isCommandAddStore(command)){
                    logistic.addStore(command);
                }
                if(logistic.isCommandAddProduct(command)){
                    logistic.addProduct(command);
                }
                if(logistic.isCommandExhibit(command)){
                    logistic.exhibitProductToStore(command);
                }
                if(logistic.isCommandList(command)){
                    logistic.listStatistics();
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
