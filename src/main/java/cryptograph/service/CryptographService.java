package cryptograph.service;

public class CryptographService {
    private final String alphabet;

    public CryptographService(String alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String data, int key) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char charToEncrypt = data.charAt(i);
            int index = alphabet.indexOf(charToEncrypt);
            if (index != -1) {
                char encryptedChar = alphabet.charAt(getShiftedIndexByKey(index, key));
                builder.append(encryptedChar);
            } else {
                builder.append(charToEncrypt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String data, int key) {
        return encrypt(data, -key);
    }

    private int getShiftedIndexByKey(int index, int key) {
        int shiftedIndex = (index + key) % alphabet.length();
        return shiftedIndex < 0 ? shiftedIndex + alphabet.length() : shiftedIndex;
    }
}
