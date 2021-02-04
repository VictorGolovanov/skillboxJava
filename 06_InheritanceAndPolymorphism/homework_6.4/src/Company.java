import java.util.*;

public class Company {
    private static final double MIN_INCOME =  8000000.0;
    private static final double MAX_INCOME = 15000000.0;

    private static final ArrayList<Employee> employees = new ArrayList<>();

    public void hire(Employee employee)
    {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> employeeCollection)
    {
        employees.addAll(employeeCollection);
    }

    public void fire(Employee employee)
    {
        employees.remove(employee);
    }

    public static int getIncome()
    {
        return (int)(Math.random() * (MAX_INCOME - MIN_INCOME) + MIN_INCOME);
    }

    // создаем отсортированный список нужного размера по компаратору, чтобы уже использовать в нужном методе
    private List<Employee> getSortedList(int count, Comparator<Employee> comparator) {
        List<Employee> copyList = new ArrayList<>(employees);
        copyList.sort(comparator);
        List<Employee> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(copyList.get(i));
        }
        return result;
    }

    public List<Employee> getTopSalaryStaff(int count, int monthCompanyIncome) {
        return getSortedList(count, (o1, o2)
                -> (int) (o2.getMonthSalary(monthCompanyIncome) - o1.getMonthSalary(monthCompanyIncome)));
    }

    public List<Employee> getLowestSalaryStaff(int count, int monthCompanyIncome) {
        return getSortedList(count, (o1, o2)
                -> (int) (o1.getMonthSalary(monthCompanyIncome) - o2.getMonthSalary(monthCompanyIncome)));
    }

    public int countEmployees() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}