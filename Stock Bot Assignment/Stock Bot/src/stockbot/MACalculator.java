package stockbot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MACalculator {

    /**
     * Processes the stock data from the input file, calculates moving averages,
     * and writes them to the output file.
     *
     * @param inputFile  The path to the CSV file with stock data.
     * @param outputFile The path for the output file with moving average data.
     * @param windowSize The size of the moving window for averaging.
     */
    public static void processStockData(String inputFile, String outputFile, int windowSize) {
        Queue<Double> window = new LinkedList<>();
        double windowSum = 0;

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

            bw.write("Date,Moving Average\n"); // Write header to output file

            for (int i = 1; i < lines.size(); i++) { // Skip header line
                String[] values = lines.get(i).split(",");
                String date = values[0];
                double closePrice = Double.parseDouble(values[4]);

                window.add(closePrice);
                windowSum += closePrice;

                if (window.size() > windowSize) {
                    windowSum -= window.remove();
                }

                double average = window.size() < windowSize ? windowSum / window.size() : windowSum / windowSize;
                bw.write(date + "," + average + "\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFile = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/PEP (1).csv\"";
        String outputFile = "/Users/hopelepeshko/Desktop/SCHOOL!!!!!/FALL 2023/Prob & Appl. Stat/Stock Bot Assignment/MA Output.csv";
        int windowSize = 5; // Example window size

        processStockData(inputFile, outputFile, windowSize);
    }
}


    
    
    
    
    
    
    
    

