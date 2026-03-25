package seedu.address.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Panel containing the list of B2B4U.
 */
public class FileListPanel extends UiPart<Region> {
    private static final String FXML = "FileListPanel.fxml";
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();

    private final WatchService watchService;
    private final ObservableList<File> fileList = FXCollections.observableArrayList();

    @FXML
    private ListView<File> fileListView;

    /**
     * Creates a {@code FileListPanel} with the given directory.
     */
    public FileListPanel(Path directory) throws IOException {
        super(FXML);
        File[] existingFiles = directory.toFile().listFiles();
        if (existingFiles != null) {
            fileList.addAll(existingFiles);
        }

        watchService = FileSystems.getDefault().newWatchService();
        directory.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        EXECUTOR_SERVICE.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    WatchKey key = watchService.take();

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();

                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            continue;
                        }

                        Path changed = directory.resolve((Path) event.context());
                        File changedFile = changed.toFile();

                        Platform.runLater(() -> {
                            if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                                fileList.add(changedFile);
                            } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                                fileList.removeIf(f -> f.equals(changedFile));
                            } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                                int index = fileList.indexOf(changedFile);
                                if (index >= 0) {
                                    fileList.set(index, changedFile);
                                }
                            }
                        });
                    }

                    // directory no longer accessible
                    if (!key.reset()) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        FilteredList<File> filteredFiles = new FilteredList<>(fileList, this::isValidFile);
        SortedList<File> sortedFiles = new SortedList<>(filteredFiles,
                Comparator.comparing(File::lastModified, Comparator.reverseOrder()));
        fileListView.setItems(sortedFiles);
        fileListView.setCellFactory(listView -> new FileListPanel.FileListViewCell());

        // Refresh all visible cells when any file changes
        fileList.addListener((ListChangeListener<File>) change -> {
            fileListView.refresh();
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code File} using a {@code FileCard}.
     */
    class FileListViewCell extends ListCell<File> {
        @Override
        protected void updateItem(File file, boolean empty) {
            super.updateItem(file, empty);

            if (empty || file == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FileCard(file).getRoot());
            }
        }
    }

    /**
     * Checks if a given file is a valid B2B4U .json file.
     */
    public boolean isValidFile(File file) {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(Path.of(file.getPath()));
        try {
            Optional<ReadOnlyAddressBook> addressBookOptional = addressBookStorage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                return false;
            }
            return true;
        } catch (DataLoadingException e) {
            return false;
        }
    }
}
