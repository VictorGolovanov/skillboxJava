public class LegalPerson extends Client {

    private static final double FEE_TAKE = 0.01; // 1%

    @Override
    double getAmount() {
        return moneyAmount;
    }

    @Override
    void put(double amountToPut) {
        if(amountToPut >= 0.0)
        {
            moneyAmount += amountToPut;
        }
    }

    @Override
    void take(double amountToTake) {
        double amountToTakeWithFee = amountToTake + (amountToTake * FEE_TAKE);
        if(amountToTakeWithFee <= getAmount())
        {
            moneyAmount -= amountToTakeWithFee;
        }
    }

}
