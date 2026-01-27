package cryptograph.service;

import cryptograph.exception.UnknownLanguageException;

public class AlphabetDetectionService {
    private static final String EN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String UK_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

    public String getAlphabet(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Data is empty");
        }
        int uk = 0;
        int en = 0;
        for (char ch : text.toLowerCase().toCharArray()) {
            if (ch >= 'а' && ch <= 'я') {
                uk++;
            } else if (ch >= 'a' && ch <= 'z') {
                en++;
            }
        }
        if (uk == 0 && en == 0) {
            throw new UnknownLanguageException("Unknown language");
        }
        return uk > en ? UK_ALPHABET : EN_ALPHABET;
    }
}
