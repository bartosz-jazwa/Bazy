package robot;

import com.mongodb.client.MongoCollection;
import mongo.utils.MongoUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Robot {
    public static void main(String[] args) {
        MongoCollection<org.bson.Document> found = MongoUtils
                .getInstance()
                .getClient()
                .getDatabase("pages")
                .getCollection("found");

        String searchText = "BMW";

        Set<String> urlToSearch = new HashSet<>();
        urlToSearch.add("https://OLX.pl");
        Set<String> urlUsed = new HashSet<>();
        Iterator<String> iterator = urlToSearch.iterator();

        while (urlToSearch.iterator().hasNext()) {
            String url = urlToSearch.iterator().next();
            if (!urlUsed.contains(url)) {
                urlUsed.add(url);

                try {
                    System.out.println("szukanie w " + url);
                    Document page = Jsoup.connect(url).followRedirects(true).get();

                    String content = page.text();
                    if (content.contains(searchText)) {
                        org.bson.Document foundPage = new org.bson.Document("url",url)
                                .append("content",content)
                                .append("date",new Date());
                        found.insertOne(foundPage);
                    }
                    Elements elements = page.select("a");
                    for (Element link:elements) {
                        String href = link.absUrl("href");
                        if (href!=null && href.length()>0){
                            urlToSearch.add(href);
                        }
                    }

                } catch (Exception e) {
                    System.out.println("blad " + url);
                }
            }
        }
    }
}
