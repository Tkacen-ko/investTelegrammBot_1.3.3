package tipa;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collection;

public class App {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public App(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public static void main(String[] args){
        Collection<Collection<String>>mass = new ArrayList<>();
        try {
            for (int i = 0; i <= 283; i++) {
                Collection <String> sti = new ArrayList<>();
                Element e = Parser.getPage().get(i);
                if (e.select("td").size() == 20) {
                    for(Element td : e.select("td")){
                        sti.add(td.text());
                    }
                    mass.add(sti);
                }
            }
        }
        catch (Exception e) {
            System.out.println("!!! \nВнимание я влетел в блок иключения\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        for (Collection<String>a : mass){
            System.out.println(a);
        }
    }
}