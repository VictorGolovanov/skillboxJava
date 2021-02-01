public class LegalPerson extends Client {

    private static final double defaultTakeCommission = 0.01; // 1%
    private static final double defaultPutCommission = 0.0;

    LegalPerson()
    {
        super(defaultPutCommission, defaultTakeCommission);
    }

}
