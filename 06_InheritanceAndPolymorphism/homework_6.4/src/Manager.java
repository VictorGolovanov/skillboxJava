public class Manager implements Employee
{
    private Company company;
    private static final double FIX_PART = 60000.0;
    private static final double BONUS_PERCENT = 0.05;
    private static final int LOW_INCOME = 115000;
    private static final int HIGH_INCOME = 140000;

    public int getProfitForCompany()
    {
        return (int)(Math.random() * (HIGH_INCOME - LOW_INCOME) + LOW_INCOME);
    }

    @Override
    public double getMonthSalary() {
        return FIX_PART + getProfitForCompany() * BONUS_PERCENT;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format(getMonthSalary() + " руб.");
    }
}
