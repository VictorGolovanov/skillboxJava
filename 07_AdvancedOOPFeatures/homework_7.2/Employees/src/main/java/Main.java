import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year
        Date workYearStart = new Date();
        workYearStart.setYear(year);

        Optional<Employee> optional = staff
                .stream()
                .filter(e -> e.getWorkStart().equals(workYearStart))
                .max(Comparator.comparing(Employee::getSalary));

        // return optional.orElse(null);
        if (optional.isPresent())
        {
            return optional.get();
        }
        else{
            return null;
        }
    }
}