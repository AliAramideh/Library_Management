package model;

public class Publication {

    // Fileds
    private String name;
    private String author;
    private int price;
    private String language;

    // Constructor
    public Publication(String name, String author, String language, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.language = language;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public String getLanguage() {
        return language;
    }
}
