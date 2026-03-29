package seedu.address.commons.core;

import seedu.address.ui.UiUtil;

import java.util.Map;

public abstract class Theme {
    public static final Map<String, String> AVAILABLE_THEMES = Map.of(
            "dark", UiUtil.getUrl("DarkTheme.css").toString(),
            "light", UiUtil.getUrl("LightTheme.css").toString(),
            "warm", UiUtil.getUrl("ReadingMode.css").toString(),
            "sakura", UiUtil.getUrl("Sakura.css").toString()
    );

    public static final String AVAILABLE_THEMES_MESSAGE = "Available themes:\n -dark\n -light\n -warm\n -sakura";
}
