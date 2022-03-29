package by.vsu.partText.Comparators;

import by.vsu.partText.Word;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComparatorWordByConsonant implements Comparator<Word> {

    private static final String REG_EXP_VOWEL = "^(?i:[aeiouаоуыэяёюие]).*";
    private static final String REG_EXP_CONSONANT = "(?i:[bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ])";


    @Override
    public int compare(Word o1, Word o2) {

        if (!Pattern.matches(REG_EXP_VOWEL, o1.getString())
                && !Pattern.matches(REG_EXP_VOWEL, o2.getString())) {
            return 0;
        } else if (!Pattern.matches(REG_EXP_VOWEL, o1.getString())) {
            return 1;
        } else if (!Pattern.matches(REG_EXP_VOWEL, o2.getString())) {
            return -1;
        }


        Matcher matcherForFirst = Pattern.compile(REG_EXP_CONSONANT).matcher(o1.getString());
        Matcher matcherForSecond = Pattern.compile(REG_EXP_CONSONANT).matcher(o2.getString());

        if (!matcherForFirst.find(0) &&
                !matcherForSecond.find(0)) {
            return 0;
        } else if (!matcherForFirst.find(0)) {
            return 1;
        } else if (!matcherForSecond.find(0)) {
            return -1;
        }

        String first = matcherForFirst.group();
        String second = matcherForSecond.group();

        return first.compareTo(second);
    }
}
