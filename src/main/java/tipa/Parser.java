package tipa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

public class Parser {
    public static Elements getPage() throws Exception{
        String url = "https://smart-lab.ru/q/shares/order_by_issue_capitalization_usd/desc/";
        Document page = Jsoup.parse(new URL(url), 3000);
        Element table = page.select("table[class=simple-little-table trades-table]").first();
        Elements el2 = table.getElementsByTag("tr");
        return el2;
    };
}

