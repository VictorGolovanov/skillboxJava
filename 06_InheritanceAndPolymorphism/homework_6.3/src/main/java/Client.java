public abstract class Client {

    private final double putCommission;
    private final double takeCommission;

    protected double moneyAmount;

    abstract double getAmount();

    //должен быть protected? Я почему-то думал, что в абстрактном классе все методы должны быть абстрактными :)
    protected void put(double amountToPut){
        put(amountToPut, putCommission);
    }

    protected void put(double amountToPut, double putCommission){
        if(amountToPut >= 0.0)
        {
            moneyAmount += (amountToPut - (amountToPut * putCommission));
        }
    }

    protected void take(double amountToTake)
    {
        take(amountToTake, takeCommission);
    }

    protected void take(double amountToTake, double takeCommission)
    {
        if(amountToTake <= moneyAmount)
        {
            moneyAmount -= (amountToTake + (amountToTake * takeCommission));
        }
    }

    // конструктор
    protected Client(double putCommission, double takeCommission)
    {
        this.putCommission = putCommission;
        this.takeCommission = takeCommission;
    }

}
