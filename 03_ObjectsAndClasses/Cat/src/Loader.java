
public class Loader
{
    public static void main(String[] args)
    {
        // это код для тестов котов :)
        Cat barsik = new Cat(3000.0);
        System.out.println(barsik.getWeight());
        System.out.println(barsik.getDrinkAmount());
        Cat murka = new Cat();
        System.out.println(murka.getWeight());

        // create our kitten
        Cat murzik = getKitten();
        System.out.println("Murzik weight is " + murzik.getWeight());
        Cat pushistik = getKitten();
        System.out.println("Pushistik weight is " + pushistik.getWeight());
        Cat gav = getKitten();
        System.out.println("Gav weight is " + gav.getWeight());

        gav.meow();
        gav.feed(150.501);
        System.out.println(gav.getWeight());
        System.out.println(gav.getEatenFood());
    }
    private static Cat getKitten()
    {
        return new Cat(1100.0);
    }
}