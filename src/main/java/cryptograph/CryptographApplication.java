package cryptograph;

import cryptograph.service.Runner;
import cryptograph.service.CryptographService;
import cryptograph.service.AlphabetDetectionService;
import cryptograph.service.FileService;

public class CryptographApplication {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        AlphabetDetectionService alphabetDetectionService = new AlphabetDetectionService();
        CryptographService cryptographService = new CryptographService(alphabetDetectionService);
        Runner runner = new Runner(cryptographService, fileService);
        runner.run(args);
    }
}
