import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        //System.out.println(staff);

        sortBySalaryAndAlphabet(staff);
        for(Employee employee : staff){
            System.out.println(employee);
        }
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
        /*Collections.sort(staff, ((o1, o2) -> {
            // если зарплаты одинаковые, то сраниваем по имени => == 0
            if(o1.getSalary().compareTo(o2.getSalary()) == 0)
            {
                return o1.getName().compareTo(o2.getName());
            }
            // иначе продолжаем сравнивать по
            else{
                return o1.getSalary().compareTo(o2.getSalary());
            }
        }));*/

        // действительно, совсем проще. По смыслу тоже самое, сначала по зарплате, потом по имени
        staff.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
    }
}