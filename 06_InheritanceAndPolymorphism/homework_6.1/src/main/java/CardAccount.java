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
}
