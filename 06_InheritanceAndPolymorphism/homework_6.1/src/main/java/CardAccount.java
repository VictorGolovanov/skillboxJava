public class CardAccount extends BankAccount {


    private static final String accountName = "Счет кредитной карты. ";
    private static final String accountCurrency = "Валюта - RUB.";
    private static final String serviceMessageMoney = "Остаток на счете: ";
    private static final String accountServiceTerms = "За снятие наличных и перевод взимается комиссия в размере ";
    private static final String serviceMessageFee = "%% от суммы снятия или перевода."; // %% иначе выходил UnknownFormatConversionException
    double moneyAmount = getMoneyAmount();

    @Override
    public void put(double amountToPut) {
        if(amountToPut >= 0.0)
        {
            moneyAmount += amountToPut;
        }
    }

    @Override
    public boolean take(double amountToTake) {
        boolean isTook = false;
        double amountWithPercent = amountToTake + (amountToTake * TRANSACTION_PERCENT);
        if(amountWithPercent <= moneyAmount)
        {
            isTook = true;
            moneyAmount -= amountWithPercent;
        }
        return isTook;
    }


    @Override
    public String toString() {
        return String.format("Имя владельца: " + ownerName + "\n" + accountName + accountCurrency +
                "\n" + serviceMessageMoney + moneyAmount +
                "\n" + accountServiceTerms + (TRANSACTION_PERCENT * 100) + serviceMessageFee);
    }
}
