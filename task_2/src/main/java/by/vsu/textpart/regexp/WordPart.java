package by.vsu.textpart.regexp;

public enum WordPart {
    NUMBER("\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d{2}\\-\\d{2}"),
    WORD("[a-zA-Z-_0-9.]+@[a-zA-Z-_0-9]+\\.[a-z]{2,3}"),
    EMAIL("[a-zA-Zа-яА-Я]+[-]?[a-zA-Zа-яА-Я]*");

    private String wordPart;

    WordPart(String s) {
        this.wordPart = s;
    }

    public String getWordPart() {
        return wordPart;
    }
}
