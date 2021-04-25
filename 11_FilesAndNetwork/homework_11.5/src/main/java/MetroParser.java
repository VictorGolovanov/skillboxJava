import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Line;
import core.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MetroParser {

    private Document document;
    private Elements elements;
    private StationIndex metroMap;
    private Map<String, Station> allStations;
    private List<Line> linesList;
    private final String PATH; // for JSON-file
    private List<TreeSet<Station>> allConnections;


    public MetroParser(String LINK, String PATH) throws IOException {
        document = Jsoup.connect(LINK).maxBodySize(0).get();
        elements = document.select("div#metrodata");
        this.PATH = PATH;
        metroMap = new StationIndex();
        allStations = new LinkedHashMap<>();
        allConnections = new ArrayList<>();
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
        linesList = parseLines();
        linesList.forEach((line) -> {
            metroMap.addLine(line);
            List<Station> stationsLine = parseStations(line);
            metroMap.addAllLineStations(line, stationsLine);
            stationsLine.forEach(el -> allStations.put(el.getStationName(), el));
        });
        allConnections = parseConnections(linesList, allStations);
        allConnections.forEach(x -> metroMap.addConnection(x));
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

    private List<TreeSet<Station>> parseConnections(List<Line> lineList, Map<String, Station> stationList) {
        List<TreeSet<Station>> result = new ArrayList<>();
        lineList.forEach(line -> {
            Elements connections = elements.select("div.js-metro-stations.t-metrostation-list-table[data-line = " + line.getNumber() + "]").select("p");
            List<TreeSet<Station>> connectionsLine = connections.stream().map(stations -> {
                TreeSet<Station> stationSet = new TreeSet<>();
                stationSet.add(stationList.get(stations.select("span.name").text()));
                for (Line innerLine : lineList) {
                    if (extractStationName(stations, innerLine) != null) {
                        stationSet.add(stationList.get(extractStationName(stations, innerLine)));
                    }
                }
                return stationSet;
            }).filter(s -> s.size() > 1).collect(Collectors.toList());
            result.addAll(connectionsLine);
        });
        return result;
    }

    private String extractStationName(Element element, Line line) {
        String title = element.select("span.ln-" + line.getNumber()).attr("title");
        if (!title.isBlank() && !title.isEmpty()) {
            int begin = title.indexOf("«") + 1;
            int end = title.lastIndexOf("»");
            return title.substring(begin, end);
        } else return null;
    }

    public void printResult() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");
        JSONArray connectionsArray = (JSONArray) jsonData.get("connections");

        stationsObject
                .keySet()
                .stream()
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(((String)s)
                .replaceAll("[^\\d]", ""))))
                .forEachOrdered(lineNumberObject -> {
                    JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
                    int stationsCount = stationsArray.size();
                    System.out.println("Номер линиии " + lineNumberObject + " - колличество станций : " + stationsCount);
        });

        System.out.println();
        System.out.println("Переходы с тремя и более станциями считаются за один! Пока не решил как иначе посчитать.");
        System.out.println("Количество переходов в метро: " + connectionsArray.size());

        /*List<Station> connectionStations = new ArrayList<>();
        connectionsArray.forEach(connectionObject ->{
            JSONArray connection = (JSONArray) connectionObject;
            //List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                //String lineNumber = ((String) itemObject.get("lineNumber"));
                String stationName = (String) itemObject.get("stationName");

                Station station = new Station(stationName);
                connectionStations.add(station);
            });
        });
        System.out.println(connectionStations.size());*/
    }

    private String getJsonFile(){
        StringBuilder builder = new StringBuilder();
        try{
            List<String> jsonLines = Files.readAllLines(Paths.get(PATH));
            jsonLines.forEach(line -> builder.append(line).append("\n")); // (builder::append)
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return builder.toString();
    }

}
