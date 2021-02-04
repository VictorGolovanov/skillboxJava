public class Manager implements Employee
{
    private static final double FIX_PART = 60000.0;
    private static final double BONUS_PERCENT = 0.05;
    private static final int LOW_INCOME = 115000;
    private static final int HIGH_INCOME = 140000;

    @Override
    public double getMonthSalary(int monthCompanyIncome) {
        int ProfitToCompany = (int)(Math.random() * (HIGH_INCOME - LOW_INCOME) + LOW_INCOME);
        return FIX_PART + ProfitToCompany * BONUS_PERCENT;
    }
}
