public class BankAccount {

  public double moneyAmount;
  public static final double TRANSACTION_PERCENT = 0.01;
  private static final String accountName = "Банковский счет. ";
  private static final String accountCurrency = "Валюта - RUB."; // можно было бы сделать единым для всех классов, но счета могут быть и в разных валютах
  private static final String serviceMessageMoney = "Остаток на счете: ";
  private static final String accountServiceTerms = "Пополнение и снятие без комиссий и ограничений.";

  public String ownerName = "не задано";

  public double getAmount() {
    return moneyAmount;
  }

  public void put(double amountToPut) {
    if(amountToPut >= 0.0)
    {
      moneyAmount += amountToPut;
    }
  }

  public boolean take(double amountToTake) {
    boolean isTook = false;
    if(amountToTake <= moneyAmount)
    {
      isTook = true;
      moneyAmount -= amountToTake;
    }
    return isTook;
  }

  public boolean send(BankAccount sender, BankAccount receiver, double amount)
  {
    boolean isSent = false;
    if(amount > 0.0)
    {
      if(sender.take(amount))
      {
        receiver.put(amount);
        isSent = true;
      }
      else
        {
          System.out.println("Операция не может быть выполнена!");
        }
    }
    return isSent;
  }

  @Override
  public String toString() {
    return String.format("Имя владельца: " + ownerName + "\n" + accountName + accountCurrency + "\n" + serviceMessageMoney + moneyAmount + "\n" + accountServiceTerms);
  }

  public void setOwnerName(String name)
  {
    if(name.matches("[a-zA-Z\\s]+"))
    {
      ownerName = name;
    }
    else if(name.matches("[а-яА-ЯёЁ\\s]+"))
    {
      ownerName = name;
    }
  }

  public String getOwnerName()
  {
    return ownerName;
  }

  // чтобы можно было тестировать
  public void setMoneyAmount(double moneyAmount) {
    this.moneyAmount = moneyAmount;
  }
}
