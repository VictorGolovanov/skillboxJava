import core.Line;
import core.Station;
import java.util.*;
import java.util.stream.Collectors;

public class StationIndex
{
    private LinkedHashMap<String, List<String>> stations;
    private List<TreeSet<Station>> connections;
    private List<Line> lines;

    public StationIndex(){
        stations = new LinkedHashMap<>();
        connections = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public void addLine(Line line){
        lines.add(line);
    }

    public void addAllLineStations(Line line, List<Station> stations) {
        List<String> stationsList = stations.stream()
                .map(Station::getStationName)
                .collect(Collectors.toList());
        this.stations.put(line.getNumber(), stationsList);
    }


    public void addConnection(TreeSet<Station> stations) {
        if (!connections.contains(stations)) {
            connections.add(stations);
        }
    }
}
