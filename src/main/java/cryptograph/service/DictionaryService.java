package cryptograph.service;

import cryptograph.domain.Alphabet;
import cryptograph.domain.Dictionary;
import cryptograph.exception.InvalidFilePathException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DictionaryService {
    private final Map<String, String> dictionaries;

    public DictionaryService() {
        this.dictionaries = getDictionaries();
    }

    public String getDictionary(String alphabet) {
        FileService fileService = new FileService();
        return fileService.readFromFile(getFilePathToDictionary(alphabet));
    }

    private String getFilePathToDictionary(String alphabet) {
        if (!dictionaries.containsKey(alphabet)) {
            throw new InvalidFilePathException("Unknown dictionary!");
        }
        return dictionaries.get(alphabet);
    }

    private Map<String, String> getDictionaries() {
        Map<String, String> dictionaries = new HashMap<>();
        dictionaries.put(Alphabet.EN_ALPHABET.getAlphabet(), Dictionary.EN_DICTIONARY.getDictionary());
        dictionaries.put(Alphabet.UK_ALPHABET.getAlphabet(), Dictionary.UK_DICTIONARY.getDictionary());
        return Collections.unmodifiableMap(dictionaries);
    }
}
