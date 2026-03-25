package seedu.address.ui;

import javafx.scene.control.ButtonType;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteFileAlertTest extends GuiUnitTest {
    @Test
    public void constructor_success() throws Exception {
        runAndWait(() -> {
            DeleteFileAlert alert = new DeleteFileAlert("test.json", 10);
            alert.show();
            assertEquals(ButtonType.CANCEL, alert.getResult());
        });
    }
}
