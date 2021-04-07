import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Movements {
    private List<String> lines;
    private List<Transaction> movements;

    public Movements(String pathMovementsCsv){
        loadMovementsFromFile(pathMovementsCsv);
    }

    private void loadMovementsFromFile(String pathMovementsCsv) {
        try {
            movements = new ArrayList<>();
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
                        String description = fragments[5].split("    ")[1].trim(); // отсекаем все лишнее
                        double income = Double.parseDouble(fragments[6]);
                        double expense  = Double.parseDouble(fragments[7]);

                        movements.add(new Transaction(type, number, currency,
                                dateOfTransaction, referenceOfTransaction,
                                description, income, expense));
                    }
                }
            } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public double getExpenseSum() {
        return movements.stream().mapToDouble(Transaction::getExpense).sum();
    }

    public double getIncomeSum() {
        return movements.stream().mapToDouble(Transaction::getIncome).sum();
    }

    public void getExpensesByDescription(){
        // ключи - описание операции; значения - размер расходов
        Map<String, Double> map = movements.stream()
                .collect(Collectors.groupingBy(Transaction::getDescription, Collectors.summingDouble(Transaction::getExpense)));

        for(Map.Entry<String, Double> m : map.entrySet()){
            System.out.println("ИТОГО расход по категории: " + m.getKey() + " -> " + m.getValue() + " RUB");
        }
    }
}

