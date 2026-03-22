package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactFieldComparator;
import seedu.address.model.contact.ContactTagComparator;

public class SortCommandParserTest {
    private static final SortCommandParser PARSER = new SortCommandParser();

    @Test
    public void parse_emptyArg_returnsSortCommand() {
        assertParseSuccess(PARSER, "     ", new SortCommand());
    }

    @Test
    public void parse_validArgs_returnsSortCommand() throws ParseException {
        assertParseSuccess(PARSER, " n/ASC t/friends:DESC",
                new SortCommand(new ContactFieldComparator(ContactFieldComparator.Field.NAME,
                        ContactFieldComparator.Order.ASCENDING)
                        .thenComparing(new ContactTagComparator("friends", ContactTagComparator.Order.DESCENDING))));
    }
}
