
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double eatenAmount; // переменная для учета съеденного котами
    private static int count = 0; // переменная счетчик количества кошек

    public Cat()
    {
        count++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        eatenAmount = 0.0; // предположим, что кошка еще ничего не ела и не пила

    }

    public void meow()
    {
        if(weight >= maxWeight || weight <= minWeight) // этим условием проверяем, существует ли кот на данный момент
        {
            System.out.println("Unexisting creature cannot do anything!");
        }
        else {
            weight = weight - 1;
            System.out.println("Meow");
        }
    }

    public void feed(Double amount)
    {
        if(weight >= maxWeight || weight <= minWeight)
        {
            System.out.println("Unexisting creature cannot do anything!");
        }
        else{
            weight = weight + amount;
        }
    }

    public void drink(Double amount)
    {
        if(weight >= maxWeight || weight <= minWeight)
        {
            System.out.println("Unexisting creature cannot do anything!");
        }
        else {
            weight = weight + amount;
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            count--;
            return "Dead";
        }
        else if(weight > maxWeight) {
            count--;
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
        // т.к. всякое увеличение веса связано с питанием и питьем:
        eatenAmount = weight - originWeight; // предположим, что сюда входит съеденное и выпитое
        eatenAmount = +eatenAmount; // чтобы складывать все съеденное и выпитое
        if(eatenAmount >= 0)
        {
            return eatenAmount;
        }
        else // если кот после приема пищи помяукал и его вес ушел в минус,
             // чтобы не было возвращено отрицательное значение
            {
                return null;
            }
    }

    public void pee()
    {
        if(weight >= maxWeight || weight <= minWeight)
        {
            System.out.println("Unexisting creature cannot do anything!");
        }
        else
        {
            weight = weight - weight/575;
            System.out.println("    Master, I did something... Clean my toilet, now!!!");
        }
    }
    public static int getCount()
    {
        return count;
    }
}