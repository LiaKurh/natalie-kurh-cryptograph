package cryptograph.service;

public class CryptographService {
    private final AlphabetDetectionService alphabetDetectionService;

    public CryptographService(AlphabetDetectionService alphabetDetectionService) {
        this.alphabetDetectionService = alphabetDetectionService;
    }

    public String encrypt(String data, int key) {
        return getShiftedData(data, key);
    }

    public String decrypt(String data, int key) {
        return getShiftedData(data, -key);
    }

    public String bruteForce(String data) {
        return "";
    }

    private String getShiftedData(String data, int key) {
        String alphabet = alphabetDetectionService.getAlphabet(data);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char charToShift = data.charAt(i);
            int index = alphabet.indexOf(charToShift);
            if (index != -1) {
                char shiftedChar = alphabet.charAt(getShiftedIndexByKey(alphabet, index, key));
                builder.append(shiftedChar);
            } else {
                builder.append(charToShift);
            }
        }
        return builder.toString();
    }

    private int getShiftedIndexByKey(String alphabet, int index, int key) {
        int shiftedIndex = (index + key) % alphabet.length();
        return shiftedIndex < 0 ? shiftedIndex + alphabet.length() : shiftedIndex;
    }
}
