import java.time.ZoneId;
import java.util.*;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {

        Optional<Employee> optional = staff
                .stream()
                // объект класса Date получаем методом getWorkStart() и переводим его в LocalDate,
                // затем вызываем метод getYear, который возвращает int и его сравниваем с year
                .filter(e -> e.getWorkStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == year)
                .max(Comparator.comparing(Employee::getSalary));

        return optional.orElse(null);
    }
}