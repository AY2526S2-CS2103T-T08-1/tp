package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Closes the contact detail view panel.
 */
public class CloseCommand extends Command {

    public static final String COMMAND_WORD = "close";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Closes the contact detail panel.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_CLOSE_PANEL_SUCCESS = "Contact detail panel closed.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_CLOSE_PANEL_SUCCESS, false, false, null, true);
    }
}
