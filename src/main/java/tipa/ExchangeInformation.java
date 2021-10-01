package tipa;

public class ExchangeInformation {
    String dateAndTime;
    String name;
    String tiket;
    int price;
    int capitalizationRub;
    int capitalizationDollars;
    int changesDay;
    int changesWeek;
    int changesMonth;
    int changesYear;

    public ExchangeInformation(String dateAndTime) {
    }
    public ExchangeInformation(String dateAndTime, String tiket, int price, int capitalizationRub, int capitalizationDollars, int changesDay, int changesWeek, int changesMonth, int changesYear, String name) {
        this.dateAndTime = dateAndTime;
        this.tiket = tiket;
        this.price = price;
        this.capitalizationRub = capitalizationRub;
        this.capitalizationDollars = capitalizationDollars;
        this.changesDay = changesDay;
        this.changesWeek = changesWeek;
        this.changesMonth = changesMonth;
        this.changesYear = changesYear;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getTiket() {
        return tiket;
    }

    public int getPrice() {
        return price;
    }

    public int getCapitalizationRub() {
        return capitalizationRub;
    }

    public int getCapitalizationDollars() {
        return capitalizationDollars;
    }

    public int getChangesDay() {
        return changesDay;
    }

    public int getChangesWeek() {
        return changesWeek;
    }

    public int getChangesMonth() {
        return changesMonth;
    }

    public int getChangesYear() {
        return changesYear;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCapitalizationRub(int capitalizationRub) {
        this.capitalizationRub = capitalizationRub;
    }

    public void setCapitalizationDollars(int capitalizationDollars) {
        this.capitalizationDollars = capitalizationDollars;
    }

    public void setChangesDay(int changesDay) {
        this.changesDay = changesDay;
    }

    public void setChangesWeek(int changesWeek) {
        this.changesWeek = changesWeek;
    }

    public void setChangesMonth(int changesMonth) {
        this.changesMonth = changesMonth;
    }

    public void setChangesYear(int changesYear) {
        this.changesYear = changesYear;
    }

    @Override
    public String toString() {
        return "tipa.dela.AddStocks{" +
                "dateAndTime='" + dateAndTime + '\'' +
                ", tiket='" + tiket + '\'' +
                ", price=" + price +
                ", capitalizationRub=" + capitalizationRub +
                ", capitalizationDollars=" + capitalizationDollars +
                ", changesDay=" + changesDay +
                ", changesWeek=" + changesWeek +
                ", changesMonth=" + changesMonth +
                ", changesYear=" + changesYear +
                '}';
    }
}