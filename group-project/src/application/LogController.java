package application;

import java.io.IOException;

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
	@FXML
	private Label errorUser;
	@FXML
	private Label errorPass;
	@FXML
	private Label errorNoMatch;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	
	private boolean studentPage = true;
	
	public void enterOnAction(ActionEvent e) throws IOException {
		if(infoCorrect()) {
			//send info to the database
			
			
			SwitchSceneController switchSceneController = new SwitchSceneController();  //to specify if it goes through the teacher or student end
			switchSceneController.switchToLoginScene(e);
			
		}
		
	}
	
	public boolean infoCorrect() {
//		if(firstName.getText().isEmpty()) {
//			return false;
//		}
//		
//		if(lastName.getText().isEmpty()) {
//			
//		}
		
		
		
		return true;
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
