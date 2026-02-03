package cryptograph.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
        String alphabet = alphabetDetectionService.getAlphabet(data);
        Set<String> dictionary = getDictionary(alphabet);
        int key = getKey(data, alphabet, dictionary);
        return decrypt(data, key);
    }

    private int getKey(String data, String alphabet, Set<String> dictionary) {
        int possibleKey = 1;
        long max = 0L;
        int key = 0;
        for (int i = 1; i < alphabet.length(); i++) {
            String possibleData = getShiftedData(data, -possibleKey);
            long count = getNumberOfSameWords(possibleData, dictionary);
            if (max < count) {
                max = count;
                key = possibleKey;
            }
            possibleKey++;
        }
        return key;
    }

    private long getNumberOfSameWords(String data, Set<String> dictionary) {
        String text = data.replaceAll("[^\\p{L}\\s]", "")
                .replaceAll("\\s+", " ")
                .toLowerCase()
                .trim();
        Set<String> words = Arrays.stream(text.split(" ")).collect(Collectors.toSet());
        return dictionary.stream().map(String::toLowerCase).filter(words::contains).count();
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

    private Set<String> getDictionary(String alphabet) {
        String dictionary = new DictionaryService().getDictionary(alphabet);
        return Arrays.stream(dictionary.split(System.lineSeparator()))
                .map(String::trim)
                .collect(Collectors.toSet());
    }
}
