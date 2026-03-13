package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.TypicalContacts.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

/**
 * Contains tests for {@code ContactCard}.
 */
public class ContactCardTest extends GuiUnitTest {
    @Test
    public void constructor_success() throws Exception {
        runAndWait(() -> {
            assertDoesNotThrow(() -> new ContactCard(ALICE, 1));
        });
    }

    @Test
    public void setContact_contactWithAllFields_success() throws Exception {
        runAndWait(() -> {
            Contact fullContact = new ContactBuilder()
                    .withName("Full Contact")
                    .withPhone("12345678")
                    .withEmail("test@example.com")
                    .withAddress("123 Test St")
                    .withTags("tag1", "tag2")
                    .withNotes("Test notes")
                    .build();
            assertDoesNotThrow(() -> new ContactCard(fullContact, 1));
        });
    }

    @Test
    public void setContact_contactWithOnlyPhone_success() throws Exception {
        runAndWait(() -> {
            Contact phoneOnlyContact = new ContactBuilder()
                    .withName("Minimal Contact")
                    .withPhone("12345678")
                    .build();
            assertDoesNotThrow(() -> new ContactCard(phoneOnlyContact, 2));
        });
    }

    @Test
    public void setContact_contactWithOnlyEmail_success() throws Exception {
        runAndWait(() -> {
            Contact emailOnlyContact = new ContactBuilder()
                    .withName("Email Only")
                    .withEmail("test@example.com")
                    .build();
            assertDoesNotThrow(() -> new ContactCard(emailOnlyContact, 3));
        });
    }

    @Test
    public void setContact_contactWithTags_success() throws Exception {
        runAndWait(() -> {
            Contact emailOnlyContact = new ContactBuilder()
                    .withName("Email Only")
                    .withEmail("test@example.com")
                    .withTags("tag1", "tag2")
                    .build();
            assertDoesNotThrow(() -> new ContactCard(emailOnlyContact, 4));
        });
    }

    @Test
    public void setContact_contactWithNotes_success() throws Exception {
        runAndWait(() -> {
            Contact notesOnlyContact = new ContactBuilder()
                    .withName("Notes Only")
                    .withEmail("test@example.com")
                    .withNotes("Some notes here")
                    .build();
            assertDoesNotThrow(() -> new ContactCard(notesOnlyContact, 5));
        });
    }
}
