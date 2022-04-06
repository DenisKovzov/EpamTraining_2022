package by.vsu.textpart;

import by.vsu.textpart.regexp.WordPart;

import java.util.regex.Pattern;

public class Word implements Component {

    static public final String REG_EXP_WORD = getWordExp();

    private String word;

    public Word(String str) {
        validate(str);

        this.word = str;
    }

    private void validate(String str) {
        if (!isWord(str)) {
            throw new IllegalArgumentException(str + " is not word");
        }
    }

    public static boolean isWord(String str) {
        return Pattern.matches(REG_EXP_WORD, str);
    }

    static private final String getWordExp() {

        StringBuilder regExp = new StringBuilder();

        for (WordPart wordPart : WordPart.values()) {
            regExp.append(wordPart.getWordPart() + "|");
        }

        return regExp.delete(regExp.length() - 1, regExp.length()).toString();
    }


    public String getString() {
        return word;
    }
}
