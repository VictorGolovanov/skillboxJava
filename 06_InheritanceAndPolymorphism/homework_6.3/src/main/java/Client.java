public abstract class Client {

    protected double putCommission;
    protected double takeCommission;

    protected double moneyAmount;

    abstract double getAmount();

    abstract void put(double amountToPut);

    abstract void put(double amountToPut, double commission);

    abstract void take(double amountToTake);

    abstract void take(double amountToTake, double takeCommission);

    // конструктор
    protected Client(double putCommission, double takeCommission)
    {
        this.putCommission = putCommission;
        this.takeCommission = takeCommission;
    }

}
