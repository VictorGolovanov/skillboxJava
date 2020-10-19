
public class Loader
{
    public static void main(String[] args)
    {
        // это код для тестов котов :)

        Cat cat1 = new Cat(2134.5);
        System.out.println(cat1.getWeight());
        System.out.println();

        Cat barsik = new Cat(3000.0);
        System.out.println(barsik.getWeight());
        System.out.println(barsik.getDrinkAmount());
        Cat murka = new Cat();
        System.out.println(murka.getWeight());
        System.out.println();
        System.out.println(Cat.getCount());
        System.out.println();

        // create our kitten
        Cat murzik = getKitten();
        System.out.println("Murzik weight is " + murzik.getWeight());
        Cat pushistik = getKitten();
        System.out.println("Pushistik weight is " + pushistik.getWeight());
        Cat gav = getKitten();
        System.out.println("Gav weight is " + gav.getWeight());
        System.out.println();
        gav.feed(123.4);
        System.out.println("! " + gav.getEatenFood());
        System.out.println("Gav weight is " + gav.getWeight());
        System.out.println();
        System.out.println(Cat.getCount());
        System.out.println();

        // Pafnutiy
        Cat pafnutiy = getKitten();
        System.out.println();
        System.out.println(Cat.getCount());
        System.out.println();
        pafnutiy.setName("Пафнутий");
        pafnutiy.setColor(Color.BLACK_WITH_WHITE);
        Cat pahom = new Cat(pafnutiy);
        System.out.println(Cat.getCount());
        System.out.println();
        pafnutiy.feed(153.1);
        System.out.println(pahom.getName());
        System.out.println(pahom.getColor());
        System.out.println(pahom.getWeight());
        pahom.feed(123.4);
        System.out.println(pahom.getEatenFood());

        // проверяем, останется ли цел Пахом, если что-то станет с Пафнутием
        while(!pafnutiy.getStatus().equals("Exploded"))
        {
            pafnutiy.feed(2345.6);
        }
        System.out.println(Cat.getCount());
        System.out.println();
        // выясняем, что Пахом тоже оказадся перекормлен, хотя мы его покормили умеренным количеством еды
        System.out.println(pahom.getStatus());
        System.out.println(pahom.getEatenFood()); // при этом съел он по-прежнему только 123.4 г

    }
    private static Cat getKitten()
    {
        return new Cat(1100.0);
    }
}