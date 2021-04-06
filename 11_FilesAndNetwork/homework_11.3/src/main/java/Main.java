
public class Main {

    public static final String MOVEMENT_LIST = "files/movementList.csv";

    public static void main(String[] args) {
        Movements mov = new Movements(MOVEMENT_LIST);
        double expenseSum = mov.getExpenseSum();
        double incomeSum = mov.getIncomeSum();

        System.out.println("Общая сумма доходов: " + incomeSum + " руб.");
        System.out.println("Общая сумма расходов: " + expenseSum + " руб.\n");

        System.out.println("Сумма расходов по категориям:");

        mov.getExpensesByDescription();

    }
}
