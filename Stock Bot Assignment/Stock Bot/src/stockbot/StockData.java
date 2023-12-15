package stockbot;

public class StockData {
    private String date;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private double adjClosePrice;
    private long volume;

    public StockData(String date, double openPrice, double highPrice, double lowPrice, double closePrice, double adjClosePrice, long volume) {
        this.date = date;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.adjClosePrice = adjClosePrice;
        this.volume = volume;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getAdjClosePrice() {
        return adjClosePrice;
    }

    public long getVolume() {
        return volume;
    }

    // Setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public void setAdjClosePrice(double adjClosePrice) {
        this.adjClosePrice = adjClosePrice;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "date='" + date + '\'' +
                ", openPrice=" + openPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", closePrice=" + closePrice +
                ", adjClosePrice=" + adjClosePrice +
                ", volume=" + volume +
                '}';
    }
}
