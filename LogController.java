package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class LogController {
	
	@FXML
	private Button signUp;
	@FXML
	private Button btnMode;
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
	@FXML
    private BorderPane parent;
	@FXML
    private ImageView imgMode;
	@FXML
    private ImageView imgUni;
	
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
	
	public void changeMode(ActionEvent event) {
    	Controller.isLightMode = !Controller.isLightMode;
    	if(Controller.isLightMode) {
    		setLightMode();
    	}else {
    		setDarkMode();
    	}
    }
    
    public void setLightMode() {
    	parent.getStylesheets().remove("styles/darkMode.css");
    	parent.getStylesheets().add("styles/lightMode.css");
    	Image image = new Image("img/dark.png");
    	imgMode.setImage(image);
    	Image imageUni = new Image("application/Login_Logo.jpg");
    	imgUni.setImage(imageUni);
    	Paint paint = Paint.valueOf("black");
    	Paint paint2 = Paint.valueOf("white");
    	changePage.setTextFill(paint2);
    	signUp.setTextFill(paint2);
    	close.setTextFill(paint2);
    	pageName.setTextFill(paint);
    	Controller.isLightMode = true;

    }
    
public void setDarkMode() {
			parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    	Image imageUni = new Image("application/dark_loginPage.png");
    	imgUni.setImage(imageUni);
    	Paint paint = Paint.valueOf("black");
    	Paint paint2 = Paint.valueOf("white");
    	changePage.setTextFill(paint);
    	signUp.setTextFill(paint);
    	close.setTextFill(paint);
    	pageName.setTextFill(paint2);
    	Controller.isLightMode = false;
    	
    }
	

}