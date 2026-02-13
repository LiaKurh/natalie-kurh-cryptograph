package cryptograph.service;

import cryptograph.domain.Command;

import java.util.Scanner;

public class CLIService {

    public String[] getProgramArguments() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter program command:  ENCRYPT, DECRYPT or BRUTE_FORCE");
            String command = scanner.nextLine();
            System.out.println("Enter path to the file: ");
            String file = scanner.nextLine();
            if(Command.BRUTE_FORCE.name().equals(command)) {
                return new String[]{command, file};
            }
            System.out.println("Enter the key: ");
            String key = scanner.nextLine();
            return new String[]{command, file, key};
        }
    }
}
