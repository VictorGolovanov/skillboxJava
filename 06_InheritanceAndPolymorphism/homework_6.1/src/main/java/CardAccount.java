public class CardAccount extends BankAccount {

    private static final String accountName = "Счет кредитной карты. ";
    private static final String accountCurrency = "Валюта - RUB.";
    private static final String serviceMessageMoney = "Остаток на счете: ";
    private static final String accountServiceTerms = "За снятие наличных и перевод взимается комиссия в размере ";
    private static final String serviceMessageFee = "%% от суммы снятия или перевода."; // %% иначе выходил UnknownFormatConversionException


    @Override
    public boolean take(double amountToTake) {
        double amountToTakeCard = amountToTake + (amountToTake * TRANSACTION_PERCENT);
        return super.take(amountToTakeCard);
    }


    @Override
    public String toString() {
        return String.format("Имя владельца: " + ownerName + "\n" + accountName + accountCurrency +
                "\n" + serviceMessageMoney + moneyAmount +
                "\n" + accountServiceTerms + (TRANSACTION_PERCENT * 100) + serviceMessageFee);
    }
}
