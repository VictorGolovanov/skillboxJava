import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex
{
    private TreeMap<String, Set<String>> stations;
    private List<TreeSet<Station>> connections;
    private TreeSet<Line> lines;

    public StationIndex(){
        stations = new TreeMap();
        connections = new ArrayList<>();
        lines = new TreeSet<>();
    }

    public void addLine(Line line){
        lines.add(line);
    }

    public void addAllLineStations(Line line, List<Station> stations) {
        Set<String> stationsSet = stations.stream()
                .map(Station::getStationName)
                .collect(Collectors.toSet());
        this.stations.put(line.getNumber(), stationsSet);
    }

    public void addConnection(TreeSet<Station> stations) {
        if (!connections.contains(stations)) {
            connections.add(stations);
        }
    }

    public List<TreeSet<Station>> getConnections() {
        return connections;
    }
}
