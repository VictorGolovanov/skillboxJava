import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class SiteMapper extends RecursiveTask<LinkedHashSet<WebPage>>
{
    private WebPage parent;
    private volatile LinkedHashSet<WebPage> websiteMap = new LinkedHashSet<>();

    public SiteMapper(WebPage parent){
        this.parent = parent;
    }

    private synchronized LinkedHashSet<WebPage> getChildren(WebPage parent){ //private Set<WebPage> getChildren(WebPage parent){
        try{
            Document doc = Jsoup.connect(parent.getUrl()).maxBodySize(0).timeout(10000).get();
            Elements elements = doc.select("a[href]");
            LinkedHashSet<String> urls = new LinkedHashSet<>();
            elements.stream()
                    .map(e -> e.attr("abs:href"))
                    .filter(e -> !e.equals(parent.getUrl()))
                    .filter(e -> e.startsWith(parent.getUrl()))
                    .filter(e -> !e.contains("#") && !e.contains("?") && !e.contains("'"))
                    .filter(e -> !e.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)"))
                    .forEachOrdered(urls::add);

            for(String url : urls){
                WebPage page = new WebPage(url, parent);
                if(!websiteMap.contains(page)){
                    parent.addChildren(page);
                }
            }
            Thread.sleep(500);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return parent.getChildrenPages();
    }

    @Override
    protected LinkedHashSet<WebPage> compute() {
        synchronized (websiteMap){
            websiteMap.add(parent);
            Set<WebPage> children = this.getChildren(parent);
            Set<SiteMapper> taskList = Collections.synchronizedSet(new LinkedHashSet<>());
            for(WebPage page : children){
                taskList.add((SiteMapper) new SiteMapper(page).fork());
                for (SiteMapper task : taskList) {
                    websiteMap.addAll(task.join());
                }
            }
        }
        return websiteMap;
    }

}
