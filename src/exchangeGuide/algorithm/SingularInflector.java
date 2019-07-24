package exchangeGuide.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class SingularInflector {
    private static SingularInflector instance;
    private HashMap<String, String> dataMap;
    private Set<String> malformedLines;
    private Set<String> unmatchedWords;
    private Set<String> convertedWords;
    private static final String RESOURCES_PATH = "resources/";
    private static final String FILE_NAME = RESOURCES_PATH + "EnglishNounInflections.csv";
    private static final String FILE_NOT_FOUND = "File %s is not found! Exiting.";
    private static final String DATA_SOURCE_MALFORMED = "File %s contains malformed line:\n%s";

    private SingularInflector() {
        dataMap = new HashMap<>();
        unmatchedWords = new HashSet<>();
        convertedWords = new HashSet<>();
        malformedLines = new HashSet<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                putInDataMap(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(String.format(FILE_NOT_FOUND, FILE_NAME));
            System.exit(-1);
        }
    }

    // static block initialization for exception handling and eager initialization
    static {
        try {
            instance = new SingularInflector();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating SingularInflector instance");
        }
    }

    /**
     * if the word is noun, find singular form and convert.
     * if the word is not a noun or the word is not found in dictionary, return the original word
     * @param word  an English word wished to be converted to its singular inflection
     * @return  the singular form or just the original word, capitalization is not changed
     */
    public String toSingular(String word) {
        boolean isCapitalized = false;
        String lowerCaseWord = word.toLowerCase(Locale.US);
        if (lowerCaseWord != word) {
            isCapitalized = true;
        }
        if (dataMap.containsKey(lowerCaseWord)) {
            convertedWords.add(lowerCaseWord);
            lowerCaseWord = dataMap.get(lowerCaseWord);
        } else {
            unmatchedWords.add(lowerCaseWord);
            lowerCaseWord = word;
        }
        return isCapitalized ? lowerCaseWord.substring(0, 1).toUpperCase(Locale.US) + lowerCaseWord.substring(1)
                : lowerCaseWord;
    }

    public static SingularInflector getInstance() {
        return instance;
    }

    // assumes that each line has only one comma, errors out if that is not the case.
    // convert everything to lower cased
    private void putInDataMap(String line) {
        String[] singularAndPlural = line.split(",");
        if (singularAndPlural.length != 2) {
            malformedLines.add(line);
        }
        dataMap.put(singularAndPlural[1].toLowerCase(Locale.US),
                singularAndPlural[0].toLowerCase(Locale.US));
    }
}
