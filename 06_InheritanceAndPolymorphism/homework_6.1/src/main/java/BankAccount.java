public class BankAccount {

  public double moneyAmount;

  public double getAmount() {
    return moneyAmount;
  }

  public void put(double amountToPut) {
    if(amountToPut >= 0.0)
    {
      moneyAmount += amountToPut;
    }
  }

  public void take(double amountToTake) {
    if(amountToTake <= moneyAmount)
    {
      moneyAmount -= amountToTake;
    }
  }

  public boolean send(BankAccount receiver, double amount)
  {
    double moneyToSend = amount;
    boolean isSent = false;
    if(amount > 0.0)
    {
      if(moneyAmount > moneyToSend)
      {
        receiver.put(moneyToSend);
        moneyAmount -= moneyToSend;
        isSent = true;
      }
      else
        {
          System.out.println("Операция не может быть выполнена!");
          if(amount > moneyAmount)
          {
            System.out.println("\t\t\t\t\t\t\t\tнедостаточно средств!");
          }
        }
    }
    return isSent;
  }
}
