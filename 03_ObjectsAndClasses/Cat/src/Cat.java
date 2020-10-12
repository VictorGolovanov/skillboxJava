
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double eatenAmount; // переменная для учета съеденного котами

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }

    public void meow()
    {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        weight = weight + amount;
    }

    public void drink(Double amount)
    {
        weight = weight + amount;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }
    public Double getEatenFood() // возвращаем количество съеденной еды
    {
        eatenAmount = weight - originWeight;
        return eatenAmount;
    }
    public void pee()
    {
        weight = weight - weight/575;
        System.out.println("    Master, I did something... Clean my toilet, now!!!");
    }
}