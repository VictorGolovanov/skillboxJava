import com.skillbox.airport.Airport;

public class airport
{
    public static void main(String[] args) {
        Airport domodedovo = Airport.getInstance();
        System.out.println("*************************");

        // list of aircrafts
        System.out.println(domodedovo.getAllAircrafts());
        System.out.println("*************************");

        // number of aircrafts
        System.out.println("In Domodedovo there are " + domodedovo.getAllAircrafts().size() + " aircrafts now.");
    }
}
