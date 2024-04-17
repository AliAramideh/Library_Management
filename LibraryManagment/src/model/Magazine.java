package model;

public class Magazine extends Publication {

    // Additional fileds
    private int ISSN;
    private int number;

    // Constructor
    public Magazine(String name, String author, String language, int price, int iSSN, int number) {
        super(name, author, language, price);
        ISSN = iSSN;
        this.number = number;
    }

    // getter
    public int getISSN() {
        return ISSN;
    }

    public int getNumber() {
        return number;
    }

}
