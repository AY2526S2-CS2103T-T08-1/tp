---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# B2B4U User Guide

Business to Business for You (B2B4U) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, B2B4U can get your contact management tasks done faster than traditional GUI apps.

B2B4U is designed for **consultants at medium-sized consulting or PR agencies** who need to manage long-term relationships with clients and companies. A contact in B2B4U can represent an individual person or a business entity, with optional fields to accommodate different contact types (e.g. a business may not have a physical address). It provides quick, up-to-date access to client data through a simple, unified interface — helping you deliver fast and on-point responses.

## Table of Contents

- [B2B4U User Guide](#b2b4u-user-guide)
  - [Table of Contents](#table-of-contents)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Command Format](#command-format)
    - [Flexible Time Input](#flexible-time-input)
    - [Contact Fields and Prefixes](#contact-fields-and-prefixes)
    - [Adding Contacts](#adding-contacts)
    - [Editing Contacts](#editing-contacts)
    - [Deleting Contacts](#deleting-contacts)
      - [Delete a Specific Contact](#delete-a-specific-contact)
      - [Clear All Contacts](#clear-all-contacts)
    - [Viewing a Contact](#viewing-a-contact)
    - [Managing Notes for a Contact](#managing-notes-for-a-contact)
      - [Reminders](#reminders)
    - [Filtering and Sorting the Contact List](#filtering-and-sorting-the-contact-list)
    - [Undo and Redo](#undo-and-redo)
    - [Setting the Theme](#setting-the-theme)
    - [Saving Data](#saving-data)
    - [Maintaining Separate Data Files](#maintaining-separate-data-files)
    - [Editing the Data File Directly](#editing-the-data-file-directly)
    - [Exiting B2B4U](#exiting-b2b4u)
  - [FAQ](#faq)
  - [Known Issues](#known-issues)
  - [Command Summary](#command-summary)
  - [Appendix: Command Details](#appendix-command-details)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure **Java 17 or later** is installed on your device.

   > **Mac users:** Ensure you have the exact JDK version specified [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `B2B4U.jar` file from [here](https://github.com/AY2526S2-CS2103T-T08-1/tp/releases).

3. Copy the file to the folder you want to use as the _home directory_ for B2B4U.

4. Launch the application using one of the following methods:
   - **Double-click** the `B2B4U.jar` file.
   - **Terminal:** Navigate to the folder containing `B2B4U.jar` and run:
     ```
     java -jar B2B4U.jar
     ```

<div style="page-break-after: always;"></div>

5. Within a few seconds, a GUI similar to the one below should appear. The app launches with **sample data** preloaded.

   ![Ui]({{ baseUrl }}/images/Ui.png)

6. Type a command in the command box and press **Enter** to execute it. For example, typing `help` and pressing Enter opens the help window.

   Here are some commands to try:

   | Command                                                                          | Description                                  |
   |----------------------------------------------------------------------------------|----------------------------------------------|
   | `list`                                                                           | Lists all contacts.                          |
   | `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` | Adds a contact named `John Doe`.             |
   | `view 1`                                                                         | Opens the detail panel for the 1st contact.  |
   | `close view`                                                                     | Closes the contact detail panel.             |
   | `delete 3`                                                                       | Deletes the 3rd contact in the current list. |
   | `clear`                                                                          | Deletes all contacts.                        |
   | `exit`                                                                           | Exits the app.                               |

7. Refer to [Features](#features) below for the full list of commands, or visit each command's subpage for additional details.

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Features

### Command Format

<box type="info" seamless>

**Notes about the command format:**

- Words in `SCREAMING_SNAKE_CASE` are parameters to be supplied by you.
  e.g. in `add n/NAME`, `NAME` is a parameter, so you might write `add n/John Doe`.

- Items in square brackets are **optional**.
  e.g. `n/NAME [t/TAG]` can be written as `n/John Doe t/friend` or simply `n/John Doe`.

- Items followed by `…` can be used **zero or more times**.
  e.g. `[t/TAG]…` can be omitted entirely, or written as `t/friend`, `t/friend t/family`, etc.

- Prefixed parameters (e.g. `t/TAG`, `open/FILE`) can be in any order, except in the `sort` command where order affects results.
  e.g. `n/NAME p/PHONE` and `p/PHONE n/NAME` are both acceptable.

- Commands that do not take parameters (such as `list`, `exit`, and `clear`) will fail if extra parameters are provided.
  e.g. `exit 123` will fail.

- If you are copying commands from a PDF version of this document, be careful when commands span multiple lines — spaces around line breaks may be lost when pasted.

- Unrecognized commands will display an error message.
  ![unknown command]({{ baseUrl }}/images/unknownCommand.png)

</box>

<div style="page-break-after: always;"></div>

### Flexible Time Input

A contact may contain fields that require a date/time as input.
For this purpose, B2B4U supports flexible date and time input formats.
To successfully input a date, the user input must fulfill the following criteria:
- The input must contain exactly one word parameter `MONTH` which represents the month:
  - Valid formats for `MONTH`: 
    - The full name of the month (e.g. `January`, `March`), case-insensitive (e.g. `january` will be accepted as `January`, `MARCH` will be accepted as `March`).
    - A three-letter abbreviation of the month (e.g. `Jan`, `Mar`), case-insensitive (e.g. `jan` will be accepted as `Jan`, `MAR` will be accepted as `Mar`).
- The input must contain exactly one numeric parameter `DAY` which represents the day of the month:
- `DAY` and `MONTH` must be separated by a valid `SEPARATOR`:
  - A `SEPARATOR` can be any one of the following characters: `/`, `\`, `-`, `,` or ` `(whitespace).
  - Additional whitespaces in between the `SEPARATOR` and the parameters are allowed.
- `DAY` and `MONTH` must in combination form a valid date with the given year(how the year may be input is explained below).

Additionally, the input may contain the following parameters:
- Exactly one 4-digit numeric parameter `YEAR` which represents the year:
  - If `YEAR` is absent, the current year will be used for the date on default.
  - As mentioned above, `YEAR` must form a valid date with the given `DAY` and `MONTH`.
- Exactly one parameter `TIME` which represents the time of day, which can be any one of the following formats:
  - 24 hour `HH:MM` format:
     - If the hour can be represented as a single digit, the first zero can be omitted. (e.g. 07:30 and 7:30 will both be accepted as 7:30AM)
     - The time must be valid: `HH` must be between 0 and 23(inclusive), `MM` must be between 00 and 59(inclusive).
  - 12 hour `HH:MM(AM/PM)` format:
    - The constraints for the `HH:MM` are similar to 24 hour `HH:MM` format, with the exception that `HH` must be between 1 and 12(inclusive).
    - There must be exactly one Meridiem suffix(`AM` or `PM`) at the end of the time.
    - The Meridiem suffix must be directly attached to the clock time(`HH:MM`) and cannot be separated by any other character(s).
    - Example: `7:30AM`, `12:00PM`, `11:59PM`
  - 12 hour simplified `HH(AM/PM)` format:
    - Similar to the 12 hour `HH:MM(AM/PM)` format with the colon and minute parameter is omitted.
    - Example: `11AM` which will be the same as `11:00` and `11:00AM`
  - `TIME` will only be parsed if provided alongside a valid date.
- All parameters must be similarly separated by a valid `SEPARATOR`.

The date/time input should fall into at least one of the following patterns, rather than randomly ordered:

<box type="info" seamless>

**Note:** In the following tables, `SEPARATOR` will be represented by `/`.

</box>

- Partial date(containing only `DAY` and `MONTH`):

| Pattern   | Format      | Example  |
|-----------|-------------|----------|
| Day-month | `DAY/MONTH` | `31 Oct` |
| Month-day | `MONTH/DAY` | `Oct 31` |

- Date only(containing only `DAY`, `MONTH` and `YEAR`):

| Pattern               | Format           | Example       |
|-----------------------|------------------|---------------|
| Little-endian         | `DAY/MONTH/YEAR` | `31 Oct 2026` |
| Middle-endian         | `MONTH/DAY/YEAR` | `Oct 31 2026` |
| Big-endian            | `YEAR/MONTH/DAY` | `2026 Oct 31` |
| Reverse middle-endian | `YEAR/DAY/MONTH` | `2026 31 Oct` |

<div style="page-break-after: always;"></div>

- Partial date + time(containing only `DAY`, `MONTH` and `TIME`):

| Pattern                 | Format           | Example         |
|-------------------------|------------------|-----------------|
| Time-prefixed day-month | `TIME/DAY/MONTH` | `12:00 31 Oct`  |
| Time-prefixed month-day | `TIME/MONTH/DAY` | `12:00 Oct 31`  |
| Time-suffixed day-month | `DAY/MONTH/TIME` | `31 Oct 12:00`  |
| Time-suffixed month-day | `MONTH/DAY/TIME` | `Oct 31 12:00`  |

- Full date + time(containing `DAY`, `MONTH`, `YEAR`, and `TIME`):

| Pattern                             | Format                | Example             |
|-------------------------------------|-----------------------|---------------------|
| Time-prefixed little-endian         | `TIME/DAY/MONTH/YEAR` | `12:00 31 Oct 2026` |
| Time-prefixed middle-endian         | `TIME/MONTH/DAY/YEAR` | `12:00 Oct 31 2026` |
| Time-prefixed big-endian            | `TIME/YEAR/MONTH/DAY` | `12:00 2026 Oct 31` |
| Time-prefixed reverse middle-endian | `TIME/YEAR/DAY/MONTH` | `12:00 2026 31 Oct` |
| Time-suffixed little-endian         | `DAY/MONTH/YEAR/TIME` | `12:00 31 Oct 2026` |
| Time-suffixed middle-endian         | `MONTH/DAY/YEAR/TIME` | `Oct 31 2026 12:00` |
| Time-suffixed big-endian            | `YEAR/MONTH/DAY/TIME` | `2026 Oct 31 12:00` |
| Time-suffixed reverse middle-endian | `YEAR/DAY/MONTH/TIME` | `2026 31 Oct 12:00` |

<box type="info" seamless>

**Note:** For additional ease of input, B2B4U also allows the following input keywords which can be parsed as a date based on the current system time.

| Keyword                               | Result                                            |
|---------------------------------------|---------------------------------------------------|
| `today` or `tdy`                      | Current system date                               |
| `tomorrow` or `tmrw` or `tmr`         | The day after the current system date             |
| `yesterday` or `yst` or `yday`        | The day before the current system date            |
| `next week`                           | 7 days after the current system date              |
| `sunday` or `sun`                     | The first Sunday after the current system date    |
| `monday` or `mon`                     | The first Monday after the current system date    |
| `tuesday`, `tues` or `tue`            | The first Tuesday after the current system date   |
| `wednesday` or `wed`                  | The first Wednesday after the current system date |
| `thursday`, `thurs`, `thur`, or `thu` | The first Thursday after the current system date  |
| `friday` or `fri`                     | The first Friday after the current system date    |
| `saturday` or `sat`                   | The first Saturday after the current system date  |

The keyword input is case-insensitive. <br>
Only one of such keywords may be used in a singular input regarding time, and if used, it can replace the above "month" and "day" input requirements entirely. <br>
Example: On 24 March 2026, inputting `next week 12PM` for a [reminder](#reminders-2) sets the reminder for 12:00PM 31 March 2026.
</box>

Once a valid date or time hsa been accepted as input, it will be displayed by B2B4U in the following formats:
- A date is displayed in "`Mth` `DAY`, `YEAR`" format, wherein `Mth` is the month abbreviated to three letters with only its first letter capitalised.
  - Example: 31 October 2026 will be displayed as "Oct 31, 2026".
- A date with time is displayed in "`HH:mm`, `Mth` `DAY`, `YEAR`" format.
  - Example: 12:00 noon on 31 October 2026 will be displayed as "12:00, Oct 31, 2026".

<box type="info" seamless>

**Note:** Both of the above display formats are also accepted input formats by B2B4U.

</box>

To allow for user freedom, it is not necessary that the user creates an input that is a valid date, or even an input that resembles a time for a field related to time. <br>
This allows freedom for the following use cases:
- If the user cannot remember clearly the last time they had contacted someone but wants to note down that is has been a while since they were in contact, they can edit their contact's `LastContacted` field to the phrase "over a year ago".
- If the user is planning to meet someone sometime in December, however it is currently January and much of their other plans are yet to be set in stone, they can add a reminder with its time set to "sometime in December", then edit its date once they have finalised on a more concrete timing.

This does mean that if an attempt to input a date contains an input that cannot be successfully parsed as one, it will be recorded as is.
This can be identified to be the case when the field's data appears just like its input, rather than in the standard format of representing a date/time in B2B4U, explained above. <br>
If an incorrectly input date/time is indeed recorded, the user should use one of the edit commands or the [`undo`](#undo-and-redo) command to correct their mistake.

### Contact Fields and Prefixes

**Name(**`n/`**):**
- The contact's name.
- Can contain symbols `.` `'` `-` `_` `&` `/` and letter characters from any language.
- First character must be a letter character.
- Must be non-empty.

**Phone number(**`p/`**):**
- The contact's phone number.
- Can only contain '+' and digits.
- Without a country code, should be 5-14 digits long. 
- With a country code (starting with '+'), should be 8-15 digits long.

**Email(**`e/`**):**
- The contact's email address.
- Should be of the format `local-part@domain`
  - Constraints of `local-part`:
    - Must contain only alphanumeric characters and the special characters `.`, `_`, `%`, `+`, and `-`.
    - Must start and end with an alphanumeric character.
    - Is at least 1 character long.
    - Cannot have consecutive periods.
  - `domain` consists of one or more `label`s separated by periods. Constraints of `label`:
    - Must start and end with an alphanumeric character.
    - Must contain only alphanumeric characters or hyphens.
    - Is at least 1 character long
  - The last domain label (top-level domain) must be at least 2 characters long, and must be alphabetic.

**Address(**`a/`**):**
- The contact's address.
- Can take any values, but should not be blank.

**Last Contacted(**`lc/`**):**
- The last time the user has contacted this person.
- A non-blank [time-related input](#flexible-time-input).

**Tag(**`t/`**):**
- A tag to add onto the contact.
- Can only contain alphanumeric characters.

**Notes:**
- See [Managing Notes for a Contact](#managing-notes-for-a-contact).

### Adding Contacts

To add a new contact, use the [`add` command](#adding-a-contact-add).

Format: `add n/NAME (p/PHONE | e/EMAIL) [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

- At least **one** (or both) of `p/PHONE` or `e/EMAIL` must be provided.
- After adding, if similar contacts exist, the list will filter to show them.

**Examples:**
- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
- `add n/Alex Tan p/91234567`

### Editing Contacts

To edit an existing contact, use the [`edit` command](#editing-a-contact-edit).

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

- At least **one** optional field must be provided.
- Editing tags **replaces all existing tags** rather than adding to them.
- You can remove an optional field by providing its prefix with no value (e.g. `t/` to clear all tags, `p/` to remove the phone number).

**Examples:**
- `edit 1 p/91234567 e/johndoe@example.com`
- `edit 2 n/Betsy Crower t/` — renames and clears all tags

### Deleting Contacts

- To delete a specific contact, use the [`delete` command](#deleting-a-contact-delete).
- To delete all contacts at once, use the [`clear` command](#clearing-all-contacts-clear).

#### Delete a Specific Contact

Format: `delete INDEX`

- `INDEX` refers to the index number shown in the displayed contact list.
- The index must be a **positive integer** (e.g. 1, 2, 3…).

**Example:**
- `delete 3` — deletes the 3rd contact in the current list.

#### Clear All Contacts

Format: `clear`

<box type="warning" seamless>

**Caution:** `clear` permanently removes **all** contacts. This action can be undone with the `undo` command.

</box>

### Viewing a Contact

To view the details of a contact, use the [`view` command](#viewing-a-specific-contact-view).

Format: `view INDEX`

- `INDEX` refers to the index number shown in the displayed contact list.
- The index must be a **positive integer** (e.g. 1, 2, 3…).

**Example:**
- `view 1` — opens the detail panel for the 1st contact.

To close the detail panel, use the [`close view` command](#closing-the-view-panel-close-view).

Format: `close view`

### Managing Notes for a Contact

To manage notes and reminders for a contact, use the [`note` command](#managing-notes-reminders-for-a-contact-note).

| Operation                                             | Format                                        | Description                                                                                |
|-------------------------------------------------------|-----------------------------------------------|--------------------------------------------------------------------------------------------|
| [**Add a note**](#add-a-note)                         | `note INDEX NOTE [on/TIME]`                   | Appends a note to the contact. Including `on/TIME` turns it into a [reminder](#reminders). |
| [**Edit a note**](#edit-a-specific-note)              | `note INDEX el/NOTE_INDEX NEW_NOTE [on/TIME]` | Replaces the note at the specified position.                                               |
| [**Remove a specific note**](#remove-a-specific-note) | `note INDEX cl/NOTE_INDEX`                    | Removes the note at the specified position.                                                |
| [**Remove first N notes**](#remove-the-first-n-notes) | `note INDEX co/LINES_TO_REMOVE`               | Removes the first *N* notes, where *N* = `LINES_TO_REMOVE`.                                |
| [**Clear all notes**](#clear-all-notes)               | `note INDEX ca/`                              | Removes all notes from the contact.                                                        |

Notes support **contact references** using the `@INDEX` syntax, which creates a bidirectional association between two contacts. Both contacts will appear when searching for either one using `find @INDEX`.

#### Reminders

Any note with a scheduled time (set using `on/TIME`) becomes a reminder. Contacts with active reminders gain a `Reminder` tag, which turns red when the reminder is due within **7 days**. During this window, a pop-up notification will also appear each time you launch B2B4U. <br>
The input format of `TIME` is [flexible](#flexible-time-input).

![reminder]({{ baseUrl }}/images/reminder.png)

<div style="page-break-after: always;"></div>

### Filtering and Sorting the Contact List

By default, B2B4U displays all contacts sorted by **most urgent reminder** first, followed by **most recently contacted**.

B2B4U also provides commands to filter and sort the contact list, which is useful when managing a large number of contacts.

**Filtering:**

Format: `find [KEYWORD]… [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…`

- Use the [`find` command](#finding-contacts-find) with keywords or field-specific prefixes to filter contacts (e.g. `find n/Alex t/friends`).
- Use `find @INDEX` to find all contacts associated with the contact at that index.
- Use [`find` without any arguments](#clearing-filters) to remove all active filters.

**Sorting:**

Format: `sort [n/asc | desc] [p/asc | desc] [e/asc | desc] [a/asc | desc] [lu/asc | desc] [lc/asc | desc] [t/TAG_NAME:asc | desc]…`

- Use the [`sort` command](#sorting-contacts-sort) with field prefixes and `asc` or `desc` to specify the [sort direction](#sort-order-by-field) (e.g. `sort n/asc` to sort by name A–Z, `sort lc/desc` to sort by last contacted, newest first).
- Use [`sort` without any arguments](#resetting-sort-order) to reset to the default sort order.

Active filters and sort orders are preserved independently — running `sort` will not clear your current filter, and vice versa.

Note that the following commands may also affect the active filter or sort order:
- A [`list` command](#listing-all-contacts-list) displays all contacts in the default sort order.
- An [`add` command](#adding-a-contact-add) resets the sort order and may filter to show only [similar contacts](#similar-contacts).

### Undo and Redo

B2B4U supports undoing and redoing commands to help recover from mistakes.

- To undo the last command, use the [`undo` command](#undoing-a-command-undo).
- To redo the last undone command, use the [`redo` command](#redoing-a-command-redo).

### Setting the Theme

B2B4U includes several color palettes (called "themes") to customize the look of the app.

To switch themes, use the [`theme THEME_NAME` command](#set-theme-theme).

Available themes:

| Theme                              | Command        |
|------------------------------------|----------------|
| [Dark mode](#dark-mode-dark)       | `theme dark`   |
| [Light mode](#light-mode-light)    | `theme light`  |
| [Reading mode](#reading-mode-book) | `theme book`   |
| [Sakura](#sakura-theme-sakura)     | `theme sakura` |
| [Grass](#grass-theme-grass)        | `theme grass`  |
| [Techcore](#techcore-tech)         | `theme tech`   |
| [Jirai Kei](#jirai-kei-jirai)      | `theme jirai`  |

<div style="page-break-after: always;"></div>

### Saving Data

B2B4U automatically saves your data to disk after any command that modifies it. There is no need to save manually.

### Maintaining Separate Data Files

B2B4U supports multiple data files, which is useful for keeping separate contact lists (e.g. work vs. personal contacts). All data files must be stored in the data folder: `[JAR file location]/data/`.

- To view all available data files, use the [`view files` command](#viewing-available-files-view-files).
- To open a data file, use the [`file open/FILE_NAME` command](#open-file-file-open).
- To delete a data file, use the [`file delete/FILE_NAME` command](#deleting-a-file-file-delete).
- To close the file list panel, use the [`close view` command](#closing-the-view-panel-close-view). The file list shares the same panel used to view contact details.

### Editing the Data File Directly

B2B4U data is saved as JSON files in `[JAR file location]/data/`. Advanced users may edit these files directly.

<box type="warning" seamless>

**Caution:** If your edits make the data file's format invalid, B2B4U will discard all data and start with an empty file on the next launch. Always **back up the file** before making direct edits. Certain changes may also cause unexpected app behavior (e.g. if a value falls outside the acceptable range). Only edit the data file if you are confident you can do so correctly.

</box>

### Exiting B2B4U

To exit B2B4U, use the [`exit` command](#exiting-the-program-exit).

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q: How do I transfer my data to another computer?**

Install B2B4U on the new computer, then replace the empty data file it creates with the data file from your previous B2B4U home folder.

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. **Multiple screens:** If you move B2B4U to a secondary screen and later switch back to using only the primary screen, the GUI may open off-screen.

   *Fix:* Delete the `preferences.json` file created by the app before relaunching.

2. **Minimized Help Window:** If the Help Window is minimized and you run `help` again (via command, the Help menu, or `F1`), the original window remains minimized and no new window appears.

   *Fix:* Manually restore the minimized Help Window.

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                   | Format         | Parameters                                                                                                                    | Examples                                                                                           |
|--------------------------|----------------|-------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| **Help**                 | `help`         | `[COMMAND]`                                                                                                                   | `help add`                                                                                         |
| **Add contact**          | `add`          | `n/NAME (p/PHONE \| e/EMAIL) [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…`                                                        | `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Edit contact**         | `edit`         | `INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…`                                                 | `edit 2 n/James Lee e/jameslee@example.com`                                                        |
| **Delete contact**       | `delete`       | `INDEX`                                                                                                                       | `delete 3`                                                                                         |
| **Clear all contacts**   | `clear`        |                                                                                                                               |                                                                                                    |
| **Add note**             | `note`         | `INDEX NOTE [on/TIME]`                                                                                                        | `note 1 To meet in February on/15 Apr`                                                             |
| **Edit note**            | `note`         | `INDEX el/NOTE_INDEX NEW_NOTE [on/TIME]`                                                                                      | `note 1 el/1 Updated note text.`                                                                   |
| **Remove specific note** | `note`         | `INDEX cl/NOTE_INDEX`                                                                                                         | `note 1 cl/2`                                                                                      |
| **Remove first N notes** | `note`         | `INDEX co/LINES_TO_REMOVE`                                                                                                    | `note 1 co/2`                                                                                      |
| **Clear all notes**      | `note`         | `INDEX ca/`                                                                                                                   | `note 1 ca/`                                                                                       |
| **List contacts**        | `list`         |                                                                                                                               |                                                                                                    |
| **Find contacts**        | `find`         | `[KEYWORD]… [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…`                                            | `find n/James t/friends`                                                                           |
| **Find by association**  | `find`         | `@INDEX`                                                                                                                      | `find @1`                                                                                          |
| **Clear filters**        | `find`         |                                                                                                                               |                                                                                                    |
| **Sort contacts**        | `sort`         | `[n/asc \| desc] [p/asc \| desc] [e/asc \| desc] [a/asc \| desc] [lu/asc \| desc] [lc/asc \| desc] [t/TAG_NAME:asc \| desc]…` | `sort n/asc t/friends:desc`                                                                        |
| **Reset sort order**     | `sort`         |                                                                                                                               |                                                                                                    |
| **Undo**                 | `undo`         |                                                                                                                               |                                                                                                    |
| **Redo**                 | `redo`         |                                                                                                                               |                                                                                                    |
| **View contact**         | `view`         | `INDEX`                                                                                                                       | `view 1`                                                                                           |
| **Close detail panel**   | `close view`   |                                                                                                                               |                                                                                                    |
| **Open file**            | `file open/`   | `FILE_NAME`                                                                                                                   | `file open/newContactList`                                                                         |
| **Delete file**          | `file delete/` | `FILE_NAME`                                                                                                                   | `file delete/OldContactList`                                                                       |
| **View available files** | `view files`   |                                                                                                                               |                                                                                                    |
| **Change theme**         | `theme`        | `THEME_NAME`                                                                                                                  | `theme sakura`                                                                                     |

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Appendix: Command Details

This appendix serves as a comprehensive reference for all commands available in B2B4U.
Each command is explained in detail with its format, parameters, and examples of usage.

<box type="info" seamless>
<b>Note:</b> The commands are intended to be viewed from the feature list above, where you can click on each command to jump to its detailed explanation below.
</box>

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message]({{ baseUrl }}/images/helpMessage.png)

Format: `help [COMMAND]`

* Without arguments, opens the help window with a link to the User Guide.
* With a `COMMAND` argument (e.g. `help add`, `help note`), opens the help window with usage details and a direct link to the relevant section of the User Guide.

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Adding a contact: `add`

Adds a contact to the contact list.

Format: `add n/NAME (p/PHONE | e/EMAIL) [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A contact can have any number of tags (including 0). You must include **at least one** of `p/PHONE` or `e/EMAIL` (the parentheses mean “one or both”); you may supply both.
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
* `add n/Alex Tan p/91234567`
* `add n/Jane Smith e/jane@example.com lc/22/Feb/2026`

![add contact]({{ baseUrl }}/images/addContact.png)

### Duplicate contacts

A contact is considered a **duplicate** of an existing contact if all of the following criteria hold:

- Both contacts have the exact same name
- Both contacts have the exact same phone number
- Both contacts have the exact same email address

If you try to add a duplicate contact, B2B4U will reject the command with the message: "This contact already exists in the address book".

<div style="page-break-after: always;"></div>

### Similar contacts
After a successful `add` command, the contact list will be reset to display every contact in the default sort order, then if there are similar contacts in the list, the contact list will be displayed to display the similar contacts.

Two contacts are similar if:

- Both contacts share the same name
- Both contacts share the same phone number
- Both contacts share the same email address

<box type="info" seamless>

**Note:** Similar contact detection uses **exact matching** only. Names that appear similar to a human (e.g. "John Doe" and "John Doe Sr.") will not be flagged as similar unless they match exactly. The same applies to phone numbers and emails.
</box>

![add contact]({{ baseUrl }}/images/addContact-similar.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Editing a contact: `edit`

Edits an existing contact in the contact list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

* Edits the contact at the specified `INDEX`. The index refers to the index number shown in the displayed contact list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the contact will be removed i.e adding of tags is not cumulative.
* You can remove all the contact's tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd contact to be `Betsy Crower` and clears all existing tags.
*  `edit 3 lc/today` Updates the last contacted date of the 3rd contact to today.

![edit contact]({{ baseUrl }}/images/editContact.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Deleting a contact: `delete`

Deletes the specified contact from the contact list.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

![delete contact]({{ baseUrl }}/images/deleteContact.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Clearing all contacts: `clear`

Clears all contacts from the contact list.

Format: `clear`

![clear]({{ baseUrl }}/images/clear.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Listing all contacts: `list`

Shows a list of all contacts in the contact list and resets contact order by oldest contact first.

Format: `list`

![list contacts]({{ baseUrl }}/images/listContacts.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

### Finding contacts: `find`

Finds contacts whose fields match the specified search criteria.

Format: `find [KEYWORD]… [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…`
or: `find @INDEX` to find contacts associated with the contact at INDEX

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* Unprefixed `KEYWORD`s search across all fields (name, phone, email, address, notes, tags) using partial matching. Each keyword must appear somewhere in the contact.
    * Note: As months are abbreviated in reminders, a search with the full name of a month exceeding 3 letters in length will not successfully filter for reminders with that month.
    * Example: Given contact "Alex Yeoh" with note "to meet _on_ Jun 19, 2026"
        * `find Jun` will display contact "Alex Yeoh"
        * `find June` **will not** display contact "Alex Yeoh"

<div style="page-break-after: always;"></div>

* Prefixed searches (`n/`, `p/`, `e/`, `a/`, `lc/`) filter by the specified field using partial matching.
* `t/TAG` filters by tag using **exact** matching (e.g. `t/friend` will not match a tag named `friends`).
* All search conditions are combined with **AND** logic — only contacts satisfying **every** condition are returned.
* At least one search condition must be provided.
* `find @INDEX` performs a **bidirectional** cross-reference lookup on the contact at the given index:
    * It finds all contacts that are **referenced by** the target contact's notes (via `@INDEX` references).
    * It also finds all contacts whose notes **reference** the target contact.
    * This allows you to see all associations regardless of which direction the reference was made.

Examples:
* `find John` returns contacts containing `john` in any field.
* `find n/Alex` returns contacts with `Alex` in their name.
* `find p/94` returns contacts with `94` in their phone number.
* `find a/street t/friends` returns contacts that have `street` in their address **and** the exact tag `friends`.
* `find @1` shows all contacts associated with the 1st contact — both contacts referenced in their notes and contacts that reference them.
* If Contact 1's notes contain `@2` (a reference to Contact 2), both `find @1` and `find @2` will show the association between them.

![find contacts]({{ baseUrl }}/images/findContacts.png)

### Clearing filters

To remove the current filters and display every contact, use the `find` command without any additional keywords.

<box type="info" seamless>

**Note:** Changing or resetting the `find` filters has no impact on the current sort order. To reset both the applied filters and sort order at the same time, use the [`list` command](#listing-all-contacts-list) instead.

</box>

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

### Sorting contacts: `sort`

Sorts the currently displayed contacts by the specified field(s).

Format: `sort [n/asc | desc] [p/asc | desc] [e/asc | desc] [a/asc | desc] [lu/asc | desc] [lc/asc | desc] [t/TAG_NAME:asc | desc]…`

* Sorts by the fields indicated by each prefix, in the order the prefixes are given.
* `n/` — sort by name, `p/` — sort by phone, `e/` — sort by email, `a/` — sort by address, `lu/` — sort by last updated, `lc/` — sort by last contacted.
* `t/TAG_NAME` — contacts with the ranked tag `TAG_NAME` are displayed at the top.
* At least one sort criterion must be provided.
* Sort criterions from separate `sort` commands can stack.
* Older sort criterion given priority in sorting.

Examples:
* `sort n/asc` sorts all contacts alphabetically by name.
* `sort lu/desc` sorts contacts by when they were last updated.
* `sort n/asc t/vip:desc` sorts contacts alphabetically by name, with contacts tagged `vip` shown first. Contacts that are tagged `vip` will be sorted in decreasing `vip` rank.

![sort lu/desc t/friends:asc n/asc]({{ baseUrl }}/images/sort.png)

<div style="page-break-after: always;"></div>

### Sort order by field

| Field          | Prefix        | Ascending: `asc`                                                                                                                                                                                                                     | Descending: `desc`                                                                                                                                                                                 |
|----------------|---------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name           | `n/`          | Sort contacts by name, in alphabetical order.                                                                                                                                                                                        | Sort contacts by name, in reverse alphabetical order.                                                                                                                                              |
| Phone number   | `p/`          | Sort contacts by phone number, in alphabetical order. <box type="info" seamless>**Note: ** Rather than sorting by the number value, the phone number is sorted similarly to a word. I.e. "12" will be sorted to be before "2".</box> | Sort contacts by phone number, in reverse alphabetical order.                                                                                                                                      |
| Email          | `e/`          | Sort contacts by email, in alphabetical order.                                                                                                                                                                                       | Sort contacts by email, in reverse alphabetical order.                                                                                                                                             |
| Address        | `a/`          | Sort contacts by address, in alphabetical order.                                                                                                                                                                                     | Sort contacts by address, in reverse alphabetical order.                                                                                                                                           |
| Last updated   | `lu/`         | Sort contacts by the least recently updated first.                                                                                                                                                                                   | Sort contacts by the most recently updated first.                                                                                                                                                  |
| Last contacted | `lc/`         | Sort contacts by the least recently contacted first.                                                                                                                                                                                 | Sort contacts by the most recently contacted first.                                                                                                                                                |
| Tag            | `t/TAG_NAME/` | Sort contacts by tag `TAG_NAME` first. <br> Contacts that have a numerical `TAG_NAME` rank will be sorted in decreasing `TAG_NAME` rank(highest rank first, followed by non-numerical ranks).                                        | Sort contacts by tag `TAG_NAME` first. <br> Contacts that have a numerical `TAG_NAME` rank will be sorted in increasing `TAG_NAME` rank(non-numerical ranks first, followed by lowest rank first). |

### Resetting sort order

To reset the current sort order, use the `sort` command without any additional keywords.

<box type="info" seamless>

**Note:** Changing or resetting the sort order has no impact on the current `find` filters. To reset both the applied filters and sort order at the same time, use the [`list` command](#listing-all-contacts-list) instead.

</box>

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Managing notes/reminders for a contact: `note`

Manages notes and reminders for an existing contact in the contact list.

### Add a note

Format: `note INDEX NOTE [on/TIME]`

* Appends `NOTE` to the contact at the specified `INDEX`. The index **must be a positive integer** 1, 2, 3, …​
* New notes are stacked underneath existing ones.
* `TIME` can accept most conventional date/time formats and may omit the year. If unable to parse as a date, it will be saved as a plain string.
* Filling the `on/TIME` field turns the note into a [reminder](#reminders). The system will warn of reminders due within 1 week.
* Notes support **contact references** using the `@INDEX` syntax. When you include `@INDEX` in a note, it creates a link to the contact at that index. The reference is displayed as the contact's name in **bold and underlined** text.
* If a referenced contact's name changes, the displayed name updates automatically.
* If a referenced contact is deleted, the reference is replaced with the contact's name as plain text.
* If a contact reference is corrupted (e.g. changed to an invalid value in the data file), it will be displayed as `@Unknown` in the note.
* You cannot add a note that is **identical** to one already on that contact (same text **and** the same reminder time, if any). If you try, the command fails and no change is made.

Examples:
* `note 1 Likes to swim.`
* `note 2 Follow up call on/15 Apr`
* `note 1 Worked with @2` — creates a reference to the 2nd contact in the note.

![add note]({{ baseUrl }}/images/addNote.png)

<div style="page-break-after: always;"></div>

### Edit a specific note

Format: `note INDEX el/NOTE_INDEX NEW_NOTE [on/TIME]`

* Replaces the note at position `NOTE_INDEX` of the contact at the specified `INDEX` with `NEW_NOTE`.
* `NOTE_INDEX` refers to the position of the note as displayed (starting from 1).
* Optionally include `on/TIME` to set or update the reminder for the edited note.
* After editing, the note must not match **another** note on the same contact (same text **and** the same reminder time, if any). Otherwise the command fails and the note is left unchanged.

Examples:
* `note 1 el/1 Updated note text.` replaces the 1st note of the 1st contact.
* `note 2 el/3 Follow up call on/15 Apr` replaces the 3rd note for the 2nd contact and sets a reminder.

![edit note]({{ baseUrl }}/images/editNote.png)

<div style="page-break-after: always;"></div>

### Remove a specific note

Format: `note INDEX cl/NOTE_INDEX`

* Removes the note at position `NOTE_INDEX` from the contact at the specified `INDEX`.
* `NOTE_INDEX` refers to the position of the note as displayed (starting from 1).

Example:
* `note 1 cl/2` removes the 2nd note from the 1st contact.

![remove specific note]({{ baseUrl }}/images/removeSpecificNote.png)

<div style="page-break-after: always;"></div>

### Remove the first N notes

Format: `note INDEX co/LINES_TO_REMOVE`

* Removes the first `LINES_TO_REMOVE` notes from the contact at the specified `INDEX`.
* `LINES_TO_REMOVE` must be a non-negative integer.
* If `LINES_TO_REMOVE` exceeds the number of existing notes, all notes are removed.

Examples:
* `note 1 co/1` removes the first note from the 1st contact.
* `note 2 co/3` removes the first 3 notes from the 2nd contact.

![remove notes]({{ baseUrl }}/images/removeNotes.png)

<div style="page-break-after: always;"></div>

### Clear all notes

Format: `note INDEX ca/`

* Removes all notes from the contact at the specified `INDEX`.

Example:
* `note 1 ca/`

![remove all notes]({{ baseUrl }}/images/removeAllNotes.png)

<div style="page-break-after: always;"></div>

### Reminders

By including a `/on` prefix and a time afterwards in a `note`, users can create reminders attached to a contact, which is useful to scheduling meetings and events relating to those contacts. <br>
Contacts with a reminder will gain a special `Reminder` tag and automatically be placed towards the top of the contact list. <br>
The input format for the time is [flexible](#flexible-time-input).

![Reminder]({{ baseUrl }}/images/notes-reminder.png)

<div style="page-break-after: always;"></div>

Users will be notified that the reminder of a contact is due within 7 days in the following ways:
- The `Reminder` tag of the contact will turn reddish
- The contact will be placed at the very top of the contact list, above other contacts without a due reminder
- A reminder window will pop-up during startup

![Due Reminder]({{ baseUrl }}/images/notes-dueReminder.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Undoing a command: `undo`

Reverts the last executed command that modified data.

Format: `undo`

* Only the following commands can be undone:
    * Commands that modify contact list data: `add`, `edit`, `delete`, `note`, `clear`
    * Commands that change the filter/sort patterns: `list`, `find`, `sort`
    * Commands that modify application settings: `file`, `theme`
* Commands that do not fall in the above categories (`help`, `view`, `close view`, `undo`, `redo`, `exit`) are ignored by undo.
* Displays the feedback of the undone command after execution.

Examples:
* `delete 1` followed by `undo` restores the deleted contact.
* `edit 1 n/New Name` followed by `undo` reverts the name change.

![undo command]({{ baseUrl }}/images/undoCommand.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Redoing a command: `redo`

Reverses the effect of an [`undo` command](#undoing-a-command-undo), effectively re-applying the previously undone action.

Format: `redo`

* Only applicable after an `undo` command has been executed.
* Restores the application state to the state prior to the previous `undo` command, as if said `undo` command was never executed at all.
* Displays the feedback of the redone command after successful execution.

Examples:
* `delete 1` then `undo` then `redo` re-deletes the 1st contact.
* `edit 1 n/New Name` then `undo` then `redo` re-applies the name change.

![redo command]({{ baseUrl }}/images/redoCommand.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Viewing a specific contact: `view`

Displays a specific contact's full details in a side panel.

Format: `view INDEX`

* Displays the contact at the specified `INDEX` in a separate panel.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* If the viewed contact is subsequently edited (e.g. via `edit` or `note`), the detail panel updates automatically to reflect the changes.

Example:
* `view 1` displays the details of the 1st contact.

![view contact]({{ baseUrl }}/images/viewContact.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

### Viewing available files: `view files`

Displays all the B2B4U contact list files in the data subfolder in a side panel, each with the number of contacts they contain.

Format: `view files`

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Closing the view panel: `close view`

Closes the currently open contact detail or file list panel and returns to the main list view.

Format: `close view`

* Does not require any index or additional parameters.
* If no contact or file list panel is currently open, the command executes without error and does nothing.

Example:
* `close view`

![close view]({{ baseUrl }}/images/closeView.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Open file: `file open/`

Changes the contact list file being accessed to a different one in the data subfolder.

Format: `file open/FILE_NAME`

* Accesses the file named `FILE_NAME`.json in the data subfolder.
* If `FILE_NAME`.json does not already exist, it will be created with an empty contact list.
* `FILE_NAME` can only contain **Alphanumeric characters and/or the underscore '_' character**.

Examples:
* If new_file.json does not exist, `file open/new_file` will create new_file.json and allow immediate access to it.

![Open file]({{ baseUrl }}/images/file-open.png)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Deleting a file: `file delete/`

Deletes the specified file from the data subfolder.

Format: `file delete/FILE_NAME`

* Deletes the file named `FILE_NAME`.json in the data subfolder.
* `FILE_NAME`.json **must exist in the data subfolder**.
* `FILE_NAME`.json **cannot be the currently accessed file**.
* If `FILE_NAME`.json is not empty, an alert window will pop up and require confirmation of deletion.

Examples:
* If empty.json does not contain any contacts, `file delete/empty` will delete said file without issue.

![Delete empty file]({{ baseUrl }}/images/deleteEmptyFile.png)

<div style="page-break-after: always;"></div>

* If oldContactList.json contains at least one contact, `file delete/oldContactList` will trigger an alert window to appear and prompt for confirmation.

![Delete file]({{ baseUrl }}/images/deleteFile.png)

[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

### Set theme: `theme`

Changes the theme to one of 7 supported themes.

Format: `theme THEME_NAME`

| Theme        | Description                                                               | Command        | Image                                                    |
|--------------|---------------------------------------------------------------------------|----------------|----------------------------------------------------------|
| Dark Mode    | Perfect for late-night work.                                              | `theme dark`   | ![Dark Mode]({{ baseUrl }}/images/theme-dark.png)        |
| Light Mode   | For when you're in well-lit conditions.                                   | `theme light`  | ![Light Mode]({{ baseUrl }}/images/theme-light.png)      |
| Reading Mode | To lessen the strain on the eyes.                                         | `theme book`   | ![Reading Mode]({{ baseUrl }}/images/theme-book.png)     |
| Sakura Theme | For fans of the kawaii and pink aesthetic.                                | `theme sakura` | ![Sakura Theme]({{ baseUrl }}/images/theme-sakura.png)   |
| Grass Theme  | An alternate colour scheme that reduces eye strain.                       | `theme grass`  | ![Grass Theme]({{ baseUrl }}/images/theme-grass.png)     |
| Techcore     | For those who want to appear like they're at the forefront of technology. | `theme tech`   | ![Techcore Theme]({{ baseUrl }}/images/theme-tech.png)   |
| Jirai Kei    | For fans of the Jirai Kei aesthetic.                                      | `theme jirai`  | ![Jirai Kei Theme]({{ baseUrl }}/images/theme-jirai.png) |


[(Back to top)](#b2b4u-user-guide)

--------------------------------------------------------------------------------------------------------------------

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[(Back to top)](#b2b4u-user-guide)
