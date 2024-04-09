package application;

import java.io.IOException;
//import java.awt.Button;
//import java.awt.Label;
//import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Controller implements Initializable {
	
    	static boolean isLightMode = true;
    	@FXML
	    private Button swapPage;
	  	@FXML
	  	private Button btnMode;
	    @FXML
	    private Label errorLabel;
	    @FXML
	    private Label loginType;
	    @FXML
	    private Label errorNoPass;
	    @FXML
	    private ImageView imgMode;
	    @FXML
	    private Button loginButton;
	    @FXML
	    private Button loginCancel;
	    @FXML
	    private PasswordField loginPass;
	    @FXML
	    private TextField loginUser;
	    @FXML
	    private BorderPane parent;
	    @FXML
	    private AnchorPane password;
	    @FXML
	    private Label labelUserName;
	    @FXML
	    private Label labelPassword;
	    @FXML
	    private ImageView imgUni;
	    @FXML
	    private Button createLogin;
	    //private HyperLink createLoginPage;     trying to make a link to create login page
	    private boolean studentPage = true;
	    
	    
	    
	   
	    
	    
	    
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
		
		


	
	    public void loginButtonOnAction(ActionEvent e) throws IOException {
	    	
	    	if (checkUserPass()) {
	    		if (studentPage) {
	    			SwitchSceneController switchSceneController = new SwitchSceneController();  //to specify if it goes through the teacher or student end
	    			switchSceneController.switchToStudentMenuScene(e);
	    		} else {
	    			SwitchSceneController switchSceneController = new SwitchSceneController();  
	    			switchSceneController.switchToTeacherMenuScene(e);
	    		}
	    		
	    	}
	    	
	    
	    	
			
			
			
			/* THIS CODE IS COMMENTED OUT TEMPORARILY
			
			if (loginUser.getText().isBlank() == false && loginPass.getText().isBlank() == false) {
				//errorLabel.setText("Error");
				validateLogin();
				
			} else {
					errorLabel.setText("Please enter username and password");
				} */
	}
	    
	    public void createLoginOnAction(ActionEvent e) throws IOException{
	    	SwitchSceneController switchSceneController = new SwitchSceneController();
			switchSceneController.switchToCreateLoginScene(e);
	    }
	    
	    public boolean checkUserPass() {
	    	
	    	if(userCheck() && passCheck()) {
	    		return true;
	    	}
	    	
	    	
	    	
	    	return false;
	    }
	    
	    public boolean userCheck() {
	    	if(loginUser.getCharacters().length() < 9) {
	    		userErrorMessage();
	    		return false;
	    	}
	    	
	    	return true;
	    }
	    
	    public boolean passCheck() {
	    	
	    	boolean lower = false;
	    	boolean upper = false;
	    	boolean number = false;
	    	
	    	String temp = loginPass.getCharacters().toString();
	    	char check;
	    	if (temp.length() < 9) {
	    		
	    		shortPass();
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
	    	
	    	if (!lower) {
	    		noLower();
	    	}
	    	
	    	if (!upper) {
	    		noUpper();
	    	}
	    	
	    	if (!number) {
	    		noNumber();
	    	}
	    	
	    	return false;
	    }
		
		public void userErrorMessage() {
			if (loginUser.getCharacters() == null) {
				errorLabel.setText("No Username Was Entered");
			} else if (loginUser.getCharacters().length() < 9) {
				errorLabel.setText("Username must be at least 9 characters");
			}
		}

		public void noLower() {
			errorLabel.setText("Password must contain at least 1 lowercase letter");
			
		}
		
		public void noUpper() {
			errorLabel.setText("Password must contain at least 1 uppercase letter");
			
		}
		
		public void noNumber() {
			errorLabel.setText("Password must contain at least 1 number");
			
		}
		
		public void shortPass() {
			errorLabel.setText("Password must be at least 9 characters");
			
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
		

	    public void changeMode(ActionEvent event) {
	    	isLightMode = !isLightMode;
	    	if(isLightMode) {
	    		setLightMode();
	    	}else {
	    		setDarkMode();
	    	}
	    }
	    
	    private void setLightMode() {
	    	parent.getStylesheets().remove("styles/darkMode.css");
	    	parent.getStylesheets().add("styles/lightMode.css");
	    	Image image = new Image("img/dark.png");
	    	imgMode.setImage(image);
	    	Image imageUni = new Image("application/Login_Logo.jpg");
	    	imgUni.setImage(imageUni);
	    	Paint paint1 = Paint.valueOf("white");
	    	Paint paint2 = Paint.valueOf("black");
	    	labelUserName.setTextFill(paint2);
	    	labelPassword.setTextFill(paint2);
	    	loginButton.setTextFill(paint1);
	    	loginCancel.setTextFill(paint1);
	    	swapPage.setTextFill(paint1);
	    	loginType.setTextFill(paint2);
	    	createLogin.setTextFill(paint1);
	    	Controller.isLightMode = true;
	    }
	    
        private void setDarkMode() {
        	parent.getStylesheets().remove("styles/lightMode.css");
	    	parent.getStylesheets().add("styles/darkMode.css");
	    	Image image = new Image("img/light.png");
	    	imgMode.setImage(image);
	    	Image imageUni = new Image("application/dark_loginPage.png");
	    	imgUni.setImage(imageUni);
	    	Paint paint1 = Paint.valueOf("black");
	    	Paint paint2 = Paint.valueOf("white");
	    	labelUserName.setTextFill(paint2);
	    	labelPassword.setTextFill(paint2);
	    	loginButton.setTextFill(paint1);
	    	loginCancel.setTextFill(paint1);
	    	swapPage.setTextFill(paint1);
	    	loginType.setTextFill(paint2);
	    	createLogin.setTextFill(paint1);
	    	Controller.isLightMode = false;
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

		public void initialize1(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

}