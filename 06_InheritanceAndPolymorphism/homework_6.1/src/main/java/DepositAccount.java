import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {
    LocalDate lastIncome = LocalDate.of(2020, Month.DECEMBER, 21);

    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
    }

    @Override // тут видимо нет комиссии
    public void take(double amountToTake) {
        LocalDate dateOfTake = LocalDate.now();
        // сначала делал через Calendar и добавлял через add 30 дней, но тесты не проходил. потом нашел альтернативу
        if(ChronoUnit.MONTHS.between(lastIncome, dateOfTake) >= 1)
        {
            super.take(amountToTake);
        }
    }

    @Override
    public boolean send(BankAccount receiver, double amount) {
        double moneyToSend = amount;
        boolean isSent = false;
        if(amount > 0.0)
        {
            if(moneyAmount > moneyToSend)
            {
                LocalDate dateOfTake = LocalDate.now();
                if(ChronoUnit.MONTHS.between(lastIncome, dateOfTake) >= 1)
                {
                    receiver.put(moneyToSend);
                    moneyAmount -= moneyToSend;
                    isSent = true;
                }
            }
            else
            {
                System.out.println("Операция не может быть выполнена!");
                if(amount > moneyAmount)
                {
                    System.out.println("\tнедостаточно средств!");
                }
            }
        }
        return isSent;
    }
}
