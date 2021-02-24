import java.util.*;

public class Company {
    /*private static final double MIN_INCOME =  8000000.0;
    private static final double MAX_INCOME = 15000000.0;*/
    private static final ArrayList<Employee> employees = new ArrayList<>();

    public void hire(Employee employee)
    {
        employees.add(employee);
        employee.setCompany(this);
    }

    public void hireAll(Collection<Employee> employeeCollection)
    {
        employees.addAll(employeeCollection);
        for(Employee employee : employeeCollection)
        {
            employee.setCompany(this);
        }
    }

    public void fire(Employee employee)
    {
        employees.remove(employee);
        employee.setCompany(null);
    }

    public static int getIncome()
    {
        int profit = 0;
        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                profit += ((Manager) employee).getProfitForCompany();
            }
        }
        return profit;
        /*return (int)(Math.random() * (MAX_INCOME - MIN_INCOME) + MIN_INCOME);*/
    }

    // создаем отсортированный список нужного размера по компаратору, чтобы уже использовать в нужном методе
    /*private List<Employee> getSortedList(int count, Comparator<Employee> comparator) {
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
    }*/

    // метод, который сортирует и печатает нужное количество зарплат
    private void getSortedList(int count, Comparator<Employee> comparator) {
        List<Employee> copyList = new ArrayList<>(employees);
        copyList
                .stream()
                .sorted(comparator)
                .limit(count)
                .forEach(System.out::println);
    }

    // а тут ссылаемся на getSortedList

    public void getTopSalaryStaff(int count)
    {
        getSortedList(count, employees.sort(Comparator.comparing(Employee::getMonthSalary)).reversed());
    }
    public void getLowestSalaryStaff(int count)
    {
        getSortedList(count, employees.sort(Comparator.comparing(Employee::getMonthSalary)));
    }

    public int countEmployees() {
        return employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}