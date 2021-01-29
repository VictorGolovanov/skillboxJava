public class IndividualBusinessman extends Client {

    private static final double FeeToPut_LOW = 0.005; // 0.5%
    private static final double FeeToPut_HIGH = 0.01; // 1%
    private static final double PUT_AMOUNT = 1000.0;
    private static final double FEE_TAKE = 0.01; // 1%
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
                moneyAmount += (amountToPut - (amountToPut * FeeToPut_HIGH));
            }
            if(amountToPut >= PUT_AMOUNT)
            {
                moneyAmount += (amountToPut - (amountToPut * FeeToPut_LOW));
            }
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
