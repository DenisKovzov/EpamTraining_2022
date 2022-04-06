package by.vsu.textpart;

import java.util.regex.Pattern;

public class PunctuationMark implements Component {
    public static final String REG_EXP = "[,.!?]+";

    private String mark;

    public PunctuationMark(String str) {
        validate(str);

        this.mark = str;
    }

    private void validate(String str) {
        if (isPunctuation(str) == false) {
            throw new IllegalArgumentException(str + " is not mark");
        }
    }

    public static boolean isPunctuation(String str) {
        return Pattern.matches(REG_EXP, str);
    }

    public String getString() {
        return mark;
    }
}
