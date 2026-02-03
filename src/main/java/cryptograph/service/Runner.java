package cryptograph.service;

import cryptograph.constant.ArgsIndex;
import cryptograph.domain.Command;
import cryptograph.validation.ProgramArgumentsValidation;

public class Runner {
    private final CryptographService cryptographService;
    private final FileService fileService;

    public Runner(CryptographService cryptographService, FileService fileService) {
        this.cryptographService = cryptographService;
        this.fileService = fileService;
    }

    public void run(String... args) {
        if (args.length == 0) {
            CLIService cli = new CLIService();
            args = cli.getProgramArguments();
        }
        if (new ProgramArgumentsValidation().isValidArgs(args)) {
            String inputData = fileService.readFromFile(args[ArgsIndex.ARGS_FILE_PATH_INDEX]);
            String result = getEncryptedDecryptedData(inputData, args);
            fileService.writeToFile(result, args[ArgsIndex.ARGS_FILE_PATH_INDEX], args[ArgsIndex.ARGS_COMMAND_INDEX]);
        }
    }

    private String getEncryptedDecryptedData(String data, String... args) {
        return switch (Command.valueOf(args[ArgsIndex.ARGS_COMMAND_INDEX])) {
            case Command.ENCRYPT -> cryptographService.encrypt(data, Integer.parseInt(args[ArgsIndex.ARGS_KEY_INDEX]));
            case Command.DECRYPT -> cryptographService.decrypt(data, Integer.parseInt(args[ArgsIndex.ARGS_KEY_INDEX]));
            case Command.BRUTE_FORCE -> cryptographService.bruteForce(data);
        };
    }
}
