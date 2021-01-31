public class LegalPerson extends Client {

    private static final double defaultTakeCommission = 0.01; // 1%
    private static final double defaultPutCommission = 0.0;

    @Override
    double getAmount() {
        return moneyAmount;
    }

    @Override
    void put(double amountToPut) {
        put(amountToPut, defaultPutCommission);
    }
    @Override
    protected void put(double amountToPut, double commission) {
        if(amountToPut >= 0.0)
        {
            moneyAmount += amountToPut;
        }
    }

    @Override
    void take(double amountToTake) {
        take(amountToTake, defaultTakeCommission);
    }

    @Override
    protected void take(double amountToTake, double takeCommission) {
        double amountToTakeWithFee = amountToTake + (amountToTake * takeCommission);
        if(amountToTakeWithFee <= getAmount())
        {
            moneyAmount -= amountToTakeWithFee;
        }
    }

    // конструктор-наследник?
    LegalPerson()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }

}
