package view.enums.massages;

import model.Library;

public enum MenuMassages {

    LIBRARY_CREATED_SUCCESFULLY("Library created successfully"),
    MEMBER_EXIST("A member with this ID already exists"),
    ACCOUNT_CREATED_SUCCESFULLY("Account created successfully"),
    MEMBER_DOES_NOT_EXIST("No member with this ID exists"),
    BALANCE_INCREASED("Balance increased successfully"),
    BOOK_DOSE_NOT_EXIST("No book with this ISBN was found in the library"),
    BALANCE_IS_NOT_ENOUGH("The member's balance is not enough"),
    MAGAZINE_DOES_NOT_EXIST("No magazine with this ISSN and number was found in the library"),
    NO_BORROWED_BOOK("This member has never borrowed this book or has returned it"),
    NO_BORROWED_MAGAZINE("This member has never borrowed this magazine or has returned it"),
    NO_HAVE_NEXT_MAGAZINE("The library does not have the next issue of this magazine"),
    BOOKS_LIST("List of all books:"),
    MAGAZINES_LIST("List of all magazines:");

    public String output;

    // Constructor
    MenuMassages(String output) {
        this.output = output;
    }
}
