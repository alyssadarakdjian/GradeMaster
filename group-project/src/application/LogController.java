package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	private Label errorLabel;
	@FXML
	private Label errorNoMatch;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	
	private boolean studentPage = true;
	
	public void enterOnAction(ActionEvent e) throws Exception {
		if(infoCorrect()) {
			//send info to the database
			
			if (sendLogToDatabase()) {
				SwitchSceneController switchSceneController = new SwitchSceneController();  //to specify if it goes through the teacher or student end
				switchSceneController.switchToLoginScene(e);
			}
			
			errorLabel.setText("Info not sent to database");
			
			
		}
		
	}
	
	public boolean sendLogToDatabase() throws Exception {
		String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
		String databaseUser = "GradeMaster";
		String databasePassword = "Justice_League";
		String dataGroup = "students";
		
		if (!studentPage) {
			dataGroup = "teachers";
		}
		
		try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sql = "INSERT INTO " + dataGroup + " (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, firstName.getText());
	            statement.setString(2, lastName.getText());
	            statement.setString(3, userName.getText());
	            statement.setString(4, pass.getText());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    }
	}
	
	public boolean infoCorrect() {
		
		if (!fullName(firstName.getText())) {
			errorLabel.setText("Invalid Name");
			return false;
		}
		
		if (!fullName(lastName.getText())) {
			errorLabel.setText("Invalid Name");
			return false;
		}
		
		if (!userCheck()) {
			errorLabel.setText("Invalid Username");
			return false;
		}
		
		if (!passCheck()) {
			errorLabel.setText("Invalid Password");
			return false;
		}
		
		if (pass.getText() != repassword.getText()) {
			errorLabel.setText("Passwords do not Match");
			return false;
		}
		
		
		return true;
	}
	
	public boolean passCheck() {
    	
    	boolean lower = false;
    	boolean upper = false;
    	boolean number = false;
    	
    	String temp = pass.getText();
    	char check;
    	if (temp.length() < 8) {
    		

    		return false;
    	}
    	
    	for(int i = 0; i < temp.length(); i++) {
    		check = temp.charAt(i);
    		if (Character.isUpperCase(check)) {
    			upper = true;
    		}
    		
    		if (Character.isLowerCase(check)) {
    			lower = true;
    		}
    		
    		if (Character.isDigit(check)) {
    			number = true;
    		}
    	}
    	
    	if (lower && upper && number) {
    		return true;
    	}
    	
    	return false;
    }
	
	
	public boolean userCheck() {
    	if(userName.getText().length() < 8) {
    		return false;
    	}
    	
    	return true;
    }
	
	public boolean fullName(String name) {
		if (name.isEmpty()) {
			return false;
		}
		
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
