package cryptograph.utils;

public class AlphabetDetectionUtils {
    private static final String EN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String UK_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

    public String getAlphabet(String text) {
        if (text == null || text.isBlank()) {
            throw new RuntimeException("Data is empty");
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
            throw new RuntimeException("Unknown language");
        }
        return uk > en ? UK_ALPHABET : EN_ALPHABET;
    }
}
