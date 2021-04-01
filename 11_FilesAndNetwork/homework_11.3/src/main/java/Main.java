import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String MOVEMENT_LIST = "files/movementList.csv";

    public static void main(String[] args) {
        //List<Movements> movements = new ArrayList<>();
        Movements mov = new Movements(MOVEMENT_LIST);
        /*Movements mov = new Movements(MOVEMENT_LIST);
        //System.out.println(mov.loadMovementsFromFile(MOVEMENT_LIST, movements));
        System.out.println(mov.loadMovementsFromFile(MOVEMENT_LIST, movements));*/

        //movements = mov.loadMovementsFromFile(MOVEMENT_LIST);
        System.out.println("Сумма расходов: " + mov.getExpenseSum());
        System.out.println("Сумма доходов: " + mov.getIncomeSum());
    }
}
