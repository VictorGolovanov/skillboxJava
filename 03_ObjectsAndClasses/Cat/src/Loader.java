
public class Loader
{
    public static void main(String[] args)
    {
        Cat barsik = new Cat();
        Cat barsik2 = new Cat();
        Cat barsik3 = new Cat();
        Cat barsik4 = new Cat();
        System.out.println(Cat.getCount());

        barsik3.feed(1.0);
        barsik3.meow();
        barsik3.meow();
        barsik3.meow();
        barsik4.feed(200.0);
        System.out.println(barsik4.getEatenFood());
        System.out.println(barsik3.getEatenFood());


        barsik.feed(1000.0);
        while (!barsik.getStatus().equals("Dead"))
        {
            barsik.meow();
        }
        System.out.println(barsik.getEatenFood());
        barsik.pee();

        while(!barsik2.getStatus().equals("Exploded"))
        {
            barsik2.feed(1000.0);
        }
        System.out.println(barsik2.getEatenFood());
        System.out.println(Cat.getCount());
        barsik2.pee();
        System.out.println(barsik2.getWeight());
    }
}