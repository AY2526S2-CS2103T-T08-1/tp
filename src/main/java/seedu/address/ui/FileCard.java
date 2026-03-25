package seedu.address.ui;

import java.io.File;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.timepoint.DateTimeUtil;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * A UI component that displays information of a B2B4U file.
 */
public class FileCard extends UiPart<Region> {
    private static final String FXML = "FileListCard.fxml";
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public final File file;

    @FXML
    private Label name;
    @FXML
    private Label lastModified;
    @FXML
    private Label contactCount;

    /**
     * Creates a {@code FileCard} with the given file to display.
     */
    public FileCard(File file) {
        super(FXML);
        this.file = file;
        name.setText(file.getName());
        LocalDateTime lastModifiedTime = Instant.ofEpochMilli(file.lastModified()).atZone(ZONE_ID).toLocalDateTime();
        lastModified.setText("Last modified: " + DateTimeUtil.toDisplayString(lastModifiedTime));
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(Path.of(file.getPath()));
        try {
            Optional<ReadOnlyAddressBook> addressBookOptional = addressBookStorage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                contactCount.setText("0");
            }
            contactCount.setText(addressBookOptional.get().getContactList().size() + "");
        } catch (DataLoadingException e) {
            contactCount.setText("Error retrieving contact list");
        }
    }
}
