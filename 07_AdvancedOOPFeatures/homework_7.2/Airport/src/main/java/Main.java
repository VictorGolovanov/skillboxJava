import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance(); // создаем аэропорт
        System.out.println("Appropriate flights:");
        System.out.println(findPlanesLeavingInTheNextTwoHours(airport));
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        List<Terminal> terminalList = airport.getTerminals(); // все терминалы аэропорта
        List<Flight> flightList = new ArrayList<>(); // все полеты
        for (Terminal terminal : terminalList) { // заполняем рейсами
            flightList.addAll(terminal.getFlights());
        }

        Calendar now = Calendar.getInstance();
        Calendar nextTwoHours = Calendar.getInstance();
        nextTwoHours.add(Calendar.HOUR, 2);

        return flightList.stream()
                .filter(f -> f.getDate().after(now.getTime()))
                .filter(f -> f.getDate().before(nextTwoHours.getTime()))
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
                .collect(Collectors.toList());
    }

}