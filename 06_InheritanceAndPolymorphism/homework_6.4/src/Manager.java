public class Manager implements Employee
{
    private static final double FIX_PART = 60000.0;
    private static final double BONUS_PERCENT = 0.05;
    private static final int LOW_INCOME = 115000;
    private static final int HIGH_INCOME = 140000;
    private static final int ProfitToCompany = (int)(Math.random() * (HIGH_INCOME - LOW_INCOME) + LOW_INCOME);
    private static final double monthSalary = FIX_PART + ProfitToCompany * BONUS_PERCENT;

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }
}
