public class PhysicalPerson extends Client {

    private static final double defaultPutCommission = 0.0;
    private static final double defaultTakeCommission = 0.0;

    @Override
    double getAmount() {
        return moneyAmount;
    }

    PhysicalPerson()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }
}
