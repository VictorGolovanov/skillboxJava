
public class Loader
{
    public static void main(String[] args)
    {
        Cat barsik = new Cat();
        Cat barsik2 = new Cat();
        Cat barsik3 = new Cat();
        Cat barsik4 = new Cat();
        System.out.println(Cat.getCount());

        System.out.println(barsik.getEatenFood());
        barsik.feed(150.0);
        System.out.println(barsik.getEatenFood());
        barsik.feed(150.0);
        barsik.drink(10.0);
        System.out.println(barsik.getEatenFood());
        barsik.meow();
        // не очень логично, конечно, что после мяукания уменьшилось кол-во съеденного
        System.out.println(barsik.getEatenFood());
    }
}