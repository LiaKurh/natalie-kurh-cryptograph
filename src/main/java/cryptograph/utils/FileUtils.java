package cryptograph.utils;

import java.io.*;
import java.util.stream.Collectors;

public class FileUtils {

    public String readFromFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + file, e);
        }
    }

    public void writeToFile(String data, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + file, e);
        }
    }

    public String getNewFileName(String file, Commands command) {
        StringBuilder builder = new StringBuilder(file);
        int index = builder.lastIndexOf(".");
        builder.insert(index, getCommand(command));
        return builder.toString();
    }

    private String getCommand(Commands command) {
        return switch (command) {
            case ENCRYPT -> "[ENCRYPTED]";
            case DECRYPT -> "[DECRYPTED]";
            case BRUTE_FORCE -> "[BRUTE_FORCED]";
        };
    }
}
