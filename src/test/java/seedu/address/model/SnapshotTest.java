package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SnapshotTest {
    @Test
    public void equalsTest() {
        Model model = new ModelManager();
        Snapshot snapshot = model.getSnapshot();
        assertTrue(snapshot.equals(snapshot));
        assertTrue(snapshot.equals(model.getSnapshot()));
        assertFalse(snapshot.equals(null));
        assertFalse(snapshot.equals(model));
    }
}
