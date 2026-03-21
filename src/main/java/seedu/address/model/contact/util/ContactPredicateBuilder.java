package seedu.address.model.contact.util;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.contact.Contact;

/**
 * A utility class to help with building Contact predicates.
 */
public class ContactPredicateBuilder {
    private Predicate<Contact> contactPredicates;

    public ContactPredicateBuilder() {
        contactPredicates = contact -> true;
    }

    /**
     * Adds predicates that search for keywords in the {@code Contact}.
     */
    public ContactPredicateBuilder containsKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            contactPredicates = contactPredicates.and(contact -> contact.contains(keyword));
        }
        return this;
    }

    /**
     * Adds predicates that search for keywords in the {@code Name} of the {@code Contact}.
     */
    public ContactPredicateBuilder nameContainsKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            contactPredicates = contactPredicates.and(contact -> contact.containsInName(keyword));
        }
        return this;
    }

    /**
     * Adds predicates that search for keywords in the {@code Phone} of the {@code Contact}.
     */
    public ContactPredicateBuilder phoneContainsKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            contactPredicates = contactPredicates.and(contact -> contact.containsInPhone(keyword));
        }
        return this;
    }

    /**
     * Adds predicates that search for keywords in the {@code Email} of the {@code Contact}.
     */
    public ContactPredicateBuilder emailContainsKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            contactPredicates = contactPredicates.and(contact -> contact.containsInEmail(keyword));
        }
        return this;
    }

    /**
     * Adds predicates that search for keywords in the {@code Address} of the {@code Contact}.
     */
    public ContactPredicateBuilder addressContainsKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            contactPredicates = contactPredicates.and(contact -> contact.containsInAddress(keyword));
        }
        return this;
    }

    /**
     * Adds predicates that search for keywords in the {@code Tags} of the {@code Contact}.
     */
    public ContactPredicateBuilder tagsHasKeywords(List<String> keywords) {
        for (String keyword : keywords) {
            contactPredicates = contactPredicates.and(contact -> contact.hasTag(keyword));
        }
        return this;
    }

    public Predicate<Contact> build() {
        return contactPredicates;
    }
}
