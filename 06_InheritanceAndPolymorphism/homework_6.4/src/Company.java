import java.util.ArrayList;
import java.util.Collection;

public class Company {

    private static final double MIN_INCOME =  8000000.0;
    private static final double MAX_INCOME = 15000000.0;

    private static final ArrayList<Employee> employees = new ArrayList<>();

    // нанимаем одного сотрудника - аргумент - сотрудник - туда передадим уже кого нужно => Manager, topManager & Operator
    public void hire(Employee employee)
    {
        employees.add(employee);
    }

    // нанимаем группу сотрудников => передаем коллекцию
    public void hireAll(Collection<Employee> employeeCollection)
    {
        employees.addAll(employeeCollection);
    }

    // увольняем переданного сотрудника
    public void fire(Employee employee)
    {
        employees.remove(employee);
    }

    public static int getIncome()
    {
        // вопрос: почему, если вынести расчеты в класс, то при создании двух экземпляров класса у них одинаковых доход получается?
        // разве не считается эта переменная каждый раз заново?
        int MonthIncome = (int)(Math.random() * (MAX_INCOME - MIN_INCOME) + MIN_INCOME);
        return MonthIncome;
    }
}