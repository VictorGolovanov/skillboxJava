public class PhysicalPerson extends Client {

    private static final double defaultPutCommission = 0.0;
    private static final double defaultTakeCommission = 0.0;

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
        if(amountToTake <= moneyAmount)
        {
            moneyAmount -= amountToTake;
        }
    }

    // конструктор-наследник?
    PhysicalPerson()
    {
        super(defaultPutCommission, defaultTakeCommission);

    }
}
