public class Main {
    public static void main(String[] args) {
        Company sellCompany = new Company();

        for (int i = 0; i < 180; i++) {
            Employee operator = new Operator();
            sellCompany.hire(operator);
        }
        for (int i = 0; i < 80; i++) {
            Employee manager = new Manager();
            sellCompany.hire(manager);
        }
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager();
            sellCompany.hire(topManager);
        }
        System.out.println("Количество сотрудников: " + sellCompany.countEmployees());
        System.out.println();

        int incomeCompany = sellCompany.getIncome();
        System.out.println("Доход компании: " + incomeCompany + " рублей.");
        System.out.println();

        System.out.println("10 самых высоких зарплат:");
        for (Employee employee : sellCompany.getTopSalaryStaff(10)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        System.out.println();

        System.out.println("10 самых маленьких зарплат:");
        for (Employee employee : sellCompany.getLowestSalaryStaff(10)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        System.out.println("Владелец компании решил сократить половину сотрудников.");
        System.out.println("Так как в компании нет профсоюза, то задуманное удалось.");

        int countEmployees = sellCompany.countEmployees();
        for (int i = 0; i < countEmployees / 2; i++) {
            int index = (int) (Math.random() * sellCompany.countEmployees());
            Employee toFire = sellCompany.getEmployees().get(index);
            sellCompany.fire(toFire);
        }
        System.out.println("Уволено " + countEmployees / 2 + " сотрудников");

        System.out.println();

        System.out.println("10 самых высоких зарплат:");
        for (Employee employee : sellCompany.getTopSalaryStaff(10)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        System.out.println();

        System.out.println("10 самых маленких зарплат:");
        for (Employee employee : sellCompany.getLowestSalaryStaff(10)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        System.out.println("Количество сотрудников: " + sellCompany.countEmployees());
    }
}