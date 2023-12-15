package stockbot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {

    // Method to read stock data from a CSV file
    public static Map<String, StockData> readStockData(String csvFile) {
        Map<String, StockData> stockDataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip the header line

            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String date = columns[0];
                double openPrice = Double.parseDouble(columns[1]);
                double highPrice = Double.parseDouble(columns[2]);
                double lowPrice = Double.parseDouble(columns[3]);
                double closePrice = Double.parseDouble(columns[4]);
                double adjClosePrice = Double.parseDouble(columns[5]);
                long volume = Long.parseLong(columns[6]);

                StockData stockData = new StockData(date, openPrice, highPrice, lowPrice, closePrice, adjClosePrice, volume);
                stockDataMap.put(date, stockData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockDataMap;
    }

    // Main method for testing or stand alone use
    public static void main(String[] args) {
        String csvFile = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/PEP (1).csv";
        Map<String, StockData> stockDataMap = readStockData(csvFile);

        // Testing by printing data for a specific day
        System.out.println(stockDataMap.get("2022-12-12"));
    }
}
