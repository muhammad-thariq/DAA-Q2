package timeline;

import java.io.*;
import java.util.Scanner;

public class FileAppender {
    public static void appendToFile(String filePath, String newText) {
        try {
            // Read the file to determine the last index
            int lastIndex = -1;
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\t");
                    if (parts.length > 0) {
                        lastIndex = Integer.parseInt(parts[0]);
                    }
                }
            }

            // Calculate the new index
            int newIndex = lastIndex + 1;

            // Append the new line to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(newIndex + "\t" + newText);
                writer.newLine();
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error handling file: " + e.getMessage());
        }
    }
}
