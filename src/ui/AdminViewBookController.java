package ui;

import application.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
/**
 * We must put it in the same package as the BookOverview.fxml, otherwise the
 * SceneBuilder won't find it.
 *
 * @author rXing
 *
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
//import model.Book;
import business.Book;

public class AdminViewBookController {
	@FXML
	private TableView<Book> bookTable;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> copyColumn;

	@FXML
	private Label isbn;
	@FXML
	private Label authors;
	@FXML
	private Label checkoutPeriod;
	@FXML
	private Label availability;

	// Reference to the main application.
	private Main mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public AdminViewBookController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
//		titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
//		copyColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		preJava8();
		// Clear person details.
		showBookDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		bookTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBookDetails(newValue));

	}

	private void preJava8() {
		copyColumn.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				return new ReadOnlyObjectWrapper(param.getValue().getTitle());
			}
		});

		titleColumn.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
				return new ReadOnlyObjectWrapper(param.getValue().getIsbn());
			}
		});
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 *
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		bookTable.setItems(mainApp.getBookData());
	}

	private void showBookDetails(Book book) {
		if (book != null) {
			// Fill the labels with info from the person object.
			isbn.setText(book.getIsbn());
			authors.setText(book.getAuthors1());
			checkoutPeriod.setText(Integer.toString(book.getMaxCheckoutLength()));	
			
			if(book.isAvailable()) {
			availability.setText("Available");}
			else {availability.setText("Not Available");}
			// TODO: We need a way to convert the birthday into a String!
		} else {
			// Book is null, remove all the text.
			isbn.setText("");
			authors.setText("");
			checkoutPeriod.setText("");
			availability.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteBook() {
		int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			bookTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Book Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewBook() {
		Book tempBook = new Book();
		boolean okClicked = mainApp.AddBookDialog(tempBook);
		if (okClicked) {
			mainApp.getBookData().add(tempBook);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	
/*	private void handleEditBook() {
		Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
		if (selectedBook != null) {
			boolean okClicked = mainApp.EditBookDialog(selectedBook);
			if (okClicked) {
				showBookDetails(selectedBook);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Book Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}*/
}
