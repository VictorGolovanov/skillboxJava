
public class Loader
{
    public static void main(String[] args)
    {
        // это код для тестов котов :)

        Cat cat1 = new Cat(2134.5);
        System.out.println("1st cat weight is " + cat1.getWeight());
    }
    private static Cat getKitten()
    {
        return new Cat(1100.0);
    }
}