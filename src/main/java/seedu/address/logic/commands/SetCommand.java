package seedu.address.logic.commands;

import seedu.address.commons.core.Theme;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_THEME;

/**
 * Parent class for all setting-related commands which alter {@code UserPrefs}.
 */
public abstract class SetCommand extends Command {

    public static final String COMMAND_WORD = "set";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes settings.\n"
            + COMMAND_WORD + " " + PREFIX_FILE + "FILE_NAME to use a different contact list file.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_FILE + "new_file"
            + "Or: " + COMMAND_WORD + " " + PREFIX_THEME + "THEME_NAME to change the theme.\n"
            + Theme.AVAILABLE_THEMES_MESSAGE;
}
