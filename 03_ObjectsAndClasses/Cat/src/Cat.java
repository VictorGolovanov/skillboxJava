
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double eatenAmount; // переменная для учета съеденного котами
    private double drinkAmount; // давайте учтем и что кот выпил :)
    private static int count = 0; // переменная счетчик количества кошек
    private boolean isAlive; // состояние кота
    private String name;

    public static final int LEGS_COUNT = 4;
    public static final int TAIL_COUNT = 1;
    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;

    public Cat()
    {
        count++;
        //
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = MIN_WEIGHT;
        maxWeight = MAX_WEIGHT;
        isAlive = true;
        eatenAmount = 0.0; // предположим, что кошка еще ничего не ела и не пила

    }

    public Cat(Double weight)
    {
        this();
        this.weight = weight;
    }

    public Cat(String name)
    {
        this();
        this.name = name;
    }

    public String getName()
        {
            return name;
        }

    public void meow()
    {
        if(!isAlive) // так видимо лучше?
        //(weight >= maxWeight || weight <= minWeight) // этим условием проверяем, существует ли кот на данный момент
        {
            System.out.println("Unexisting creature can't meow :(");
        }
        else {
            weight = weight - 1;
            System.out.println("Meow");
        }
    }

    public void feed(Double amount)
    {
        if(isAlive)
        {
            weight = weight + amount;    // Так подсказала записать среда разработки
            isAlive = isWeightNormal();  // я сначала написал if else со скобками
            eatenAmount += amount;
            if(!isAlive)
            {
                count--;
            }
        }
        else
            {
                System.out.println("Unexisting creature can't eat :(");
            }
    }

    public void drink(Double amount)
    {
        if(!isAlive)
        {
            System.out.println("Unexisting creature can't drink :(");
        }
        else {
            weight = weight + amount;
            drinkAmount += amount;
        }
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
        return eatenAmount;
    }

    public Double getDrinkAmount() // возвращаем количество выпитого
    {
        return drinkAmount;
    }

    public void pee()
    {
        if(!isAlive)
        {
            System.out.println("Unexisting creature can't go to the toilet :(");
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

    public boolean isWeightNormal()
    {
        return (weight> MIN_WEIGHT && weight <MAX_WEIGHT);
    }
}