package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {

    // Fileds
    private static ArrayList<Book> books;
    private static ArrayList<Magazine> magazines;
    private static ArrayList<Member> members;

    static {
        members = new ArrayList<>();
        magazines = new ArrayList<>();
        books = new ArrayList<>();
    }

    // Methods:
    public static void addBook(Book book) {
        books.add(book);
    }

    public static void addMagazine(Magazine magazine) {
        magazines.add(magazine);
    }

    public static void addMember(Member member) {
        members.add(member);
    }

    public static Member getMemberByID(int ID) {
        if (Library.members == null)
            return null;
        if (members.size() == 0)
            return null;
        for (Member member : members) {
            if (member.getID() == ID)
                return member;
        }
        return null;
    }

    public static Book getBook(int ISBN) {
        if (books.size() == 0)
            return null;
        for (Book book : books) {
            if (book.getISBN() == ISBN)
                return book;
        }
        return null;
    }

    public static void removeBook(Book book) {
        if (books.size() != 0) {
            for (Book ourBook : books) {
                if (ourBook.equals(book)) {
                    books.remove(ourBook);
                    return;
                }
            }
        }
    }

    public static Magazine getMagazine(int ISSN, int number) {
        if (magazines.size() == 0)
            return null;
        for (Magazine magazine : magazines) {
            if (magazine.getISSN() == ISSN) {
                if (magazine.getNumber() == number)
                    return magazine;
            }
        }
        return null;
    }

    public static void removeMagazine(Magazine magazine) {
        if (magazines.size() != 0) {
            for (Magazine ourMagazine : magazines) {
                if (ourMagazine.equals(magazine)) {
                    magazines.remove(ourMagazine);
                    return;
                }
            }
        }
    }

    public static void sortBooks() {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                int compareName = book1.getName().compareTo(book2.getName());
                if (compareName != 0)
                    return compareName;
                int compareAuthor = book1.getAuthor().compareTo(book2.getAuthor());
                if (compareAuthor != 0)
                    return compareAuthor;
                return book1.getLanguage().compareTo(book2.getLanguage());
            }
        });
    }

    public static void sortMagazines() {
        Collections.sort(magazines, new Comparator<Magazine>() {
            @Override
            public int compare(Magazine magazine1, Magazine magazine2) {
                int compareName = magazine1.getName().compareTo(magazine2.getName());
                if (compareName != 0)
                    return compareName;
                int compareNumber = magazine1.getNumber() - magazine2.getNumber();
                if (compareNumber != 0)
                    return compareNumber;
                int compareAuthor = magazine1.getAuthor().compareTo(magazine2.getAuthor());
                if (compareAuthor != 0)
                    return compareAuthor;
                return magazine1.getLanguage().compareTo(magazine2.getLanguage());
            }
        });
    }

    // getters:
    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static ArrayList<Magazine> getMagazines() {
        return magazines;
    }
}
