package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;

/**
 * Contains tests for {@code ContactListPanel}.
 */
public class ContactListPanelTest extends GuiUnitTest {
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private ContactListPanel contactListPanel = new ContactListPanel(contacts);

    @Test
    public void emptyListScrollToTopTest() throws Exception {
        runAndWait(() -> {
            assertDoesNotThrow(() -> contactListPanel.scrollToTop());
        });
    }

    @Test
    public void emptyListScrollToBottomTest() throws Exception {
        runAndWait(() -> {
            assertDoesNotThrow(() -> contactListPanel.scrollToBottom());
        });
    }
}
