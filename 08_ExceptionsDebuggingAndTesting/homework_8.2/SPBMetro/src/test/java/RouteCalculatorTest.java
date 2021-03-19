import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase
{
    StationIndex stationIndex;
    RouteCalculator calculator;

    List<Station> noTransferRoute;
    List<Station> oneTransferRoute;
    List<Station> twoTransferRoute;

    Line tsarkayaLine;
    Line revolutionLine;
    Line technoLine;

    // станции 1 линии
    Station petrovskayaStation;
    Station ekateriniskayaStation;
    Station nikolaevskayaStation;

    // станции 2 линии
    Station leninskayaStation;
    Station oktyabrskayaStation;
    Station revolutionStation;

    // станции 3 линии
    Station vychislitelnayaStation;
    Station engineerStation;
    Station rocketStation;


    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();

        noTransferRoute = new ArrayList<>();
        oneTransferRoute = new ArrayList<>();
        twoTransferRoute = new ArrayList<>();


        // создаем три линии примерно так
        // 1
        // 1
        // 1 - переход - 2
        //               2
        //               2 - переход - 3
        //                             3
        //                             3

        tsarkayaLine = new Line(1, "Царская");
        revolutionLine = new Line(2, "Революционная");
        technoLine = new Line(3, "Технологическая");

        // станции 1 линии
        petrovskayaStation = new Station("Петровская", tsarkayaLine);
        ekateriniskayaStation = new Station("Екатерининская", tsarkayaLine);
        nikolaevskayaStation = new Station("Николаевская", tsarkayaLine);

        // станции 2 линии
        leninskayaStation = new Station("Ленинская", revolutionLine);
        oktyabrskayaStation = new Station("Октябрьская", revolutionLine);
        revolutionStation = new Station("Площадь Революции", revolutionLine);

        // станции 3 линии
        vychislitelnayaStation = new Station("Вычислительная", technoLine);
        engineerStation = new Station("Инженерная", technoLine);
        rocketStation = new Station("Ракетная", technoLine);

        stationIndex = new StationIndex();

        stationIndex.addLine(tsarkayaLine);
        stationIndex.addLine(revolutionLine);
        stationIndex.addLine(technoLine);

        stationIndex.addStation(petrovskayaStation);
        stationIndex.addStation(ekateriniskayaStation);
        stationIndex.addStation(nikolaevskayaStation);
        stationIndex.addStation(leninskayaStation);
        stationIndex.addStation(oktyabrskayaStation);
        stationIndex.addStation(revolutionStation);
        stationIndex.addStation(vychislitelnayaStation);
        stationIndex.addStation(engineerStation);
        stationIndex.addStation(rocketStation);


        // добавляем соединения станций
        // переход с 1 линии на 2 линию
        List<Station> connectionLinesOneAndTwo = new ArrayList<>();
        connectionLinesOneAndTwo.add(nikolaevskayaStation);
        connectionLinesOneAndTwo.add(leninskayaStation);
        stationIndex.addConnection(connectionLinesOneAndTwo);

        // переход со 2 линии на 3 линию
        List<Station> connectionLinesTwoAndThree = new ArrayList<>();
        connectionLinesTwoAndThree.add(revolutionStation);
        connectionLinesTwoAndThree.add(vychislitelnayaStation);
        stationIndex.addConnection(connectionLinesTwoAndThree);

        // почему-то печатает три раза
        System.out.println(stationIndex.getConnectedStations(nikolaevskayaStation));
        System.out.println(stationIndex.getConnectedStations(leninskayaStation));


        // маршурт без пересадок - 5 минут
        noTransferRoute.add(petrovskayaStation);
        noTransferRoute.add(ekateriniskayaStation);
        noTransferRoute.add(nikolaevskayaStation);

        // маршрут с 1 пересадкой 5 минут + 3.5 минуты + 5 минут = 13.5 минут
        oneTransferRoute.add(petrovskayaStation);
        oneTransferRoute.add(ekateriniskayaStation);
        oneTransferRoute.add(nikolaevskayaStation);
        oneTransferRoute.add(leninskayaStation);
        oneTransferRoute.add(oktyabrskayaStation);
        oneTransferRoute.add(revolutionStation);

        // маршрут с 2 персадками 5 минут + 3.5 минуты + 5 минут + 3.5 минуты + 5 минут = 22 минуты
        twoTransferRoute.add(petrovskayaStation);
        twoTransferRoute.add(ekateriniskayaStation);
        twoTransferRoute.add(nikolaevskayaStation);
        twoTransferRoute.add(leninskayaStation);
        twoTransferRoute.add(oktyabrskayaStation);
        twoTransferRoute.add(revolutionStation);
        twoTransferRoute.add(vychislitelnayaStation);
        twoTransferRoute.add(engineerStation);
        twoTransferRoute.add(rocketStation);

        for(int i = 0; i < twoTransferRoute.size(); i++){
            System.out.println(twoTransferRoute.get(i));
        }

    }

    public void testCalculateDurationNoTransfer(){
        double actual = RouteCalculator.calculateDuration(noTransferRoute);
        double expected = 5.0;
        assertEquals(expected, actual);
    }

    public void testCalculateDurationOneTransfer(){
        double actual = RouteCalculator.calculateDuration(oneTransferRoute);
        double expected = 13.5;
        assertEquals(expected, actual);
    }

    public void testCalculateDurationTwoTransfer(){
        double actual = RouteCalculator.calculateDuration(twoTransferRoute);
        double expected = 22.0;
        assertEquals(expected, actual);
    }

    // приватные методы тестируем через метод getShortestRoute
    public void testGetRouteOnTheLine(){
        calculator = new RouteCalculator(stationIndex);
        List<Station> actualRoute = calculator.getShortestRoute(petrovskayaStation, nikolaevskayaStation);
        List<Station> expectedRoute = noTransferRoute;
        assertEquals(expectedRoute, actualRoute);
    }

    public void testGetRouteWithOneConnection(){
        calculator = new RouteCalculator(stationIndex);
        List<Station> actualRoute = calculator.getShortestRoute(petrovskayaStation, revolutionStation);
        List<Station> expectedRoute = oneTransferRoute;
        assertEquals(expectedRoute, actualRoute);
    }

    public void testGetRouteWithTwoConnections(){
        calculator = new RouteCalculator(stationIndex);
        List<Station> actualRoute = calculator.getShortestRoute(petrovskayaStation, rocketStation);
        List<Station> expectedRoute = twoTransferRoute;
        assertEquals(expectedRoute, actualRoute);
    }
}
