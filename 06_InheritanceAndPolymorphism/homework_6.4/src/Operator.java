public class Operator implements Employee
{
    private Company company;
    private static final double FIX_PART = 35000.0;
    private static final double monthSalary = FIX_PART;

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
