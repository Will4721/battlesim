package file;

import interfaces.Unit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger {
    private static final String DEFAULT_FILE = "aquarium_log.txt";

    // 🔹 Standard log metode
    public static void log(String message) {
        log(message, DEFAULT_FILE);}

    public static void log(String message, String fileName) {

        String formattedMessage = formatMessage(message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(formattedMessage);
            writer.newLine(); // bedre end "\n"
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }
    private static String formatMessage(String message) {
        return LocalDateTime.now() + " | " + message;
    }
}
