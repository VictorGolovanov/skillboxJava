
public class Loader
{
    public static void main(String[] args)
    {
        Cat barsik = new Cat();
        Cat begemot = new Cat();
        Cat lucifer = new Cat();
        Cat catInBoots = new Cat();
        Cat walkedByHimself = new Cat();

        System.out.println("1st Step:");
        System.out.println("1. Barsik weight BEFORE feeding is " + barsik.getWeight());
        System.out.println("2. Begemot weight BEFORE feeding is " + begemot.getWeight());
        System.out.println("3. Lucifer current weight is " + lucifer.getWeight());
        System.out.println("4. 'Cat in Boots' current weight is " + catInBoots.getWeight());
        System.out.println("5. 'Cat that walked by himself' current weight is " + walkedByHimself.getWeight());
        System.out.println();

        // кормим Барсика
        System.out.println("2st Step:");
        barsik.feed(333.0);
        System.out.println("Barsik weight AFTER feeding is " + barsik.getWeight());

        // кормим кота Воланда Бегемота
        begemot.feed(444.0);
        System.out.println("Begemot weight AFTER feeding is " + begemot.getWeight());
        System.out.println();

        // издеваемся над кошками :((( не надо так!
        // кот из Золушки Люцифер
        System.out.println("3rd Step (BAD):");
        System.out.println("Current Lucifer status is " + lucifer.getStatus());
        while(lucifer.getStatus() != "Exploded")
        {
            lucifer.feed(444.444);
        }
        System.out.println("Lucifer status after feeding is " + lucifer.getStatus() + ":(");
        System.out.println();

        // Кот в сапогах домяукался...
        System.out.println("4th Step (BAD):");
        System.out.println("Current 'Cat in boots' status is " + catInBoots.getStatus());
        while(catInBoots.getStatus() != "Dead")
        {
            catInBoots.meow(); // нужно ли, чтобы в консоль выводились все "мяу"?
        }
        System.out.println("After so many 'meow' Cat in boots' status is " + catInBoots.getStatus() + ":(");
        System.out.println();
        System.out.println("'Cat that walked by himself' didn't trust people, so its current status is "
                + walkedByHimself.getStatus());
    }
}