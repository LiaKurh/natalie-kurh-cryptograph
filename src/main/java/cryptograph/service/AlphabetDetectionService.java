package cryptograph.service;

import cryptograph.domain.Alphabet;
import cryptograph.exception.UnknownLanguageException;

import java.util.*;

public class AlphabetDetectionService {

    public String getAlphabet(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Data is empty");
        }
        String letters = getNonRepeatableLetters(data);
        String[] alphabets = getAlphabets();
        return findAlphabet(letters, alphabets);
    }

    private String getNonRepeatableLetters(String text) {
        String letters = text.replaceAll("[^\\p{L}]", "");
        if (letters.isEmpty()) {
            throw new UnknownLanguageException("Unknown language!");
        }
        StringBuilder builder = new StringBuilder();
        letters.chars().distinct().forEach(c -> builder.append((char) c));
        return builder.toString();
    }

    private String[] getAlphabets() {
        return Arrays.stream(Alphabet.values()).map(Alphabet::getAlphabet).toArray(String[]::new);
    }

    private String findAlphabet(String letters, String[] alphabets) {
        String alphabet = "";
        int max = 0;
        for (String possibleAlphabet : alphabets) {
            int count = 0;
            for (int j = 0; j < letters.length(); j++) {
                int index = possibleAlphabet.indexOf(letters.charAt(j));
                if (index != -1) {
                    count++;
                }
            }
            if (max < count) {
                max = count;
                alphabet = possibleAlphabet;
            }
        }
        if (alphabet.isEmpty()) {
            throw new UnknownLanguageException("Unknown language");
        }
        return alphabet;
    }
}
