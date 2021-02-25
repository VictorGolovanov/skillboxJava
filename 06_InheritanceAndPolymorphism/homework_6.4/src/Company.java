import java.util.*;

public class Company {
    private ArrayList<Employee> employees = new ArrayList<>();

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

    public int getIncome()
    {
        int profit = 0;
        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                profit += ((Manager) employee).getProfitForCompany();
            }
        }
        return profit;
    }


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