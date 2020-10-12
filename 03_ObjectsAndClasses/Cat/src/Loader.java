
public class Loader
{
    public static void main(String[] args)
    {
        Cat barsik = new Cat();

        // учитываем съеденное
        System.out.println("1st Step:");
        System.out.println("Barsik weight BEFORE feeding is " + barsik.getWeight());
        barsik.feed(150.0);
        System.out.println("Barsik weight AFTER feeding is " + barsik.getWeight());
        System.out.println("Barsik ate " + barsik.getEatenFood() + " g of food! :) Good boy!");
        System.out.println();

        // отводим в туалет
        System.out.println("2st Step:");
        System.out.println("Barsik weight BEFORE 'pee-pee' is " + barsik.getWeight() + " g.");
        for(int i = 3; i > 0; i--)
        {
            barsik.pee();
        }
        System.out.println("Barsik weight AFTER 'pee-pee' is " + barsik.getWeight() + " g.");
        System.out.println();
    }
}