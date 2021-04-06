import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class Movements {
    private List<String> lines;
    private List<Transaction> movements;
    private Set<String> descriptionSet;

    public Movements(String pathMovementsCsv){
        loadMovementsFromFile(pathMovementsCsv);
    }

    private void loadMovementsFromFile(String pathMovementsCsv) {
        try {
            movements = new ArrayList<>();
            descriptionSet = new TreeSet<>();
            lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            // пропускаем шапку
            for(int i = 1; i < lines.size(); i++){
                // в общем вот: найдем и заменим все запятые внутри кавычек на точки
                // потом удалим все кавычки
                StringBuilder builder = new StringBuilder(lines.get(i));
                boolean inQuotes = false;

                for (int currentIndex = 0; currentIndex < builder.length(); currentIndex++) {
                    char currentChar = builder.charAt(currentIndex);
                    if (currentChar == '\"'){
                        inQuotes = !inQuotes;
                    }
                    if (currentChar == ',' && inQuotes) {
                        builder.setCharAt(currentIndex, '.');
                    }
                }

                String line = builder.toString().replace("\"", "");

                String[] fragments = line.split(",");

                if(fragments.length != 8){
                    throw new IOException("Wrong line " + line + " !!!");
                }
                    else{
                        String type = fragments[0];
                        String number = fragments[1];
                        String currency = fragments[2];
                        Date dateOfTransaction = new SimpleDateFormat("dd.MM.yyyy").parse(fragments[3]);
                        String referenceOfTransaction = fragments[4];
                        String description = fragments[5];
                        double income = Double.parseDouble(fragments[6]);
                        double expense  = Double.parseDouble(fragments[7]);

                        movements.add(new Transaction(type, number, currency,
                                dateOfTransaction, referenceOfTransaction,
                                description, income, expense));

                        String[] descriptionFragments = description.split("    "); // делим по 4 пробелам
                        descriptionSet.add(descriptionFragments[1].trim()); // страшно смотрится без .trim()
                    }
                }
            } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public double getExpenseSum() {
        double expenseSum = 0.0;
        for(Transaction movement : movements){
            expenseSum += movement.getExpense();
        }
        return expenseSum;
    }

    public double getIncomeSum() {
        double incomeSum = 0.0;
        for(Transaction movement : movements){
            incomeSum += movement.getIncome();
        }
        return incomeSum;
    }

    public void getExpensesByDescription(){
        for(String ds : descriptionSet){
            double expenseSum = 0.0;
            for(Transaction movement : movements){
                if(movement.getExpense() != 0 && movement.getDescription().contains(ds)){
                    expenseSum += movement.getExpense();
                }
            }
            System.out.print(expenseSum != 0 ? "ИТОГО расход по категории: " + ds + " -> " + expenseSum + " RUB\n" : "");
        }
    }
}

