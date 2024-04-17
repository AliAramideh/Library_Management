package model;

public class Book extends Publication {

    // Additional fileds
    private int ISBN;

    // Constructor
    public Book(String name, String author, String language, int price, int ISBN) {
        super(name, author, language, price);
        this.ISBN = ISBN;
    }

    // getter
    public int getISBN() {
        return ISBN;
    }
}
