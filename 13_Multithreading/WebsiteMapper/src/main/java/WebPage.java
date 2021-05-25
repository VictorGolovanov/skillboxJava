import java.util.*;

public class WebPage implements Comparable<WebPage>
{

    private String url;
    private WebPage parent;
    private volatile LinkedHashSet<WebPage> childrenPages = new LinkedHashSet<>();
    private int level;

    public WebPage(String url){
        this.url = url;
        parent = null;
        this.level = 0;
    }

    public WebPage(String url, WebPage parent){
        this.url = url;
        this.parent = parent;
        this.level = parent.getLevel() + 1;
    }

    public void addChildren(WebPage childrenPage){
        childrenPages.add(childrenPage);
    }


    public String getUrl() {
        return url;
    }

    public WebPage getParent(){
        return parent;
    }

    public LinkedHashSet<WebPage> getChildrenPages() {
        return childrenPages;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(WebPage page) {
        if(this.getParent() == null){
            return -1;
        }
        if(page.getParent() == null){
            return 1;
        }
        if(page.getParent().equals(this)){
            return -1;
        }
        if(this.getParent().equals(page)){
            return 1;
        }

        if(this.getLevel() == page.getLevel()){
            if(this.getParent().equals(page.getParent())){
                return this.getUrl().compareTo(page.getUrl());
            }
            else{
                return this.getParent().compareTo(page.getParent());
            }
        }
        else{
            return (this.getLevel() > page.getLevel() ? this.getParent().compareTo(page) : this.compareTo(page.getParent()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebPage page = (WebPage) o;

        return Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }



    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\t".repeat(Math.max(0, this.getLevel())));
        return builder.append(this.getUrl()).toString();
    }
}
