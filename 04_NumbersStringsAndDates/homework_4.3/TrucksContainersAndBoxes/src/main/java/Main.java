import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Хотелось написать приветсвенное сообщение, но тогда программа не проходит через тест
        // System.out.println("Сколько ящиков необходимо перевезти?");
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine(); // почему сразу не считывать целые числа через next.Int()?
        int box = Integer.parseInt(boxes); // переведем в нормальные числа
        int container;
        int truck;

        if(box % 27 == 0){
            container = box / 27;
        }
        else{
            container = (box / 27) + 1;
        }

        if(container % 12 == 0){
            truck = container / 12;
        }
        else{
            truck = (container / 12) + 1;
        }

        // Получилось слегка зубодробительно, но без тройного цикла я не придумал решение.
        int t = 1; // первый грузовик
        int c = 1; // первый контейнер
        int b = 1; // первый ящик
        while(t <= truck){
            System.out.println("Грузовик: " + t);
            int i = 1;
            while(i <= 12 & c <= container){
                System.out.println("\tКонтейнер: " + c);
                int j = 1;
                while (j <= 27 & b <= box){
                    System.out.println("\t\tЯщик: " + b);
                    j++;
                    b++;
                }
                i++;
                c++;
            }
            t++;
        }

        System.out.println("Необходимо:");
        System.out.println("грузовиков - " + truck + " шт.");
        System.out.println("контейнеров - " + container + " шт.");

        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

}
