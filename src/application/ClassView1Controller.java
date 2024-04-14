package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

public class ClassView1Controller {

   
    @FXML
    private Label Top1Label;

    @FXML
    private Label Top2Label;

    @FXML
    private Label Top3Label;
    
    @FXML
    private Label GeneralLabel;

    @FXML
    private Button btnMode;
    
    @FXML
    private Button btnMenu;

    @FXML
    private ImageView imgMode;

    @FXML
    private TitledPane parent;

    @FXML
    private Hyperlink resLabel;

    @FXML
    private AnchorPane syllabusLabel;

    private boolean isLightMode = true;

    @FXML
    private void changeMode(ActionEvent event) {
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
    	Paint paint = Paint.valueOf("black");
    	//Paint paint2 = Paint.valueOf("black");
    	GeneralLabel.setTextFill(paint);
    	Top1Label.setTextFill(paint);
    	Top2Label.setTextFill(paint);
    	Top3Label.setTextFill(paint);

    }
    
 private void setDarkMode() {
 	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    	Paint paint = Paint.valueOf("white");
    	//Paint paint2 = Paint.valueOf("black");
    	GeneralLabel.setTextFill(paint);
    	Top1Label.setTextFill(paint);
    	Top2Label.setTextFill(paint);
    	Top3Label.setTextFill(paint);
    	btnMode.setStyle("round-border");
    	
    }
}
