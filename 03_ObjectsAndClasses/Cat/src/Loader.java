
public class Loader
{
    public static void main(String[] args)
    {
        // это код для тестов котов :)
        Cat barsik = new Cat();
        Cat barsik2 = new Cat();
        Cat barsik3 = new Cat();
        Cat barsik4 = new Cat();
        System.out.println();
        System.out.println(Cat.getCount());
        System.out.println();
        System.out.println(barsik.getWeight());
        barsik.meow();
        System.out.println(barsik.getEatenFood());
        barsik.feed(100.0);
        System.out.println(barsik.getEatenFood());
        barsik.feed(100.0);
        System.out.println(barsik.getEatenFood());
        barsik.meow();
        System.out.println(barsik.getEatenFood());
        System.out.println(barsik.getStatus());
        barsik.drink(12.0);
        System.out.println(barsik.getDrinkAmount());
        barsik.drink(12.0);
        System.out.println(barsik.getDrinkAmount());
        barsik.feed(10000.0);
        System.out.println(barsik.getWeight());
        System.out.println(barsik.isWeightNormal());
        System.out.println(Cat.getCount());
        System.out.println(barsik.getStatus());
        System.out.println(Cat.getCount());
        barsik.meow();
        barsik.pee();
        barsik.feed(100.0);
        barsik.drink(20.0);
    }
}