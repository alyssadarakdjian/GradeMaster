package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogController {
	
	@FXML
	private Button signUp;
	@FXML
	private Button studentLogin;
	@FXML
	private Button teacherLogin;
	@FXML
	private TextField userName;
	@FXML
	private TextField pass;
	@FXML
	private TextField repassword;
	@FXML
	private Label pageName;
	@FXML
	private Button close;
	@FXML
	private Button changePage;
	
	private boolean studentPage = true;
	
	public void enterOnAction(ActionEvent e) {
		
	}
	
	public void closeOnAction(ActionEvent e) {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}
	
	public void swapOnAction(ActionEvent e) {
		if(studentPage) {
			pageName.setText("Teacher Signup Page");
			changePage.setText("Go to Student Signup");
			
			
			studentPage = false;
		} else {
			pageName.setText("Student Signup Page");
			changePage.setText("Go to Teacher Signup");
			
			studentPage = true;
		}
	}
}
