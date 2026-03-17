package seedu.address.logic.commands;

import static seedu.address.logic.commands.CloseCommand.MESSAGE_CLOSE_PANEL_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CloseCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_close_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_CLOSE_PANEL_SUCCESS, false, false, null, true);
        assertCommandSuccess(new CloseCommand(), model, expectedCommandResult, expectedModel);
    }
}
