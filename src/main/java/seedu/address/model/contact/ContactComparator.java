package seedu.address.model.contact;

import java.util.Comparator;

/**
 * An abstract comparator for sorting contacts based on order.
 */
public abstract class ContactComparator implements Comparator<Contact> {
    /** The orders that can be used for sorting contacts. */
    public static enum Order {
        ASCENDING, DESCENDING
    }
}
