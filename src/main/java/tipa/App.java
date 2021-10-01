package tipa;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
                    Date date=new SimpleDateFormat("dd:MM:yyyy").parse(e.select("td").get(1).text());
                    jdbcTemplate.update("INSERT INTO smart_lab_db VALUE()");
                    for (int j = 0; j < 20; j++) {

                        jdbcTemplate.update("INSERT INTO smart_lab_db VALUE ")
                        System.out.print(e.select("td").get(j).text()+"");
                    }
                    System.out.println();
//                for(Element td : e.select("td")){
//                    String t =  td.text();
//                    System.out.println("Сейчас в стринге: "+ t);
//                }


//                    for(Element td : e.select("td")){
//                        sti.add(td.text());
//                    }
//                    mass.add(sti);
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