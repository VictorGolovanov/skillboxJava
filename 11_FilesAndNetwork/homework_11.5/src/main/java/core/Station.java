package core;

public class Station implements Comparable<Station>
{
    private String lineNumber;
    private String stationName;

    public Station(String stationName, Line line){
        this.stationName = stationName;
        this.lineNumber = line.getNumber();
    }

    public String getNumber(){
        return lineNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public String toString(){
        return "название станции: " + stationName;
    }

    @Override
    public int compareTo(Station station) {
        if (this.getNumber().equals(station.getNumber())) {
            return this.getStationName().compareTo(station.getStationName());
        } else {
            return this.getNumber().compareTo(station.getNumber());
        }
    }

}