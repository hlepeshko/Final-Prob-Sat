package stockbot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RSICalculator {

    public static double calculateRSI(List<Double> changes, int periodLength) {
        double gains = 0, losses = 0;
        for (Double change : changes) {
            if (change > 0) {
                gains += change;
            } else {
                losses -= change;
            }
        }
        double averageGain = gains / periodLength;
        double averageLoss = losses / periodLength;

        double rs = (averageLoss == 0) ? Double.POSITIVE_INFINITY : averageGain / averageLoss;
        return 100 - (100 / (1 + rs));
    }

    public static List<StockData> loadStockData(String filePath) throws IOException {
        List<StockData> stockDataList = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines.subList(1, lines.size())) {
            String[] parts = line.split(",");
            double closePrice = Double.parseDouble(parts[4]);
            stockDataList.add(new StockData(parts[0], closePrice));
        }
        return stockDataList;
    }

    public static void main(String[] args) {
        String csvFilePath = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/PEP (1).csv";
        String outputFilePath = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/RSI Output.csv";
        int periodLength = 14; // typical period length for RSI

        try {
            List<StockData> stockData = loadStockData(csvFilePath);
            List<Double> priceChanges = new ArrayList<>();
            Map<String, Double> rsiByDate = new LinkedHashMap<>();

            for (int i = 1; i < stockData.size(); i++) {
                double priceChange = stockData.get(i).getClosePrice() - stockData.get(i - 1).getClosePrice();
                priceChanges.add(priceChange);

                if (i >= periodLength) {
                    double rsi = calculateRSI(priceChanges.subList(i - periodLength, i), periodLength);
                    rsiByDate.put(stockData.get(i).getDate(), rsi);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write("Date,RSI\n");
                for (Map.Entry<String, Double> entry : rsiByDate.entrySet()) {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                }
            }
            System.out.println("RSI calculation complete. Data written to '" + outputFilePath + "'.");
        } catch (IOException e) {
            System.err.println("Error reading from '" + csvFilePath + "' or writing to '" + outputFilePath + "': " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class StockData {
        private final String date;
        private final double closePrice;

        public StockData(String date, double closePrice) {
            this.date = date;
            this.closePrice = closePrice;
        }

        public String getDate() {
            return date;
        }

        public double getClosePrice() {
            return closePrice;
        }
    }
}


