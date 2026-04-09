---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# B2B4U User Guide

Business to Business for You (B2B4U) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, B2B4U can get your contact management tasks done faster than traditional GUI apps.

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
    - [Command Format](#command-format)
    - [Adding contacts](#adding-contacts)
    - [Editing contacts](#editing-contacts)
    - [Deleting contacts](#deleting-contacts)
    - [Adding notes to contacts](#adding-notes-to-contacts)
    - [Filtering and sorting the context list](#filtering-and-sorting-the-context-list)
    - [Undo and redo](#undo-and-redo)
    - [Setting the theme](#setting-the-theme)
    - [Maintaining separate data files](#maintaining-separate-data-files)
    - [Exiting B2B4U](#exiting-b2b4u)
    - [Saving data](#saving-data)
    - [Editing the data file directly](#editing-the-data-file-directly)
3. [FAQ](#faq)
4. [Known Issues](#known-issues)
5. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2526S2-CS2103T-T08-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your B2B4U application.

1. Double-click the B2B4U.jar file.<br>
   _Alternatively,_ open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar B2B4U.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui]({{ baseUrl }}/images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the contact list.

    * `view 1` : Opens the detail panel for the 1st contact.

    * `close view` : Closes the contact detail panel.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.

1. Refer to [Features]({{ baseUrl }}/UserGuide.html#features) for a full list of commands, or each command's subpages for additional details.

--------------------------------------------------------------------------------------------------------------------

## Features

### Command Format

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `SCREAMING_SNAKE_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order, however the order may affect results in certain commands (i.e. `sort`).<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will fail to execute.<br>
  e.g. if the command specifies `help 123`, it will fail.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

* If a command is not recognised, an error message will be displayed.<br>
  ![unknown command]({{ baseUrl }}/images/unknownCommand.png)
  </box>


### Adding contacts

To add a new contact, use the [`add` command]({{ baseUrl }}/user-guide/add-contact.html).

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

* At least one of `p/PHONE_NUMBER` or `e/EMAIL` must be provided.
* Names are standardised to Title Case.
* After adding, if there are similar contacts, the list will filter to display them.

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Alex Tan p/91234567`

### Editing contacts

To edit an existing contact, use the [`edit` command]({{ baseUrl }}/user-guide/edit-contact.html).

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

* At least one optional field must be provided.
* When editing tags, the existing tags are replaced entirely (not cumulative).

Examples:
* `edit 1 p/91234567 e/johndoe@example.com`
* `edit 2 n/Betsy Crower t/`

### Deleting contacts

- To delete a specific displayed contact, use the [`delete INDEX` command]({{ baseUrl }}/user-guide/delete-contact.html).
- To delete all contacts at once, use the [`clear` command]({{ baseUrl }}/user-guide/clear-contacts.html).

### Adding notes to contacts

To manage notes and reminders for a contact, use the [`note` command]({{ baseUrl }}/user-guide/notes.html).

* [**Add a note:** `note INDEX NOTE [on/TIME]`]({{ baseUrl }}/user-guide/notes.html#add-a-note) — appends a note to the contact. Including `on/TIME` turns it into a [reminder]({{ baseUrl }}/user-guide/notes.html#reminders).
* [**Edit a note:** `note INDEX el/NOTE_INDEX NEW_NOTE [on/TIME]`]({{ baseUrl }}/user-guide/notes.html#edit-a-specific-note) — replaces a specific note.
* [**Remove a specific note:** `note INDEX cl/NOTE_INDEX`]({{ baseUrl }}/user-guide/notes.html#remove-a-specific-note) — removes the note at that position.
* [**Remove first N notes:** `note INDEX c/LINES_TO_REMOVE`]({{ baseUrl }}/user-guide/notes.html#remove-the-first-n-notes) — removes the first N notes, where N = `LINES_TO_REMOVE`.
* [**Clear all notes:** `note INDEX ca/`]({{ baseUrl }}/user-guide/notes.html#clear-all-notes) — removes all notes from the contact.

Notes support **contact references** using the `@INDEX` syntax, which creates a bidirectional association between the two contacts. This means both contacts will appear when searching for either one using `find @INDEX`.

### Reminders

B2B4U can remind you of upcoming events, as every contact with a reminder in its notes will gain a `Reminder` tag which turns red once the reminder is due in 7 days.
During this period, a window will also pop-up to remind you of the upcoming event every time you start B2B4U.
<!-- TODO: Insert image of reminder window -->

### Filtering and sorting the context list

By default, B2B4U will display every contact within the contact list, ordered to place the contacts with the most urgent reminders and (subsequently) the most recently contacted date.
Of course, B2B4U does also provide commands to filter and sort the contact list to quickly find contacts that fit specific criteria in a sea of other contacts.

- To filter, use the [`find` command]({{ baseUrl }}/user-guide/find-contacts.html) with keywords or field-specific prefixes (e.g. `find n/Alex t/friends`). Use `find @INDEX` to find contacts associated with the contact at that index.
- To remove contact filters, use the [`find` command without any keywords]({{ baseUrl }}/user-guide/find-contacts.html#clearing-filters).
- To sort, use the [`sort` command]({{ baseUrl }}/user-guide/sort-contacts.html) with field prefixes followed by `asc` or `desc` to specify the [sort direction]({{ baseUrl }}/user-guide/sort-contacts.html#sort-order-by-field) (e.g. `sort n/asc` to sort by name ascending, `sort lc/desc` to sort by last contacted descending).
- To reset the sort order, use the [`sort` command without any keywords]({{ baseUrl }}/user-guide/sort-contacts.html#resetting-sort-order).

The effects of the `find` and `sort` commands will be maintained even when the other command is made, and will only be changed another iteration of its own command, with the exception of the following commands which can also change the filter/sort criteria:

- A [`list` command]({{ baseUrl }}/user-guide/list-contacts.html) will display every contact in the default sort order.
- An [`add` command]({{ baseUrl }}/user-guide/add-contact.html#similar-contacts) will reset the sort order, and may filter to display only similar contacts.

### Undo and redo

B2B4U allows you to undo and redo commands to prevent data loss due to mistakes.

- To undo the last command, use the [`undo` command]({{ baseUrl }}/user-guide/undo-command.html).
- To redo the last command, use the [`redo` command]({{ baseUrl }}/user-guide/redo-command.html).

### Setting the theme

B2B4U features a variety of color palettes(referred to as 'themes') to customise your experience.

To change to a different theme, use the [`theme THEME_NAME` command]({{ baseUrl }}/user-guide/set-theme.html).

Available themes:
- [Dark mode: `dark`]({{ baseUrl }}/user-guide/set-theme.html#dark-mode-dark)
- [Light mode: `light`]({{ baseUrl }}/user-guide/set-theme.html#light-mode-light)
- [Reading mode: `book`]({{ baseUrl }}/user-guide/set-theme.html#reading-mode-book)
- [Sakura theme: `sakura`]({{ baseUrl }}/user-guide/set-theme.html#sakura-theme-sakura)
- [Grass theme: `grass`]({{ baseUrl }}/user-guide/set-theme.html#grass-theme-grass)
- [Techcore theme: `tech`]({{ baseUrl }}/user-guide/set-theme.html#techcore-tech)
- [Jirai Kei theme: `jirai`]({{ baseUrl }}/user-guide/set-theme.html#jirai-kei-jirai)

### Maintaining separate data files

B2B4U allows you to maintain multiple separate data files.
This is useful if you want to maintain separate contact lists for different purposes (e.g. work vs personal contacts).
All data files must be placed in the data folder: `[JAR file location]/data/`.

- To view a list of all available data files, use the [`view files` command]({{ baseUrl }}/user-guide/view.html#viewing-available-files-view-files).
- To open a specific data file, use the [`file open/FILE_NAME` command]({{ baseUrl }}/user-guide/file.html#open-file-file-open).
- To delete a specific data file, use the [`file delete/FILE_NAME` command]({{ baseUrl }}/user-guide/file.html#deleting-a-file-file-delete).

### Exiting B2B4U

To exit B2B4U, use the [`exit` command]({{ baseUrl }}/user-guide/exit-program.html).

### Saving data

B2B4U data is saved to the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Editing the data file directly

By default, B2B4U data is saved automatically as JSON files within the `[JAR file location]/data` folder.
Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, B2B4U will discard all data and start with an empty data file at the next run.  Hence, it is recommended to make a backup of the file before editing it.<br>
Furthermore, certain edits can cause the B2B4U application to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

For more information, refer to the [Command summary](#command-summary) at the end of this document,
or the specific pages for [viewing]({{ baseUrl }}/user-guide/view.html) and [managing]({{ baseUrl }}/user-guide/file.html) files.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous B2B4U home folder.

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                            | Format         | Parameters                                                                                                                    | Examples                                                                                           |
|-----------------------------------|----------------|-------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| **Help**                          | `help`         | `[COMMAND]`                                                                                                                   | `help add`                                                                                         |
| **Add contact**                   | `add`          | `n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`                                                 | `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Edit contact**                  | `edit`         | `INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`                                         | `edit 2 n/James Lee e/jameslee@example.com`                                                        |
| **Delete contact**                | `delete`       | `INDEX`                                                                                                                       | `delete 3`                                                                                         |
| **Clear contacts**                | `clear`        |                                                                                                                               |                                                                                                    |
| **Note (add)**                    | `note`         | `INDEX NOTE [on/TIME]`                                                                                                        | `note 1 To meet in February on/15 Apr`                                                             |
| **Note (edit)**                   | `note`         | `INDEX el/NOTE_INDEX NEW_NOTE [on/TIME]`                                                                                      | `note 1 el/1 Updated note text.`                                                                   |
| **Note (remove specific)**        | `note`         | `INDEX cl/NOTE_INDEX`                                                                                                         | `note 1 cl/2`                                                                                      |
| **Note (remove)**                 | `note`         | `INDEX c/LINES_TO_REMOVE`                                                                                                     | `note 1 c/2`                                                                                       |
| **Note (clear)**                  | `note`         | `INDEX ca/`                                                                                                                   | `note 1 ca/`                                                                                       |
| **List contacts**                 | `list`         |                                                                                                                               |                                                                                                    |
| **Find contacts**                 | `find`         | `[KEYWORD]… [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`                                                                | `find n/James t/friends`                                                                           |
| **Find contacts(by association)** | `find`         | `@INDEX`                                                                                                                      | `find @1`                                                                                          |
| **Remove filters**                | `find`         |                                                                                                                               |                                                                                                    |
| **Sort contacts**                 | `sort`         | `[n/asc \| desc] [p/asc \| desc] [e/asc \| desc] [a/asc \| desc] [lu/asc \| desc] [lc/asc \| desc] [t/TAG_NAME:asc \| desc]…` | `sort n/asc t/friends:desc`                                                                        |
| **Sort by last updated**          | `sort`         |                                                                                                                               |                                                                                                    |
| **Undo**                          | `undo`         |                                                                                                                               |                                                                                                    |
| **Redo**                          | `redo`         |                                                                                                                               |                                                                                                    |
| **View contact**                  | `view`         | `INDEX`                                                                                                                       | `view 1`                                                                                           |
| **Close view**                    | `close view`   |                                                                                                                               |                                                                                                    |
| **Open file**                     | `file open/`   | `FILE_NAME`                                                                                                                   | `file open/newContactList`                                                                         |
| **Delete file**                   | `file delete/` | `FILE_NAME`                                                                                                                   | `file delete/OldContactList`                                                                       |
| **View list of available files**  | `view files`   |                                                                                                                               |                                                                                                    |
| **Change theme**                  | `theme`        | `THEME_NAME`                                                                                                                  | `theme sakura`                                                                                     |

--------------------------------------------------------------------------------------------------------------------

# Appendix: Command Details

## Viewing help: `help`

Shows a message explaining how to access the help page.

![help message]({{ baseUrl }}/images/helpMessage.png)

Format: `help [COMMAND]`

* Without arguments, opens the help window with a link to the User Guide.
* With a `COMMAND` argument (e.g. `help add`, `help note`), opens the help window with usage details and a direct link to the relevant section of the User Guide.

--------------------------------------------------------------------------------------------------------------------

## Adding a contact: `add`

Adds a contact to the contact list.

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [lc/LAST_CONTACTED] [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A contact can have any number of tags (including 0). At least one of `p/PHONE_NUMBER` or `e/EMAIL` must be provided.
</box>

* Names are standardised to Title Case (e.g. `john doe` becomes `John Doe`).
* Phone numbers accept digits, spaces, and `+`. Numbers starting with `+` (country code) must be 8–15 digits; otherwise 5–14 digits.
* Tags accept alphanumeric strings in the format `TAG` or `TAG:RANK` (e.g. `friend`, `client:vip`).
* `LAST_CONTACTED` accepts most conventional date/time formats (e.g. `22/02/2026`, `15 Apr`, `today`).

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
* `add n/Alex Tan p/91234567`
* `add n/Jane Smith e/jane@example.com lc/22/02/2026`

![add contact]({{ baseUrl }}/images/addContact.png)

### Similar contacts
After a successful `add` command, the contact list will be reset to display every contact in the default sort order, then if there are similar contacts in the list, the contact list will be displayed to display the similar contacts.

Two contacts are similar if:

- Both contacts share the same name
- Both contacts share the same phone number
- Both contacts share the same email address

![add contact]({{ baseUrl }}/images/addContact-similar.png)

--------------------------------------------------------------------------------------------------------------------

## Editing a contact: `edit`

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

--------------------------------------------------------------------------------------------------------------------

## Deleting a contact: `delete`

Deletes the specified contact from the contact list.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

![delete contact]({{ baseUrl }}/images/deleteContact.png)

--------------------------------------------------------------------------------------------------------------------

## Clearing all contacts: `clear`

Clears all contacts from the contact list.

Format: `clear`

![clear]({{ baseUrl }}/images/clear.png)

--------------------------------------------------------------------------------------------------------------------

## Listing all contacts: `list`

Shows a list of all contacts in the contact list and resets contact order by oldest contact first.

Format: `list`

![list contacts]({{ baseUrl }}/images/listContacts.png)

--------------------------------------------------------------------------------------------------------------------

## Finding contacts: `find`

Finds contacts whose fields match the specified search criteria.

Format: `find [KEYWORD]… [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`
or: `find @INDEX` to find contacts associated with the contact at INDEX

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* Unprefixed `KEYWORD`s search across all fields (name, phone, email, address, notes, tags) using partial matching. Each keyword must appear somewhere in the contact.
    * Note: As months are abbreviated in reminders, a search with the full name of a month exceeding 3 letters in length will not successfully filter for reminders with that month.
    * Example: Given contact "Alex Yeoh" with note "to meet _on_ Jun 19, 2026"
        * `find Jun` will display contact "Alex Yeoh"
        * `find June` **will not** display contact "Alex Yeoh"
* Prefixed searches (`n/`, `p/`, `e/`, `a/`) filter by the specified field using partial matching.
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
<b>Note:</b> Changing or resetting the <code>find</code> filters has no impact on the current sort order. To reset both the applied filters and sort order at the same time, use the <a href="{{ baseUrl }}/user-guide/list-contacts.html"><code>list</code> command</a> instead.
</box>

--------------------------------------------------------------------------------------------------------------------

## Sorting contacts: `sort`

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

### Sort order by field


| Field                 | Ascending: `asc`                                                                                                                                                                                                                                                                                      | Descending: `desc`                                                                                                                                                                                                                                                 |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Name: `n/`            | Sort contacts by name, in alphabetical order <br> ![sort n/asc]({{ baseUrl }}/images/sort-name-asc.png)                                                                                                                                                                                               | Sort contacts by name, in reverse alphabetical order  <br> ![sort n/desc]({{ baseUrl }}/images/sort-name-desc.png)                                                                                                                                                 |
| Phone number: `p/`    | Sort contacts by phone number, in alphabetical order <br> <box type="info" seamless>**Note: ** Rather than sorting by the number value, the phone number is sorted similarly to a word. I.e. "12" will be sorted to be before "2".</box>  <br> ![sort p/asc]({{ baseUrl }}/images/sort-phone-asc.png) | Sort contacts by phone number, in reverse alphabetical order <br> ![sort p/desc]({{ baseUrl }}/images/sort-phone-desc.png)                                                                                                                                         |
| Email: `e/`           | Sort contacts by email, in alphabetical order  <br> ![sort e/asc]({{ baseUrl }}/images/sort-email-asc.png)                                                                                                                                                                                            | Sort contacts by email, in reverse alphabetical order <br> ![sort e/desc]({{ baseUrl }}/images/sort-email-desc.png)                                                                                                                                                |
| Address: `a/`         | Sort contacts by address, in alphabetical order  <br> ![sort a/asc]({{ baseUrl }}/images/sort-address-asc.png)                                                                                                                                                                                        | Sort contacts by address, in reverse alphabetical order <br> ![sort a/desc]({{ baseUrl }}/images/sort-address-desc.png)                                                                                                                                            |
| Last updated: `lu/`   | Sort contacts by the least recently updated first  <br> ![sort lu/asc]({{ baseUrl }}/images/sort-lu-asc.png)                                                                                                                                                                                          | Sort contacts by the most recently updated first <br> ![sort lu/desc]({{ baseUrl }}/images/sort-lu-desc.png)                                                                                                                                                       |
| Last contacted: `lc/` | Sort contacts by the least recently contacted first  <br> ![sort lc/asc]({{ baseUrl }}/images/sort-lc-asc.png)                                                                                                                                                                                        | Sort contacts by the most recently contacted first <br> ![sort lc/desc]({{ baseUrl }}/images/sort-lc-desc.png)                                                                                                                                                     |
| Tag: `t/TAG_NAME/`    | Sort contacts by tag `TAG_NAME` first. Contacts that have a numerical `TAG_NAME` rank will be sorted in decreasing `TAG_NAME` rank(highest rank first, followed by non-numerical ranks). <br> ![sort t/TAG_NAME:asc]({{ baseUrl }}/images/sort-tag-asc.png)                                           | Sort contacts by tag `TAG_NAME` first. Contacts that have a numerical `TAG_NAME` rank will be sorted in increasing `TAG_NAME` rank(non-numerical ranks first, followed by lowest rank first). <br> ![sort t/TAG_NAME:desc]({{ baseUrl }}/images/sort-tag-desc.png) |

### Resetting sort order

To reset the current sort order, use the `sort` command without any additional keywords.

<box type="info" seamless>
<b>Note:</b> Changing or resetting the sort order has no impact on the current <code>find</code> filters. To reset both the applied filters and sort order at the same time, use the <a href="{{ baseUrl }}/user-guide/list-contacts.html"><code>list</code> command</a> instead. 
</box>

--------------------------------------------------------------------------------------------------------------------

## Adding notes/reminders to a contact: `note`

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

Examples:
* `note 1 Likes to swim.`
* `note 2 Follow up call on/15 Apr`
* `note 1 Worked with @2` — creates a reference to the 2nd contact in the note.

![add note]({{ baseUrl }}/images/addNote.png)

### Edit a specific note

Format: `note INDEX el/NOTE_INDEX NEW_NOTE [on/TIME]`

* Replaces the note at position `NOTE_INDEX` of the contact at the specified `INDEX` with `NEW_NOTE`.
* `NOTE_INDEX` refers to the position of the note as displayed (starting from 1).
* Optionally include `on/TIME` to set or update the reminder for the edited note.

Examples:
* `note 1 el/1 Updated note text.` replaces the 1st note of the 1st contact.
* `note 2 el/3 Follow up call on/15 Apr` replaces the 3rd note for the 2nd contact and sets a reminder.

![edit note]({{ baseUrl }}/images/editNote.png)

### Remove a specific note

Format: `note INDEX cl/NOTE_INDEX`

* Removes the note at position `NOTE_INDEX` from the contact at the specified `INDEX`.
* `NOTE_INDEX` refers to the position of the note as displayed (starting from 1).

Example:
* `note 1 cl/2` removes the 2nd note from the 1st contact.

![remove specific note]({{ baseUrl }}/images/removeSpecificNote.png)

### Remove the first N notes

Format: `note INDEX c/LINES_TO_REMOVE`

* Removes the first `LINES_TO_REMOVE` notes from the contact at the specified `INDEX`.
* `LINES_TO_REMOVE` must be a non-negative integer.
* If `LINES_TO_REMOVE` exceeds the number of existing notes, all notes are removed.

Examples:
* `note 1 c/1` removes the first note from the 1st contact.
* `note 2 c/3` removes the first 3 notes from the 2nd contact.

![remove notes]({{ baseUrl }}/images/removeNotes.png)

### Clear all notes

Format: `note INDEX ca/`

* Removes all notes from the contact at the specified `INDEX`.

Example:
* `note 1 ca/`

![remove all notes]({{ baseUrl }}/images/removeAllNotes.png)

### Reminders

By including a `/on` prefix and a time afterwards in a `note`, users can create reminders attached to a contact, which is useful to scheduling meetings and events relating to those contacts. <br>
Contacts with a reminder will gain a special `Reminder` tag and automatically be placed towards the top of the contact list.

![Reminder]({{ baseUrl }}/images/notes-reminder.png)

Users will be notified that the reminder of a contact is due within 7 days in the following ways:
- The `Reminder` tag of the contact will turn reddish
- The contact will be placed at the very top of the contact list, above other contacts without a due reminder
- A reminder window will pop-up during startup

![Due Reminder]({{ baseUrl }}/images/notes-dueReminder.png)

--------------------------------------------------------------------------------------------------------------------

## Undoing a command: `undo`

Reverts the last executed command that modified data.

Format: `undo`

* Only commands that modify data can be undone (e.g. `add`, `edit`, `delete`, `note`, `clear`, `sort`).
* Commands that do not modify data (`help`, `view`, `close view`, `list`, `find`, `undo`, `redo`, `exit`) are ignored by undo.
* Displays the feedback of the undone command.

Examples:
* `delete 1` followed by `undo` restores the deleted contact.
* `edit 1 n/New Name` followed by `undo` reverts the name change.

![undo command]({{ baseUrl }}/images/undoCommand.png)

--------------------------------------------------------------------------------------------------------------------

## Redoing a command: `redo`

Reverses the effect of an `undo` command, effectively re-applying the previously undone action.

Format: `redo`

* Only applicable after an `undo` command has been executed.
* Commands that do not modify data (`help`, `view`, `close view`, `list`, `find`, `undo`, `redo`, `exit`) are ignored by redo.
* Displays the feedback of the redone command.

Examples:
* `delete 1` then `undo` then `redo` re-deletes the 1st contact.
* `edit 1 n/New Name` then `undo` then `redo` re-applies the name change.

![redo command]({{ baseUrl }}/images/redoCommand.png)

--------------------------------------------------------------------------------------------------------------------

## Viewing a specific contact: `view`

Displays a specific contact's full details in a side panel.

Format: `view INDEX`

* Displays the contact at the specified `INDEX` in a separate panel.
* The index refers to the index number shown in the displayed contact list.
* The index **must be a positive integer** 1, 2, 3, …​
* If the viewed contact is subsequently edited (e.g. via `edit` or `note`), the detail panel updates automatically to reflect the changes.

Example:
* `view 1` displays the details of the 1st contact.

![view contact]({{ baseUrl }}/images/viewContact.png)

--------------------------------------------------------------------------------------------------------------------

## Viewing available files: `view files`

Displays all the B2B4U contact list files in the data subfolder in a side panel, each with the number of contacts they contain.

Image to be added

Format: `view files`

--------------------------------------------------------------------------------------------------------------------

## Closing the contact detail panel: `close view`

Closes the currently open contact detail panel and returns to the main list view.

Format: `close view`

* Does not require any index or additional parameters.
* If no contact panel is currently open, the command still executes without error.

Example:
* `close view`

![close view]({{ baseUrl }}/images/closeView.png)

--------------------------------------------------------------------------------------------------------------------

## Open file: `file open/`

Changes the contact list file being accessed to a different one in the data subfolder.

Format: `file open/FILE_NAME`

* Accesses the file named `FILE_NAME`.json in the data subfolder.
* If `FILE_NAME`.json does not already exist, it will be created with an empty contact list.
* `FILE_NAME` can only contain **Alphanumeric characters and/or the underscore '_' character**.

Examples:
* If new_file.json does not exist, `file open/new_file` will create new_file.json and allow immediate access to it.

![Open file]({{ baseUrl }}/images/file-open.png)

## Deleting a file: `file delete/`

Deletes the specified file from the data subfolder.

Format: `file delete/FILE_NAME`

* Deletes the file named `FILE_NAME`.json in the data subfolder.
* `FILE_NAME`.json **must exist in the data subfolder**.
* `FILE_NAME`.json **cannot be the currently accessed file**.
* If `FILE_NAME`.json is not empty, an alert window will pop up and require confirmation of deletion.

Examples:
* If empty.json does not contain any contacts, `file delete/empty` will delete said file without issue.

![Delete empty file]({{ baseUrl }}/images/deleteEmptyFile.png)

* If oldContactList.json contains at least one contact, `file delete/oldContactList` will trigger an alert window to appear and prompt for confirmation.

![Delete file]({{ baseUrl }}/images/deleteFile.png)

--------------------------------------------------------------------------------------------------------------------

## Set theme: `theme`

Changes the theme in user.

Format: `theme THEME_NAME`

## Available themes
### Dark Mode: `dark`
![Dark Mode]({{ baseUrl }}/images/theme-dark.png)

Command: `theme dark` <br>
Perfect for late-night work.
### Light Mode: `light`
![Light Mode]({{ baseUrl }}/images/theme-light.png)

Command: `theme light` <br>
For when you're in well-lit conditions.
### Reading Mode: `book`
![Reading Mode]({{ baseUrl }}/images/theme-book.png)

Command: `theme book` <br>
To lessen the strain on the eyes.
### Sakura Theme: `sakura`
![Sakura Theme]({{ baseUrl }}/images/theme-sakura.png)

Command: `theme sakura` <br>
For fans of the kawaii and pink aesthetic.
### Grass Theme: `grass`
![Grass Theme]({{ baseUrl }}/images/theme-grass.png)

Command: `theme grass` <br>
An alternate colour scheme that reduces eye strain.
### Techcore: `tech`
![Techcore Theme]({{ baseUrl }}/images/theme-tech.png)

Command: `theme tech` <br>
For those who want to appear like they're at the forefront of technology
### Jirai Kei: `jirai`
![Jirai Kei Theme]({{ baseUrl }}/images/theme-jirai.png)

Command: `theme jirai` <br>
For fans of the Jirai Kei aesthetic.

--------------------------------------------------------------------------------------------------------------------

## Exiting the program: `exit`

Exits the program.

Format: `exit`
