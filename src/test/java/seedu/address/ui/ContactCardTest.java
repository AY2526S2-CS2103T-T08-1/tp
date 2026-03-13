package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContacts.ALICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Note;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains tests for {@code ContactCard}.
 */
public class ContactCardTest extends GuiUnitTest {
    private static final Name NAME = new Name("Alice Pauline");
    private static final Optional<Phone> PHONE = Optional.of(new Phone("91234567"));
    private static final Optional<Phone> EMPTY_PHONE = Optional.empty();
    private static final Optional<Email> EMAIL = Optional.of(new Email("test@example.com"));
    private static final Optional<Email> EMPTY_EMAIL = Optional.empty();
    private static final Optional<Address> ADDRESS = Optional.of(new Address("123 Main Street"));
    private static final Optional<Address> EMPTY_ADDRESS = Optional.empty();
    private static final List<Note> NOTES = List.of(new Note("Sample notes"));
    private static final List<Note> EMPTY_NOTES = new ArrayList<>();
    private static final Set<Tag> TAGS = Set.of(new Tag("friends"));
    private static final Set<Tag> EMPTY_TAGS = Set.of();

    @Test
    public void constructor_success() throws Exception {
        runAndWait(() -> {
            assertDoesNotThrow(() -> new ContactCard(ALICE, 1));
        });
    }

    @Test
    public void setContact_contactWithAllFields_success() throws Exception {
        runAndWait(() -> {
            Contact fullContact = new Contact(NAME, PHONE, EMAIL, ADDRESS, NOTES, TAGS);
            ContactCard contactCard = new ContactCard(fullContact, 1);
            assertTrue(contactCard.isNameVisible());
            assertTrue(contactCard.isPhoneVisible());
            assertTrue(contactCard.isEmailVisible());
            assertTrue(contactCard.isAddressVisible());
            assertTrue(contactCard.isNotesVisible());
            assertTrue(contactCard.isTagsVisible());
        });
    }

    @Test
    public void setContact_contactWithOnlyPhone_success() throws Exception {
        runAndWait(() -> {
            Contact phoneOnlyContact = new Contact(NAME, PHONE, EMPTY_EMAIL, EMPTY_ADDRESS, EMPTY_NOTES, EMPTY_TAGS);
            ContactCard contactCard = new ContactCard(phoneOnlyContact, 2);
            assertTrue(contactCard.isNameVisible());
            assertTrue(contactCard.isPhoneVisible());
            assertFalse(contactCard.isEmailVisible());
            assertFalse(contactCard.isAddressVisible());
            assertFalse(contactCard.isNotesVisible());
            assertFalse(contactCard.isTagsVisible());
        });
    }

    @Test
    public void setContact_contactWithOnlyEmail_success() throws Exception {
        runAndWait(() -> {
            Contact emailOnlyContact = new Contact(NAME, EMPTY_PHONE, EMAIL, EMPTY_ADDRESS, EMPTY_NOTES, EMPTY_TAGS);
            ContactCard contactCard = new ContactCard(emailOnlyContact, 3);
            assertTrue(contactCard.isNameVisible());
            assertFalse(contactCard.isPhoneVisible());
            assertTrue(contactCard.isEmailVisible());
            assertFalse(contactCard.isAddressVisible());
            assertFalse(contactCard.isNotesVisible());
            assertFalse(contactCard.isTagsVisible());
        });
    }

    @Test
    public void setContact_contactWithTags_success() throws Exception {
        runAndWait(() -> {
            Contact emailOnlyContact = new Contact(NAME, EMPTY_PHONE, EMAIL, EMPTY_ADDRESS, EMPTY_NOTES, TAGS);
            ContactCard contactCard = new ContactCard(emailOnlyContact, 4);
            assertTrue(contactCard.isNameVisible());
            assertFalse(contactCard.isPhoneVisible());
            assertTrue(contactCard.isEmailVisible());
            assertFalse(contactCard.isAddressVisible());
            assertFalse(contactCard.isNotesVisible());
            assertTrue(contactCard.isTagsVisible());
        });
    }

    @Test
    public void setContact_contactWithNotes_success() throws Exception {
        runAndWait(() -> {
            Contact notesOnlyContact = new Contact(NAME, EMPTY_PHONE, EMAIL, EMPTY_ADDRESS, NOTES, EMPTY_TAGS);
            ContactCard contactCard = new ContactCard(notesOnlyContact, 5);
            assertTrue(contactCard.isNameVisible());
            assertFalse(contactCard.isPhoneVisible());
            assertTrue(contactCard.isEmailVisible());
            assertFalse(contactCard.isAddressVisible());
            assertTrue(contactCard.isNotesVisible());
            assertFalse(contactCard.isTagsVisible());
        });
    }
}
