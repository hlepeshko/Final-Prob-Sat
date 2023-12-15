package stockbot;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class StockBot {
    private double balance;
    private int ownedShares;
    private List<StockData> stockDataList;
    private Map<String, Double> netWorthByDate;
    private static final double STATIC_RSI = 43.30350953298161;  // Static RSI value
    private static final String MA_FILE = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/MA Output.csv";
    public StockBot(double initialBalance, String stockDataFilePath) {
        this.balance = initialBalance;
        this.ownedShares = 0;
        this.stockDataList = new ArrayList<>();
        this.netWorthByDate = new LinkedHashMap<>();
        loadStockData(stockDataFilePath);
    }
    private void loadStockData(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines.subList(1, lines.size())) {
                String[] parts = line.split(",");
                String date = parts[0];
                double openPrice = Double.parseDouble(parts[1]);
                double highPrice = Double.parseDouble(parts[2]);
                double lowPrice = Double.parseDouble(parts[3]);
                double closePrice = Double.parseDouble(parts[4]);
                double adjClosePrice = Double.parseDouble(parts.length > 5 ? parts[5] : parts[4]); // Assuming adjClosePrice is provided, otherwise using closePrice
                long volume = parts.length > 6 ? Long.parseLong(parts[6]) : 0; // Assuming volume is provided, otherwise default to 0
                stockDataList.add(new StockData(date, openPrice, highPrice, lowPrice, closePrice, adjClosePrice, volume));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int tradeEvaluator(StockData data) {
        double ma = getMovingAverageForDate(data.getDate());
        // Define your trading logic based on static RSI and MA values
        if (STATIC_RSI < 30 && data.getClosePrice() < ma) {
            // Buy logic
        } else if (STATIC_RSI > 70 && data.getClosePrice() > ma) {
            // Sell logic
        }
        return 0; // No action
    }
    private double getMovingAverageForDate(String date) {
        try {
            List<String> maLines = Files.readAllLines(Paths.get(MA_FILE));
            for (String line : maLines.subList(1, maLines.size())) {
                String[] parts = line.split(",");
                if (parts[0].equals(date)) {
                    return Double.parseDouble(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Default value if not found
    }
    public void executeTradingDay() {
        for (int i = 0; i < stockDataList.size(); i++) {
            StockData data = stockDataList.get(i);
            int tradeAction = tradeEvaluator(data);
            // Perform trade based on tradeAction
            // Update balance and ownedShares accordingly
            double netWorth = balance + (ownedShares * data.getClosePrice());
            netWorthByDate.put(data.getDate(), netWorth);
        }
    }
    public void writeNetWorthToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Date,Net Worth\n");
            for (Map.Entry<String, Double> entry : netWorthByDate.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        }
    }
    public static void main(String[] args) {
        String stockDataFilePath = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/PEP (1).csv"; 
        StockBot bot = new StockBot(10000, stockDataFilePath);
        bot.executeTradingDay();
        try {
            bot.writeNetWorthToCSV("Net Worth.csv");
            System.out.println("Trading complete. Net worth data written to 'Net Worth.csv'.");
        } catch (IOException e) {
            System.err.println("Error writing to 'Net Worth.csv': " + e.getMessage());
            e.printStackTrace();
        }
    }
}
