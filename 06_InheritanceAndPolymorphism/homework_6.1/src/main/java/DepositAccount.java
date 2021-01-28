import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {
    private static final String accountName = "Депозитарный счет. ";
    private static final String accountCurrency = "Валюта - RUB.";
    private static final String serviceMessageMoney = "Остаток на счете: ";
    private static final String accountServiceTerms = "Снятие и перевод средств возможны не ранее чем через 1 месяц после последнего пополнения счета.";
    private static final String serviceMessageTime = "Дата последнего пополнения: ";



    //тут сделаем private чтобы можно было только тут менять.
    private LocalDate lastIncome = LocalDate.of(2020, Month.DECEMBER, 21); // не знаю, как сымитировать пополнение когда-то в прошлом

    @Override
    public boolean take(double amountToTake) {
        LocalDate dateOfTake = LocalDate.now();
        if(ChronoUnit.MONTHS.between(lastIncome, dateOfTake) >= 1)
        {
            return super.take(amountToTake);
        }
        else{
            System.out.println("С момента предыдущего пополнения счета прошло меньше месяца.");
            return false;
        }
    }

    @Override
    public void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut);
    }

    @Override
    public String toString() {
        DateTimeFormatter lastIncomeFormat = DateTimeFormatter.ofPattern("dd.LL.yyyy");
        String lastIncomeString = lastIncomeFormat.format(lastIncome);
        return String.format("Имя владельца: " + ownerName + "\n" + accountName + accountCurrency +
                "\n" + serviceMessageMoney + getMoneyAmount() +
                "\n" + accountServiceTerms + "\n" + serviceMessageTime + lastIncomeString);
    }
}
