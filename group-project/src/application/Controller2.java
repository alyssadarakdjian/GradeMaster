package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

public class Controller2 {
	
	 @FXML
	    private AnchorPane parent;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private ImageView imgMode;

    @FXML
    private Button btnMode;
    
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
    	Paint paint = Paint.valueOf("white");
    	btn1.setTextFill(paint);
    	btn2.setTextFill(paint);
    	btn3.setTextFill(paint);
    	btn4.setTextFill(paint);
    	btn5.setTextFill(paint);
    	
    	
    }
    
    private void setDarkMode() {
    	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    	Paint paint = Paint.valueOf("black");
    	btn1.setTextFill(paint);
    	btn2.setTextFill(paint);
    	btn3.setTextFill(paint);
    	btn4.setTextFill(paint);
    	btn5.setTextFill(paint);
    	
    }

}

