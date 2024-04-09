package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class TeacherGraphController {
	@FXML
    private Button backButton;

    @FXML
    private Button backButton2;
    
    @FXML
    private Button backButton3;

    @FXML
    private Button btnMode;

    @FXML
    private ImageView imgMode;

    @FXML
    private TabPane parent;
    
public void barChart() {
		
	}
	
	public void pieChart() {
		
	}
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherMenuScene(e);
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
	    	Paint paint = Paint.valueOf("white");
	    	backButton.setTextFill(paint);
	    	backButton2.setTextFill(paint);
	    	backButton3.setTextFill(paint);
	    	Controller.isLightMode = true;

	    }
	    
public void setDarkMode() {
			parent.getStylesheets().remove("styles/lightMode.css");
	    	parent.getStylesheets().add("styles/darkMode.css");
	    	Image image = new Image("img/light.png");
	    	imgMode.setImage(image);
	    	Paint paint = Paint.valueOf("black");
	    	backButton.setTextFill(paint);
	    	backButton2.setTextFill(paint);
	    	backButton3.setTextFill(paint);
	    	Controller.isLightMode = false;
	    	
	    }
}