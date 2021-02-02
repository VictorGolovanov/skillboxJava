public class Operator implements Employee
{
    private static final double FIX_PART = 35000.0;
    private static final double monthSalary = FIX_PART;

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }
}
