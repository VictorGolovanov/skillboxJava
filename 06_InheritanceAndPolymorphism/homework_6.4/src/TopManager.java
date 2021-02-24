public class TopManager implements Employee
{
    private Company company;
    private static final double FIX_PART = 100000.0;
    private static final double BONUS_PERCENT = 1.5;
    private static final double LEVEL_FOR_BONUS = 10000000;

    @Override
    public double getMonthSalary(int monthCompanyIncome) {
        if(monthCompanyIncome > LEVEL_FOR_BONUS){
            //System.out.println(monthCompanyIncome);
            return FIX_PART + (FIX_PART * BONUS_PERCENT);
        }
        else{
            //System.out.println(monthCompanyIncome);
            return FIX_PART;
        }
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
