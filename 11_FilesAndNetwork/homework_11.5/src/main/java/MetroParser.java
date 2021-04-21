import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MetroParser {

    private Document document;
    private Elements elements;
    private StationIndex metroMap;
    private Map<String, Station> allStations;
    private String PATH;


    public MetroParser(String LINK, String PATH) throws IOException {
        document = Jsoup.connect(LINK).maxBodySize(0).get();
        elements = document.select("div#metrodata");
        this.PATH = PATH;
        metroMap = new StationIndex();
        allStations = new LinkedHashMap<>();
        buildMetroMap();
        metroMapToJSON(metroMap, PATH);
    }

    private void metroMapToJSON(StationIndex metroMap, String PATH) throws IOException{
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        FileWriter toJson = new FileWriter(PATH);
        gson.toJson(metroMap, toJson);
        toJson.flush();
    }

    private void buildMetroMap(){
        // вызываем методы по постройке линий, станций и т.д. Пока без переходов
        List<Line> linesList = parseLines();
        linesList.forEach((line) -> {
            metroMap.addLine(line);
            List<Station> stationsLine = parseStations(line);
            metroMap.addAllLineStations(line, stationsLine);
            stationsLine.forEach(el -> allStations.put(el.getStationName(), el));
        });
    }

    // получаем станции конкретной линии
    private List<Station> parseStations(Line line) {
        Elements stations = elements.select("div.js-metro-stations.t-metrostation-list-table[data-line = " + line.getNumber() + "]").select("span.name");
        List<String> stationsNames = stations.stream().map(Element::text).collect(Collectors.toList());
        List<Station> stationList = stationsNames.stream().map(x -> new Station(x, line)).collect(Collectors.toList());
        return stationList;
    }

    //  получаем все линии
    private List<Line> parseLines(){
        List<Line> linesList = new ArrayList<>();
        Elements lines = elements.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln");
        LinkedHashMap<String, String> linesNames = lines.stream()
                .collect(Collectors.toMap(k -> k.attr("data-line"), Element::text, (a, b) -> a, LinkedHashMap::new));

        linesNames.forEach((k, v) -> linesList.add(new Line(k, v)));
        return linesList;
    }
}
