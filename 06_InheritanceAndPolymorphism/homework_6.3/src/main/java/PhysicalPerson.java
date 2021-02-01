public class PhysicalPerson extends Client {

    private static final double defaultPutCommission = 0.0;
    private static final double defaultTakeCommission = 0.0;

    PhysicalPerson()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }
}
