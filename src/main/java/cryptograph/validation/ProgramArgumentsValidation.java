package cryptograph.validation;

import cryptograph.constant.ArgsIndex;
import cryptograph.domain.Command;
import cryptograph.exception.InvalidFilePathException;
import cryptograph.exception.InvalidProgramArgsLengthException;
import cryptograph.exception.UnknownProgramCommandException;

import java.io.File;
import java.util.Arrays;

public class ProgramArgumentsValidation {
    private static final int PROGRAM_ARGS_LENGTH = 3;

    public boolean isValidArgs(String... args) {
        if (args.length != PROGRAM_ARGS_LENGTH) {
            throw new InvalidProgramArgsLengthException("Program must have " + PROGRAM_ARGS_LENGTH
                    + " arguments: command, file path, key. Find arguments: " + args.length);
        }
        return isValidCommand(args[ArgsIndex.ARGS_COMMAND_INDEX])
                && isValidFile(args[ArgsIndex.ARGS_FILE_PATH_INDEX])
                && isValidKey(args[ArgsIndex.ARGS_KEY_INDEX]);
    }

    private boolean isValidCommand(String command) {
        if (!Arrays.stream(Command.values()).anyMatch(e -> e.name().equals(command))) {
            throw new UnknownProgramCommandException("Unknown program command! " +
                    "Valid commands: ENCRYPT, DECRYPT or BRUTE_FORCE.");
        }
        return true;
    }

    private boolean isValidFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new InvalidFilePathException("File not found or invalid file path: " + filePath);
        }
        if (!file.isAbsolute()) {
            throw new InvalidFilePathException("File path must be absolute(full), " +
                    "starting from the root directory (disk root)!");
        }
        return true;
    }

    private boolean isValidKey(String key) {
        try {
            Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Key must be an integer! Key: " + key);
        }
        return true;
    }
}
