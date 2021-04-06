import java.util.Date;

public class Transaction {
    private String type;
    private String number;
    private String currency;
    private Date dateOfTransaction;
    private String referenceOfTransaction;
    private String description;
    private double income;  // 7 колонка
    private double expense; // 8 колонка


    protected Transaction(String type, String number, String currency, Date dateOfTransaction,
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

    public double getExpense() {
        return expense;
    }

    public double getIncome() {
        return income;
    }

    public String getDescription() {
        return description;
    }

    // остальные геттеры пока не нужны в задаче, но могут пригодиться

    public String getReferenceOfTransaction() {
        return referenceOfTransaction;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getCurrency() {
        return currency;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }
}
