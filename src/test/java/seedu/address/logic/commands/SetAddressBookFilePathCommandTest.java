package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SetAddressBookFilePathCommandTest {
    private static final String FILE_NAME = "new_book";

    @Test
    public void equals() {
        final SetAddressBookFilePathCommand standardCommand = new SetAddressBookFilePathCommand(FILE_NAME);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new SetAddressBookFilePathCommand("NEWBOOK")));
    }
}
