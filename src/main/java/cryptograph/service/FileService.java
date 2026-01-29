package cryptograph.service;

import cryptograph.domain.Command;

import java.io.*;
import java.util.stream.Collectors;

public class FileService {

    public String readFromFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + file, e);
        }
    }

    public void writeToFile(String data, String file, String command) {
        String toFile = getNewFileName(file, Command.valueOf(command));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFile, e);
        }
    }

    private String getNewFileName(String file, Command command) {
        StringBuilder builder = new StringBuilder(file);
        int index = builder.lastIndexOf(".");
        builder.insert(index, getCommand(command));
        return builder.toString();
    }

    private String getCommand(Command command) {
        return switch (command) {
            case ENCRYPT -> "[ENCRYPTED]";
            case DECRYPT -> "[DECRYPTED]";
            case BRUTE_FORCE -> "[BRUTE_FORCED]";
        };
    }
}
