package by.vsu;

import by.vsu.textpart.comparators.ComparatorWordByConsonant;
import by.vsu.textpart.Word;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "Lorem ipsum dolor    a     sit amet consectetur adipiscing, elit mauris vulputate " +
                "       nostra   diam         suspendisse scelerisque pretium " +
                "                +823(34)437-80-06" +
                "penatibus et ultricies dennoihoxunna-4478yop@mail.com\n" +
                "varius sit orci      aenean, in at leo fermentum ut magnis ligula porta.";

        Text text = new Text(str);

        System.out.println(text.getText());

        List<Word> words = new ArrayList<>();


        words.add(new Word("aenean"));
        words.add(new Word("penatibusFir"));
        words.add(new Word("magnis"));
        words.add(new Word("ultricies"));
        words.add(new Word("penatibusSec"));
        words.add(new Word("amet"));
        words.add(new Word("ipsum"));
        words.add(new Word("ab"));

        words.sort(new ComparatorWordByConsonant());

        for (Word word : words) {
            System.out.println(word.getString() + " ");
        }


    }
}