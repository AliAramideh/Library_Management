package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuCommands {
    END("\\s*end\\s*"),
    ADD_MEMBER("Add\\s+account\\s+Account-Name\\s+(?<accountName>[a-zA-Z ]+)\\s+Account-Number\\s+(?<ID>\\d{5})\\s*"),
    ACCOUNT_NAME_VALID("(?<accountName>[a-zA-Z ]+\\s+)"),
    ID_VALID("(?<ID>\\\\d{5})"),
    INCREASE_BALANCE("Increase\\s+balance\\s+Account-Number\\s+(?<ID>\\d{5})\\s+Amount\\s+(?<amount>\\d+)\\s*"),
    CASH_OUT("Cashout\\s+Account-Number\\s+(?<ID>\\d{5})"),
    ADD_BOOK(
            "Add\\s+book\\s+Book-Name\\s+(?<bookName>[a-zA-Z\\d ]+)\\s+ISBN\\s+(?<ISBN>\\d+)\\s+Author\\s+(?<author>[a-zA-Z ]+)\\s+Language\\s+(?<language>[a-zA-Z]+)\\s+Price\\s+(?<price>\\d+)\\s+Amount\\s+(?<amount>\\d+)\\s*"),
    ADD_MAGAZINE(
            "Add\\s+magazine\\s+Magazine-Name\\s+(?<magazineName>[a-zA-Z\\d ]+)\\s+ISSN\\s+(?<ISSN>\\d+)\\s*Author\\s+(?<author>[a-zA-Z ]+)\\s+Language\\s+(?<language>[a-zA-Z]+)\\s+Price\\s+(?<price>\\d+)\\s+Amount\\s+(?<amount>\\d+)\\s+Number\\s+(?<number>\\d+)\\s*"),
    // Borrow book ISBN <ISBN> Account-Number <acoount number>
    BORROW_BOOK("Borrow\\s+book\\s+ISBN\\s+(?<ISBN>\\d+)\\s+Account-Number\\s+(?<ID>\\d{5})\\s*"),
    // Borrow magazine ISSN <ISSN> Account-Number <Account number> Number <number>
    BORROW_MAGAZINE(
            "Borrow\\s+magazine\\s+ISSN\\s+(?<ISSN>\\d+)\\s+Account-Number\\s+(?<ID>\\d{5})\\s+Number\\s+(?<number>\\d+)\\s*"),
    // Return book ISBN <ISBN> Account-Number <Account number>
    RETURN_BOOK("Return\\s+book\\s+ISBN\\s+(?<ISBN>\\d+)\\s+Account-Number\\s+(?<ID>\\d{5})\\s*"),
    // Return magazine ISSN <ISSN> Account-Number <Accouunt number> Number <number>
    RETURN_MAGAZINE(
            "Return\\s+magazine\\s+ISSN\\s+(?<ISSN>\\d+)\\s+Account-Number\\s+(?<ID>\\d{5})\\s+Number\\s+(?<number>\\d+)\\s*"),
    // Donate book Account-Number <Account Number> Book-Name <Book name> ISBN <ISBN>
    // Author <author> Language <language> Price <price> Amount <amount>
    DONATE_BOOK(
            "Donate\\s+book\\s+Account-Number\\s+(?<ID>\\d{5})\\s+Book-Name\\s+(?<bookName>[a-zA-Z\\d ]+)ISBN\\s+(?<ISBN>\\d+)\\s+Author\\s+(?<author>[a-zA-Z ]+)\\s+Language\\s+(?<language>[a-zA-Z]+)\\s+Price\\s+(?<price>\\d+)\\s+Amount\\s+(?<amount>\\d+)\\s*"),
    // Return magazine and borrow next ISSN <ISSN> Account-Number <Accouunt number>
    // Number <number>
    RETURN_MAGAZINE_AND_BORROW_NEXT(
            "Return\\s+magazine\\s+and\\s+borrow\\s+next\\s+ISSN\\s+(?<ISSN>\\d+)\\s+Account-Number\\s+(?<ID>\\d{5})\\s+Number\\s+(?<number>\\d+)\\s*"),
    // Print books
    PRINT_BOOKS("Print\\s+books\\s*"),
    // Print magazines
    PRINT_MAGAZINE("Print\\s+magazines\\s*");

    public String regex;

    // Constructor
    MenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
