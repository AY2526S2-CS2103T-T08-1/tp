package seedu.address.ui;

import static seedu.address.ui.UiManager.hide;
import static seedu.address.ui.UiManager.show;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.contact.Contact;
import seedu.address.model.timepoint.DateTimeTimePoint;

/**
 * A UI component that displays detailed information of a {@code Contact}.
 */
public class ContactDetailPanel extends UiPart<Region> {

    private static final String FXML = "ContactDetailPanel.fxml";

    @FXML
    private VBox detailPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private Label lastContacted;
    @FXML
    private Label lastUpdated;
    @FXML
    private VBox notes;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox tagsContainer;
    @FXML
    private VBox phoneContainer;
    @FXML
    private VBox emailContainer;
    @FXML
    private VBox addressContainer;
    @FXML
    private VBox lastContactedContainer;
    @FXML
    private VBox lastUpdatedContainer;
    @FXML
    private VBox notesContainer;

    /**
     * Creates an empty {@code ContactDetailPanel}.
     */
    public ContactDetailPanel() {
        super(FXML);
        clearContact();
    }

    /**
     * Updates the panel to display the given contact's details.
     */
    public void setContact(Contact contact) {
        if (contact == null) {
            clearContact();
            return;
        }

        name.setText(contact.getName().fullName);

        // Phone
        if (contact.getPhone().isPresent()) {
            phone.setText(contact.getPhone().get().value);
            show(phoneContainer);
        } else {
            hide(phoneContainer);
        }

        // Email
        if (contact.getEmail().isPresent()) {
            email.setText(contact.getEmail().get().value);
            show(emailContainer);
        } else {
            hide(emailContainer);
        }

        // Address
        if (contact.getAddress().isPresent()) {
            address.setText(contact.getAddress().get().value);
            show(addressContainer);
        } else {
            hide(addressContainer);
        }

        // Last Contacted
        if (contact.getLastContacted().isPresent()) {
            lastContacted.setText(contact.getLastContacted().get().toString());
            show(lastContactedContainer);
        } else {
            hide(lastContactedContainer);
        }

        // Last Updated
        lastUpdated.setText(DateTimeTimePoint.stringify(contact.getLastUpdated()));
        show(lastUpdatedContainer);

        // Notes
        notes.getChildren().clear();
        if (!contact.getNotes().isEmpty()) {
            contact.getNotes().forEach(note -> {
                NoteLabel reminderLabel = new NoteLabel(note, notes.getStyleClass().toString());
                reminderLabel.hideHeader();
                notes.getChildren().add(reminderLabel); });
            show(notesContainer);
        } else {
            hide(notesContainer);
        }

        // Tags
        tags.getChildren().clear();
        if (!contact.getTags().isEmpty()) {
            contact.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.name))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.name)));
            show(tagsContainer);
        } else {
            hide(tagsContainer);
        }

        show(detailPane);
    }

    /**
     * Clears the contact details panel.
     */
    public void clearContact() {
        name.setText("");
        phone.setText("");
        email.setText("");
        address.setText("");
        lastContacted.setText("");
        lastUpdated.setText("");
        tags.getChildren().clear();
        notes.getChildren().clear();

        hide(phoneContainer);
        hide(emailContainer);
        hide(addressContainer);
        hide(lastContactedContainer);
        hide(lastUpdatedContainer);
        hide(notesContainer);
        hide(tagsContainer);

        hide(detailPane);
    }
}
