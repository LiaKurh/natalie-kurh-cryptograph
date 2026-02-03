package cryptograph.domain;

public enum Dictionary {
    EN_DICTIONARY("src/main/resources/en_dictionary.txt"),
    UK_DICTIONARY("src/main/resources/uk_dictionary.txt");

    private final String dictionary;

    Dictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    public String getDictionary() {
        return dictionary;
    }
}
