public class PhysicalPerson extends Client {

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
        if(amountToTake <= moneyAmount)
        {
            moneyAmount -= amountToTake;
        }
    }
}
