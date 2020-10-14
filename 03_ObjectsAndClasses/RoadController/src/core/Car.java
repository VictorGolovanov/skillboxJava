package core;

public class Car
{
    public String number; // done
    public int height; // done
    public double weight; // done
    public boolean hasVehicle; // for boolean too?
    public boolean isSpecial;  // for boolean too?

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public double getWeight() // Interesting, this line has been created automatically by IntelliJ IDEA :)
    {
        return weight;
    }

    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean isHasVehicle() { // getter without 'get' Is it correct?
        return hasVehicle;
    }

    public void setSpecial(boolean isSpecial){
        this.isSpecial = isSpecial;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
}