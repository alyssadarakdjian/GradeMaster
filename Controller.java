package application;

import java.io.IOException;

//import java.awt.Button;
//import java.awt.Label;
//import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
    	private boolean isLightMode = true;
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
	    
	    public static int userId;
	    
	    
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
	    	
	    	if (validateLogin()) {
	    		if (studentPage) {
	    			System.out.println("Student_ID:"+userId);
	    			SwitchSceneController switchSceneController = new SwitchSceneController();  //to specify if it goes through the teacher or student end
	    			switchSceneController.switchToStudentMenuScene(e);
	    		} else {
	    			System.out.println("Teacher_ID:"+userId);
	    			SwitchSceneController switchSceneController = new SwitchSceneController();  
	    			switchSceneController.switchToTeacherMenuScene(e);
	    		}
	    		
	    	}
	    
	    }
	    
	    public void createLoginOnAction(ActionEvent e) throws IOException{
	    	SwitchSceneController switchSceneController = new SwitchSceneController();
			switchSceneController.switchToCreateLoginScene(e);
	    }
	    
	    public boolean checkUserPass() {
	    	
	    	
	    	return true;
	    }
	    
	

		
		
		public void loginCancelOnAction(ActionEvent e) {
			Stage stage = (Stage) loginCancel.getScene().getWindow();
			stage.close();
		}
		
		public boolean validateLogin() {
	        String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	        String databaseUser = "GradeMaster";
	        String databasePassword = "Justice_League";
	        String dataGroup = studentPage ? "students" : "teachers"; // Determines the data group based on studentPage flag

	        try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {

	            String verifyLogin = "SELECT " + (studentPage ? "student_id" : "teacher_id") + " FROM " + dataGroup + " WHERE username = ? AND password = ?";
	            PreparedStatement statement = connection.prepareStatement(verifyLogin);
	            statement.setString(1, loginUser.getText());
	            statement.setString(2, loginPass.getText());

	            ResultSet queryResult = statement.executeQuery();

	            if (queryResult.next()) {
	                // If login is successful, retrieve the appropriate ID
	                userId = queryResult.getInt(1); // Assuming the ID column is the first one in the result set
	                errorLabel.setText("Welcome!");
	                return true;
	            } else {
	                errorLabel.setText("Invalid Login. Please Try Again");
	                return false;
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	    }

	    // Method to retrieve user_id (student_id or teacher_id)
	    public int getUserId() {
	        return userId;
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
