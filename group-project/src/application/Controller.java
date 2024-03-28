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
	  @FXML
	    private Button btnMode;

	    @FXML
	    private Label errorLabel;

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

	
	    public void loginButtonOnAction(ActionEvent e) throws IOException {
			
			SwitchSceneController switchSceneController = new SwitchSceneController();
			switchSceneController.switchToTempScene(e);
			
			/* THIS CODE IS COMMENTED OUT TEMPORARILY
			
			if (loginUser.getText().isBlank() == false && loginPass.getText().isBlank() == false) {
				//errorLabel.setText("Error");
				validateLogin();
				
			} else {
					errorLabel.setText("Please enter username and password");
				} */
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
	    
	    
	    private boolean isLightMode = true;

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
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

		public void initialize1(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

}