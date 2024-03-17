package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class MainController {
	
	@FXML
	private Button loginButton;
	@FXML
	private Button loginCancel;
	@FXML
	private Label errorLabel;
	@FXML
	private TextField loginUser;
	@FXML
	private PasswordField loginPass;
	
	@FXML
	private Button swapPage;	
	@FXML
	private Label loginType;
	@FXML
	private boolean studentPage = true; //to determine whether a student or teacher is currently using the page
	
	
	
	public void studentPageSwap(ActionEvent e) {
		if(studentPage) {
			loginType.setText("Teacher Login Page");
			swapPage.setText("Go to Student Page");
			
			
			studentPage = false;
		} else {
			loginType.setText("Student Login Page");
			swapPage.setText("Go to Teacher Page");
			
			studentPage = true;
		}
	}
	
	public void loginButtonOnAction(ActionEvent e) {
		if (loginUser.getText().isBlank() == false && loginPass.getText().isBlank() == false) {
			//errorLabel.setText("Error");
			validateLogin();
			
		} else {
				errorLabel.setText("Please enter username and password");
			}
	}
	
	
	public void loginCancelOnAction(ActionEvent e) {
		Stage stage = (Stage) loginCancel.getScene().getWindow();
		stage.close();
	}
	
	
	public void validateLogin()
	{
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '" + loginUser.getText() + "' AND password = '"+ loginPass.getText() + "'";
		
		try {
			
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				if (queryResult.getInt(1) == 1) {
					errorLabel.setText("Welcome!");
				}
				
				else {
					errorLabel.setText("Invalid Login. Please Try Again");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
