package by.vsu;

import by.vsu.textpart.Composite;
import by.vsu.textpart.PunctuationMark;
import by.vsu.textpart.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    private String text;

    public Text(String str) {
        this.text = makeText(str);
    }

    private String makeText(String str) {
        Pattern pattern = Pattern.compile(PunctuationMark.REG_EXP + "|" + Word.REG_EXP_WORD);
        Matcher matcher = pattern.matcher(str);

        Composite composite = new Composite();

        while (matcher.find()) {
            if (Word.isWord(matcher.group())) {
                composite.addComponent(new Word(matcher.group()));
            } else if (PunctuationMark.isPunctuation(matcher.group())) {
                composite.addComponent(new PunctuationMark(matcher.group()));
            }
        }
        return composite.getString();
    }

    public String getText() {
        return text;
    }

}
