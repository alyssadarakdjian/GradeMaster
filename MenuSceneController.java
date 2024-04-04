package application;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class MenuSceneController {
	
	 @FXML
	    private Button btnClassCreation;

	    @FXML
	    private Button btnGBstudent;

	    @FXML
	    private Button btnGBteacher;
	    
	    @FXML
	    private Button btnMode;

	    @FXML
	    private ImageView imgMode;

	    @FXML
	    private AnchorPane parent;

	public void TeacherGradeBook(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherGradeBook(e);
	}
	
	public void StudentGradeBook(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToStudentGradeBook(e);
	}
	
	public void TeacherClassCreate(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherClassCreate(e);
	}
	
	// public boolean isLightMode = true;

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
	    	Paint paint = Paint.valueOf("white");
	    	btnGBteacher.setTextFill(paint);
	    	btnGBstudent.setTextFill(paint);
	    	btnClassCreation.setTextFill(paint);
	    	Controller.isLightMode = true;

	    	
	    	
	 
	    }
	    
     public void setDarkMode() {
     	parent.getStylesheets().remove("styles/lightMode.css");
	    	parent.getStylesheets().add("styles/darkMode.css");
	    	Image image = new Image("img/light.png");
	    	imgMode.setImage(image);
	    	
	    	Paint paint = Paint.valueOf("black");
	    	btnGBteacher.setTextFill(paint);
	    	btnGBstudent.setTextFill(paint);
	    	btnClassCreation.setTextFill(paint);
	    	Controller.isLightMode = false;
	    	
	    }
	

	

}
