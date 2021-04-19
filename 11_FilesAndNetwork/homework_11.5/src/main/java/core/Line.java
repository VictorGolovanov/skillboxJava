package core;

public class Line implements Comparable<Line>
{
    private String number;
    private String name;

    public Line(String number, String name){
        this.number = number;
        this.name = name;
    }

    public String getNumber(){
        return this.number;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return "номер линии: " + this.number + ". название линии: " + this.name;
    }

    @Override
    public int compareTo(Line line) {
        return this.getNumber().compareTo(line.getNumber());
    }
}