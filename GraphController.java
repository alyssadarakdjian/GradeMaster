package application;

import java.io.IOException;
import application.SwitchSceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GraphController {
	
	@FXML
	private Button btnMode;
	
	@FXML
	private Button backButton;
	
	 @FXML
	    private Button backButton2;

	    @FXML
	    private Button backButton3;
	
	@FXML
    private TabPane parent;
	
	 @FXML
	 private ImageView imgMode;


	public void barChart() {
		
	}
	
	public void pieChart() {
		
	}
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToStudentMenuScene(e);
	}
	
	 public void changeMode(ActionEvent event) {
	    	Controller.setLightMode(!Controller.isLightMode());
	    	if(Controller.isLightMode()) {
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
	    	Controller.setLightMode(true);

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
	    	Controller.setLightMode(false);
	    	
	    }
}
