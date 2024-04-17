package model;

import java.util.ArrayList;

public class Member {

    // Fileds
    private String name;
    private int ID;
    private int balance;
    private ArrayList<Book> borrowedBooks;
    private ArrayList<Magazine> borrowedMagazines;

    // Constructor
    public Member(String name, int ID) {
        this.name = name;
        this.ID = ID;
        balance = 0;
        borrowedBooks = new ArrayList<>();
        borrowedMagazines = new ArrayList<>();
    }

    // Methods
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void borrowMagazine(Magazine magazine) {
        borrowedMagazines.add(magazine);
    }

    public Book getBookFromBorrowedBooks(int ISBN) {
        if (borrowedBooks.size() == 0)
            return null;
        for (Book book : borrowedBooks) {
            if (book.getISBN() == ISBN)
                return book;
        }
        return null;
    }

    public Magazine getMagazineFromBorrowedMagazines(int ISSN, int number) {
        if (borrowedMagazines.size() == 0)
            return null;
        for (Magazine magazine : borrowedMagazines) {
            if (magazine.getISSN() == ISSN) {
                if (magazine.getNumber() == number)
                    return magazine;
            }
        }
        return null;
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void returnMagazine(Magazine magazine) {
        borrowedMagazines.remove(magazine);
    }

    // Getters & Setters
    public void setBalance(int amount) {
        balance = amount;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

}
