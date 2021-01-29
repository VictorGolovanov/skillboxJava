public abstract class Client {

    protected double moneyAmount;

    abstract double getAmount();

    abstract void put(double amountToPut);

    abstract void take(double amountToTake);

}
