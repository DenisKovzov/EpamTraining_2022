package by.vsu.textpart.comparators;

import by.vsu.textpart.Word;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComparatorWordByConsonant implements Comparator<Word> {

    private static final String REG_EXP_VOWEL = "^(?i:[aeiouаоуыэяёюие]).*";
    private static final String REG_EXP_CONSONANT = "(?i:[bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ])";


    @Override
    public int compare(Word wordFirst, Word wordSecond) {

        if (!Pattern.matches(REG_EXP_VOWEL, wordFirst.getString())
                || !Pattern.matches(REG_EXP_VOWEL, wordSecond.getString())) {
            return compareWithoutVowel(wordFirst, wordSecond);
        }

        Matcher matcherForFirst = Pattern.compile(REG_EXP_CONSONANT).matcher(wordFirst.getString());
        Matcher matcherForSecond = Pattern.compile(REG_EXP_CONSONANT).matcher(wordSecond.getString());

        if (!matcherForFirst.find(0) ||
                !matcherForSecond.find(0)) {
            return compareWithoutConsonant(wordFirst, wordSecond);
        }

        String first = matcherForFirst.group();
        String second = matcherForSecond.group();

        return first.compareTo(second);
    }

    private int compareWithoutVowel(Word wordFirst, Word wordSecond) {

        if (!Pattern.matches(REG_EXP_VOWEL, wordFirst.getString())
                && !Pattern.matches(REG_EXP_VOWEL, wordSecond.getString())) {
            return 0;
        } else if (!Pattern.matches(REG_EXP_VOWEL, wordFirst.getString())) {
            return 1;
        } else {
            return -1;
        }
    }

    private int compareWithoutConsonant(Word wordFirst, Word wordSecond) {

        Matcher matcherForFirst = Pattern.compile(REG_EXP_CONSONANT).matcher(wordFirst.getString());
        Matcher matcherForSecond = Pattern.compile(REG_EXP_CONSONANT).matcher(wordSecond.getString());

        if (!matcherForFirst.find(0) &&
                !matcherForSecond.find(0)) {
            return 0;
        } else if (!matcherForFirst.find(0)) {
            return 1;
        } else {
            return -1;
        }
    }

}
