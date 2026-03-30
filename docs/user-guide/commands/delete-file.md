# Deleting a file: `delete file/`

Deletes the specified file from the data subfolder.

Format: `delete file/FILE_NAME`

* Deletes the file named `FILE_NAME`.json in the data subfolder.
* `FILE_NAME`.json **must exist in the data subfolder**.
* `FILE_NAME`.json **cannot be the currently accessed file**.
* If `FILE_NAME`.json is not empty, an alert window will pop up and require confirmation of deletion.

Examples:
* If empty.json does not contain any contacts, `delete file/empty` will delete said file without issue.
* If oldContactList.json contains at least one contact, `delete file/oldContactList` will trigger an alert window to appear and prompt for confirmation.
