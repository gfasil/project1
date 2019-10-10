package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

import application.Main;
import business.*;
import dataaccess.DataAccessFacade;
//import model.Book;
//import util.DateUtil;

public class AddBookController {

	@FXML
	private TextField isbn;
	@FXML
	private TextField bookTitle;
	@FXML
	private TextField authors;
	@FXML
	private TextField checkoutPeriod;
	@FXML
	private Button addButton;
	
	private Main mainApp;
	private Stage dialogStage;
	private Book book=new Book();
	private boolean okClicked = false;
	private DataAccessFacade dataaccess;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 *
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the book to be edited in the dialog.
	 *
	 * @param book
	 */
	public void setBook(Book book) {
		this.book = book;

		isbn.setText(book.getIsbn());
		bookTitle.setText(book.getTitle());
		authors.setText("" +Arrays.asList(book.getAuthors()));
		checkoutPeriod.setText(Integer.toString(book.getMaxCheckoutLength()));	
		
		book.addCopy();
		}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 *
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	public Main getMainApp() {
		return mainApp;
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			book.setIsbn(isbn.getText());
			book.setTitle(bookTitle.getText());
			//book.setAuthors(authors.getText());
			book.setMaxCheckoutLength((Integer.parseInt(checkoutPeriod.getText())));
			
			okClicked = true;
			dataaccess=	new DataAccessFacade();
			dataaccess.saveNewBook(this.book);
			book.addCopy();
			
			System.out.println(book);
			
			
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleNewAuthor() {
		
		/*boolean addClicked =*/ mainApp.AddAuthorDialog(book);
		//if (addClicked) {
		//	mainApp.getAuthorData();
		//}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (isbn.getText() == null || isbn.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if (bookTitle.getText() == null || bookTitle.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
//		if (authors.getText() == null || authors.getText().length() == 0) {
//			errorMessage += "No valid street!\n";
//		}

		if (checkoutPeriod.getText() == null || checkoutPeriod.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		
		}



		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
