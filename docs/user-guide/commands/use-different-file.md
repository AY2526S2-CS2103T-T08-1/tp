# Use a different file: `set file/`

Changes the contact list file being accessed to a different one in the data subfolder.

Format: `set file/FILE_NAME`

* Accesses the file named `FILE_NAME`.json in the data subfolder.
* If `FILE_NAME`.json does not already exist, it will be created with an empty contact list.
* `FILE_NAME` can only contain **Alphanumeric characters and/or the underscore '_' character**.

Examples:
* If new_file.json does not exist, `set file/new_file` will create new_file.json and allow immediate access to it.
