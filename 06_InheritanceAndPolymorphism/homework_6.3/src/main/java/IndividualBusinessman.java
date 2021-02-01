public class IndividualBusinessman extends Client {

    private static final double defaultPutCommission = 0.01; // 1%
    private static final double nextLevelPutCommission = 0.005; // 0.5%
    private static final double PUT_AMOUNT = 1000.0;
    private static final double defaultTakeCommission = 0.01; // 1%

    @Override
    double getAmount() {
        return moneyAmount;
    }

    @Override
    protected void put(double amountToPut) {
        if(amountToPut >= 0.0)
        {
            if(amountToPut < PUT_AMOUNT)
            {
                put(amountToPut, defaultPutCommission);
            }
            if(amountToPut >= PUT_AMOUNT)
            {
                put(amountToPut, nextLevelPutCommission);
            }
        }
    }

    IndividualBusinessman()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }

}
