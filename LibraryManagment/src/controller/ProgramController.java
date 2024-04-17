package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import model.Book;
import model.Library;
import model.Magazine;
import model.Member;
import view.enums.commands.MenuCommands;
import view.enums.massages.MenuMassages;

public class ProgramController {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String startApp = "Create Library";

        String command = scanner.nextLine();
        while (!command.equals(startApp)) {
            if (MenuCommands.getMatcher(command, MenuCommands.END) != null)
                break;
            System.out.println("You have to create the library first");
            command = scanner.nextLine().trim();
        }
        if (!command.contains("end"))
            System.out.println(MenuMassages.LIBRARY_CREATED_SUCCESFULLY.output);
        while (true) {
            if (command.equals("end"))
                break;
            command = scanner.nextLine();
            Matcher matcher;
            if (MenuCommands.getMatcher(command, MenuCommands.END) != null)
                break;
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.ADD_MEMBER)) != null)
                addAccount(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.INCREASE_BALANCE)) != null)
                increaseBalance(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.CASH_OUT)) != null)
                cashout(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.ADD_BOOK)) != null)
                addBook(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.ADD_MAGAZINE)) != null)
                addMagazine(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.BORROW_BOOK)) != null)
                borrowBook(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.BORROW_MAGAZINE)) != null)
                borrowMagazine(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.RETURN_BOOK)) != null)
                returnBook(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.RETURN_MAGAZINE)) != null)
                returnMagazine(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.DONATE_BOOK)) != null)
                donateBook(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.RETURN_MAGAZINE_AND_BORROW_NEXT)) != null)
                returnMagazineAndBorrowNext(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.PRINT_BOOKS)) != null)
                printBooks(matcher);
            else if ((matcher = MenuCommands.getMatcher(command, MenuCommands.PRINT_MAGAZINE)) != null)
                printMagazines(matcher);
            else
                System.out.println("invalid command");
        }
    }

    private static void addAccount(Matcher matcher) {
        String accountName = matcher.group("accountName");
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        if (Library.getMemberByID(integerID) != null)
            System.out.println(MenuMassages.MEMBER_EXIST.output);
        else {
            Member member = new Member(accountName, integerID);
            Library.addMember(member);
            System.out.println(MenuMassages.ACCOUNT_CREATED_SUCCESFULLY.output);
        }
    }

    private static void increaseBalance(Matcher matcher) {
        String ID = matcher.group("ID");
        String amount = matcher.group("amount");
        int integerID = Integer.parseInt(ID);
        int integerAmount = Integer.parseInt(amount);
        Member member = Library.getMemberByID(integerID);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else {
            int balance = member.getBalance();
            int newBalance = balance + integerAmount;
            member.setBalance(newBalance);
            System.out.println(MenuMassages.BALANCE_INCREASED.output);
        }
    }

    private static void cashout(Matcher matcher) {
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        Member member = Library.getMemberByID(integerID);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else {
            int balance = member.getBalance();
            member.setBalance(0);
            String accountName = member.getName();
            System.out.println(accountName + " cashed out successfully. Amount: " + balance);
        }
    }

    private static void addBook(Matcher matcher) {
        String bookName = matcher.group("bookName");
        String ISBN = matcher.group("ISBN");
        int integerISBN = Integer.parseInt(ISBN);
        String author = matcher.group("author");
        String language = matcher.group("language");
        String price = matcher.group("price");
        int integerPrice = Integer.parseInt(price);
        String amount = matcher.group("amount");
        int integerAmount = Integer.parseInt(amount);
        System.out.println(integerAmount + " books were added to the library successfully");
        while (integerAmount != 0) {
            Book book = new Book(bookName, author, language, integerPrice, integerISBN);
            Library.addBook(book);
            integerAmount--;
        }
    }

    private static void addMagazine(Matcher matcher) {
        String magazineName = matcher.group("magazineName");
        String ISSN = matcher.group("ISSN");
        int integerISSN = Integer.parseInt(ISSN);
        String author = matcher.group("author");
        String language = matcher.group("language");
        String price = matcher.group("price");
        int integerPrice = Integer.parseInt(price);
        String amount = matcher.group("amount");
        int integerAmount = Integer.parseInt(amount);
        String number = matcher.group("number");
        int integerNumber = Integer.parseInt(number);
        System.out.println(integerAmount + " magazines were added to the library successfully");
        while (integerAmount != 0) {
            Magazine magazine = new Magazine(magazineName, author, language, integerPrice, integerISSN, integerNumber);
            Library.addMagazine(magazine);
            integerAmount--;
        }
    }

    private static void borrowBook(Matcher matcher) {
        String ISBN = matcher.group("ISBN");
        int integerISBN = Integer.parseInt(ISBN);
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        Member member = Library.getMemberByID(integerID);
        Book book = Library.getBook(integerISBN);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else if (book == null)
            System.out.println(MenuMassages.BOOK_DOSE_NOT_EXIST.output);
        else if (member.getBalance() < book.getPrice())
            System.out.println(MenuMassages.BALANCE_IS_NOT_ENOUGH.output);
        else {
            member.borrowBook(book);
            Library.removeBook(book);
            int newBalance = member.getBalance() - book.getPrice();
            member.setBalance(newBalance);
            System.out.println(book.getName() + " was borrowed by " + member.getName() + " successfully");
        }
    }

    private static void borrowMagazine(Matcher matcher) {
        String ISSN = matcher.group("ISSN");
        int integerISSN = Integer.parseInt(ISSN);
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        String number = matcher.group("number");
        int integerNumber = Integer.parseInt(number);
        Member member = Library.getMemberByID(integerID);
        Magazine magazine = Library.getMagazine(integerISSN, integerNumber);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else if (magazine == null)
            System.out.println(MenuMassages.MAGAZINE_DOES_NOT_EXIST.output);
        else if (member.getBalance() < magazine.getPrice())
            System.out.println(MenuMassages.BALANCE_IS_NOT_ENOUGH.output);
        else {
            member.borrowMagazine(magazine);
            Library.removeMagazine(magazine);
            int newBalance = member.getBalance() - magazine.getPrice();
            member.setBalance(newBalance);
            System.out.println(magazine.getName() + " was borrowed by " + member.getName() + " successfully");
        }
    }

    private static void returnBook(Matcher matcher) {
        String ISBN = matcher.group("ISBN");
        int integerISBN = Integer.parseInt(ISBN);
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        Member member = Library.getMemberByID(integerID);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else if (member.getBookFromBorrowedBooks(integerISBN) == null)
            System.out.println("This member has never borrowed this book or has returned it");
        else {
            Book book = member.getBookFromBorrowedBooks(integerISBN);
            member.returnBook(book);
            Library.addBook(book);
            int newBalance = (int) (member.getBalance() + (0.9 * book.getPrice()));
            member.setBalance(newBalance);
            System.out.println(member.getName() + " returned " + book.getName() + " successfully");
        }
    }

    private static void returnMagazine(Matcher matcher) {
        String ISSN = matcher.group("ISSN");
        int integerISSN = Integer.parseInt(ISSN);
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        String number = matcher.group("number");
        int integerNumber = Integer.parseInt(number);
        Member member = Library.getMemberByID(integerID);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else if (member.getMagazineFromBorrowedMagazines(integerISSN, integerNumber) == null)
            System.out.println("This member has never borrowed this magazine or has returned it");
        else {
            Magazine magazine = member.getMagazineFromBorrowedMagazines(integerISSN, integerNumber);
            member.returnMagazine(magazine);
            Library.addMagazine(magazine);
            int newBalance = (int) (member.getBalance() + (0.8 * magazine.getPrice()));
            member.setBalance(newBalance);
            System.out.println(member.getName() + " returned " + magazine.getName() + " successfully");
        }
    }

    private static void donateBook(Matcher matcher) {
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        String bookName = matcher.group("bookName");
        String ISBN = matcher.group("ISBN");
        int integerISBN = Integer.parseInt(ISBN);
        String author = matcher.group("author");
        String language = matcher.group("language");
        String price = matcher.group("price");
        int integerPrice = Integer.parseInt(price);
        String amount = matcher.group("amount");
        int integerAmount = Integer.parseInt(amount);
        Member member = Library.getMemberByID(integerID);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else {
            int newBalance = member.getBalance();
            if (integerAmount != 0)
                System.out.println(member.getName() + " donated " + integerAmount + " books successfully");
            while (integerAmount != 0) {
                Book book = new Book(bookName, author, language, integerPrice, integerISBN);
                Library.addBook(book);
                newBalance += (int) (0.4 * book.getPrice());
                integerAmount--;
            }
            member.setBalance(newBalance);
        }
    }

    private static void returnMagazineAndBorrowNext(Matcher matcher) {
        String ISSN = matcher.group("ISSN");
        int integerISSN = Integer.parseInt(ISSN);
        String ID = matcher.group("ID");
        int integerID = Integer.parseInt(ID);
        String number = matcher.group("number");
        int integerNumber = Integer.parseInt(number);
        int nextNumber = integerNumber + 1;

        Member member = Library.getMemberByID(integerID);
        if (member == null)
            System.out.println(MenuMassages.MEMBER_DOES_NOT_EXIST.output);
        else if (member.getMagazineFromBorrowedMagazines(integerISSN, integerNumber) == null)
            System.out.println("This member has never borrowed this magazine or has returned it");
        else if (Library.getMagazine(integerISSN, nextNumber) == null)
            System.out.println("The library does not have the next issue of this magazine");
        else {
            Magazine borrowedMagazine = Library.getMagazine(integerISSN, integerNumber);
            Magazine nextMagazine = Library.getMagazine(integerISSN, nextNumber);
            Library.addMagazine(borrowedMagazine);
            member.returnMagazine(borrowedMagazine);
            Library.removeMagazine(nextMagazine);
            member.borrowMagazine(nextMagazine);
            System.out.println(
                    member.getName() + " returned " + borrowedMagazine.getName() + " and borrowed the next issue");
        }
    }

    private static void printBooks(Matcher matcher) {
        System.out.println("List of all books:");
        Library.sortBooks();
        ArrayList<Book> books = Library.getBooks();
        for (Book book : books) {
            int ISBN = book.getISBN();
            String bookName = book.getName();
            System.out.println(ISBN + ": " + bookName);
        }
    }

    private static void printMagazines(Matcher matcher) {
        System.out.println("List of all magazines:");
        Library.sortMagazines();
        ArrayList<Magazine> magazines = Library.getMagazines();
        for (Magazine magazine : magazines) {
            System.out.println(magazine.getISSN() + ": " + magazine.getName() + " " + magazine.getNumber());
        }
    }
}
