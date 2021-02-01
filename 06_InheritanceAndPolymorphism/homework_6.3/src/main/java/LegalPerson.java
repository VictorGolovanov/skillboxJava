public class LegalPerson extends Client {

    private static final double defaultTakeCommission = 0.01; // 1%
    private static final double defaultPutCommission = 0.0;

    @Override
    double getAmount() {
        return moneyAmount;
    }

    LegalPerson()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }

}
