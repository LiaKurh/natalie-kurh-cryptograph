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
    private static final int PROGRAM_ARGS_LENGTH_WITHOUT_KEY = 2;

    public void validateAllArgs(String... args) {
        validateArgsLength(args);
        validateCommand(args[ArgsIndex.ARGS_COMMAND_INDEX]);
        validateFile(args[ArgsIndex.ARGS_FILE_PATH_INDEX]);
        if (!args[ArgsIndex.ARGS_COMMAND_INDEX].equals(Command.BRUTE_FORCE.name())) {
            validateKey(args[ArgsIndex.ARGS_KEY_INDEX]);
        }
    }

    private void validateArgsLength(String... args) {
        String command = args[ArgsIndex.ARGS_COMMAND_INDEX];
        int length = args.length;
        if (command.equals(Command.BRUTE_FORCE.name()) && length != PROGRAM_ARGS_LENGTH_WITHOUT_KEY) {
            throw new InvalidProgramArgsLengthException(
                    String.format("Program must have %d arguments (command, file path) for BRUTE_FORCE." +
                            " Found: %d", PROGRAM_ARGS_LENGTH_WITHOUT_KEY, length));
        }
        if (!command.equals(Command.BRUTE_FORCE.name()) && length != PROGRAM_ARGS_LENGTH) {
            throw new InvalidProgramArgsLengthException(
                    String.format("Program must have %d arguments (command, file path, key). Found: %d",
                            PROGRAM_ARGS_LENGTH, length));
        }
    }

    private void validateCommand(String command) {
        try {
            Command.valueOf(command);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UnknownProgramCommandException(
                    String.format("Unknown program command: '%s'. Valid commands are: %s",
                            command, Arrays.toString(Command.values())));
        }
    }

    private void validateFile(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new InvalidFilePathException("File path cannot be null or empty!");
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new InvalidFilePathException("File not found or invalid file path: " + filePath);
        }
        if (!file.isAbsolute()) {
            throw new InvalidFilePathException("File path must be absolute(full), " +
                    "starting from the root directory! Provided: " + filePath);
        }
    }

    private void validateKey(String key) {
        try {
            Integer.parseInt(key);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                    String.format("Key must be an integer! Key: %s", key));
        }
    }
}
