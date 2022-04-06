package by.vsu.textpart;

import java.util.regex.Pattern;

public class Word implements Component {

    static public final String REG_EXP_WORD =
            "\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}" + "|" + // телефон
                    "[a-zA-Z-_0-9.]+@[a-zA-Z-_0-9]+\\.[a-z]{2,3}" + "|" + // почта
                    "[a-zA-Zа-яА-Я]+[-]?[a-zA-Zа-яА-Я]+"; // слово

    private String word;

    public Word(String str) {
        validate(str);

        this.word = str;
    }

    private void validate(String str) {
        if (isWord(str) == false) {
            throw new IllegalArgumentException(str + " is not word");
        }
    }

    public static boolean isWord(String str) {
        return Pattern.matches(REG_EXP_WORD, str);
    }

    public String getString() {
        return word;
    }
}
