package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ThemeCommandTest {

    @Test
    public void invalidTheme_failure() {
        assertThrows(AssertionError.class, () -> new ThemeCommand("foo"));
    }

    @Test
    public void executeTest() {
        Model model = new ModelManager();
        ThemeCommand themeCommand = new ThemeCommand("light");
        String expectedMessage = String.format(ThemeCommand.MESSAGE_SUCCESS, "light");
        Model expectedModel = new ModelManager();
        UserPrefs expectedUserPrefs = new UserPrefs();
        expectedUserPrefs.setTheme("light");
        assertCommandSuccess(themeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final ThemeCommand themeCommand = new ThemeCommand("dark");

        // same object -> returns true
        assertTrue(themeCommand.equals(themeCommand));

        // same theme word -> returns true
        assertTrue(themeCommand.equals(new ThemeCommand("dark")));

        // null -> returns false
        assertFalse(themeCommand.equals(null));

        // different types -> returns false
        assertFalse(themeCommand.equals(new ClearCommand()));

        // different theme word -> returns false
        assertFalse(themeCommand.equals(new ThemeCommand("light")));
    }
}
