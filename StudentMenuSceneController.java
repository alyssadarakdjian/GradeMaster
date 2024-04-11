package application;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

public class StudentMenuSceneController {

    @FXML
    private Button btnGBstudent;
    
    @FXML
    private Button btnGraphPage;
    
    @FXML
    private Button btnMode;

    @FXML
    private ImageView imgMode;

    @FXML
    private AnchorPane parent;
    
    @FXML
    private ChoiceBox<String> courseChoiceBox;
    
    @FXML
    private Label courseLabel;

    
    


    public void StudentGradeBook(ActionEvent e) throws IOException {
    	SwitchSceneController switchSceneController = new SwitchSceneController();
    	switchSceneController.switchToStudentGradeBook(e);
    }

    public void GraphPage(ActionEvent e) throws IOException {
    	SwitchSceneController switchSceneController = new SwitchSceneController();
    	switchSceneController.switchToGraphScene(e);
    }

   // private boolean isLightMode = true;

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
    	btnGBstudent.setTextFill(paint);
    	btnGraphPage.setTextFill(paint);
    	courseLabel.setTextFill(paint);
    	courseChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if(newValue != null) {
	        	courseLabel.setText(newValue); // Update label text with selected assignment name
	        	courseLabel.setTextFill(paint);
	        }
	    });
    	Controller.isLightMode= true ;

    }
    
    public void setDarkMode() {
    	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    //	imgMode.setStyle("-fx-shape:round");
    	Paint paint = Paint.valueOf("black");
    	btnGBstudent.setTextFill(paint);
    	btnGraphPage.setTextFill(paint);
    	courseLabel.setTextFill(paint);
    	courseChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if(newValue != null) {
	        	courseLabel.setText(newValue); // Update label text with selected assignment name
	        	courseLabel.setTextFill(paint);
	        }
	    });
    	Controller.isLightMode= false;
    	
    }

	public ChoiceBox<String> getCourseChoiceBox() {
		return courseChoiceBox;
	}

	public void setCourseChoiceBox(ChoiceBox<String> courseChoiceBox) {
		this.courseChoiceBox = courseChoiceBox;
	}
    	
    
}