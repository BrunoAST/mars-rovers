package mars.rover;

import mars.rover.Normalizer.Information;
import mars.rover.Normalizer.Normalize;
import mars.rover.Reader.ReaderService;

import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var filePath = prompt();

        try {
            var reader = new FileReader(filePath);
            var readerService = new ReaderService(reader);
            var lines = readerService.readFile();

            Information data = Normalize.normalizeData(lines);

            CommandRunner.execute(data);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static String prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path of the file to read: ");
        String filePath = scanner.nextLine();
        scanner.close();

        return filePath;
    }
}
