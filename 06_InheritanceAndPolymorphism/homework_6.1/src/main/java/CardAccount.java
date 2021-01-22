public class CardAccount extends BankAccount {
    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
    }

    @Override
    public void take(double amountToTake) {
        double amountToTakeCard = amountToTake + (amountToTake * 0.01);
        super.take(amountToTakeCard);
    }

    @Override
    public boolean send(BankAccount receiver, double amount) {
        double serviceFee = amount * 0.01;
        double moneyToSend = amount + serviceFee;
        boolean isSent = false;
        if(amount > 0.0)
        {
            if(moneyAmount > moneyToSend)
            {
                // комиссия уходит банку
                receiver.put(amount);
                moneyAmount -= moneyToSend;
                isSent = true;
                double bankProfit = serviceFee; // понимаю, что это тут лишнее, но банк же должен получить свою выгоду
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
