public class IndividualBusinessman extends Client {

    private static final double FeeToPut = 0.005; // 0.5%
    private static final double defaultPutCommission = 0.01; // 1%
    private static final double PUT_AMOUNT = 1000.0;
    private static final double defaultTakeCommission = 0.01; // 1%
    @Override
    double getAmount() {
        return moneyAmount;
    }

    @Override
    void put(double amountToPut) {
        if(amountToPut >= 0.0)
        {
            // странный способ поддержки предпримательства
            if(amountToPut < PUT_AMOUNT)
            {
                put(amountToPut, defaultPutCommission);
                //moneyAmount += (amountToPut - (amountToPut * FeeToPut_HIGH));
            }
            if(amountToPut >= PUT_AMOUNT)
            {
                put(amountToPut, FeeToPut);
                //moneyAmount += (amountToPut - (amountToPut * FeeToPut_LOW));
            }
        }
    }
    @Override
    protected void put(double amountToPut, double commission) {
        if(amountToPut >= 0.0)
        {
            moneyAmount += (amountToPut - (amountToPut * commission));
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
    IndividualBusinessman()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }

}
