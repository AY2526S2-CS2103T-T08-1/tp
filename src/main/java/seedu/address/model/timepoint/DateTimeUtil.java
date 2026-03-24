package seedu.address.model.timepoint;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtil {
    public static String toDisplayString(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm, MMM d, yyyy", Locale.ROOT);
        return ldt.format(formatter);
    }
}
