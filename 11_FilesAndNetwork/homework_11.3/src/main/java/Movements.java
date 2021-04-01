import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movements {
    private String pathMovementsCsv;
    List<Movements> movements;
    private String type;
    private String number;
    private String currency;
    private Date dateOfTransaction;
    private String referenceOfTransaction;
    private String description;
    private double income;  // 7 колонка  = Double.parseDouble(line[6])
    private double expense; // 8 колонка  = Double.parseDouble(line[7])

    public Movements(String pathMovementsCsv){
        this.pathMovementsCsv = pathMovementsCsv;
    }
    public Movements(String type,String number,String currency, Date dateOfTransaction,
                     String referenceOfTransaction, String description,
                     double income, double expense){
        this.type = type;
        this.number = number;
        this.currency = currency;
        this.dateOfTransaction = dateOfTransaction;
        this.referenceOfTransaction = referenceOfTransaction;
        this.description = description;
        this.income = income;
        this.expense = expense;
    }

    public List<Movements> loadMovementsFromFile(String pathMovementsCsv) {
        List<Movements> movements = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            // пропускаем шапку
            for(int i = 1; i < lines.size(); i++){

                String[] fragments = lines.get(i).split(",");
                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + lines.get(i));
                    continue;
                }
                else{

                    String type = fragments[0];
                    String number = fragments[1];
                    String currency = fragments[2];
                    Date dateOfTransaction = new SimpleDateFormat("dd.MM.yyyy").parse(fragments[3]);
                    String referenceOfTransaction = fragments[4];
                    String description = fragments[5];
                    double income = Double.parseDouble(fragments[6]);
                    double expense = Double.parseDouble(fragments[7]);
                    movements.add(new Movements(type, number, currency, dateOfTransaction,
                            referenceOfTransaction, description,
                            income, expense));
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movements;
    }


    // чисто геттер
    public double getExpenseSum() {
        List<Movements> movements = loadMovementsFromFile(pathMovementsCsv);
        double expenseSum = 0.0;
        for(Movements movement : movements){
            expenseSum += movement.expense;
        }
        return expenseSum;
    }

    // чисто геттер
    public double getIncomeSum() {
        List<Movements> movements = loadMovementsFromFile(pathMovementsCsv);
        double incomeSum = 0.0;
        for(Movements movement : movements){
            incomeSum += movement.income;
        }
        return incomeSum;
    }
}

