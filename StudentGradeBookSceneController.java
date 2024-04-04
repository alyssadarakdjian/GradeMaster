package application;

import application.SwitchSceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;

public class StudentGradeBookSceneController implements Initializable{
	@FXML
    private TableColumn<StudentGradeData, String> Ass;

    @FXML
    private TableColumn<StudentGradeData, String> Feedback;

    @FXML
    private TableColumn<StudentGradeData, Character> Grade;

    @FXML
    private Label LetterLabel;

    @FXML
    private TableColumn<StudentGradeData, Integer> Percent;

    @FXML
    private Label PercentageLabel;

    @FXML
    private TableView<StudentGradeData> TableView;

    @FXML
    private Label TotalGradeLable;

    @FXML
    private TableColumn<StudentGradeData, String> Weight;
    
    @FXML
    private TableColumn<?,?> ButtonColumn;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnMode;

    @FXML
    private ScrollPane parent;
    
    @FXML
    private ImageView imgMode;

  

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
    	btnBack.setTextFill(paint);
    	Paint paint2 = Paint.valueOf("#282828");
    	TotalGradeLable.setTextFill(paint2);
    	PercentageLabel.setTextFill(paint2);
    	LetterLabel.setTextFill(paint2);
    	Ass.setStyle("#282828");
    	Controller.isLightMode= true ;
    	
  
    }
    
    public void setDarkMode() {
 	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    	Paint paint = Paint.valueOf("black");
    	Paint paint2 = Paint.valueOf("white");
    	btnBack.setTextFill(paint);
    	TotalGradeLable.setTextFill(paint2);
    	PercentageLabel.setTextFill(paint2);
    	LetterLabel.setTextFill(paint2);
    	Controller.isLightMode= false ;
    	
    }


	
	ObservableList<StudentGradeData> list = FXCollections.observableArrayList(
			new StudentGradeData("HW#1","10%", 'A', 100, "Good Job!!"),
			new StudentGradeData("HW#2","10%", 'A', 95, "I have made some notes but overall good job!!"),
			new StudentGradeData("HW#3","10%", 'A', 96, "Good Job!!")
			
			);
	@Override
	public void initialize(URL url, ResourceBundle ResourceBundel) {
		Ass.setCellValueFactory(new PropertyValueFactory<StudentGradeData,String>("Ass"));
		Weight.setCellValueFactory(new PropertyValueFactory<StudentGradeData,String>("Weight"));
		Grade.setCellValueFactory(new PropertyValueFactory<StudentGradeData,Character>("Grade"));
		Percent.setCellValueFactory(new PropertyValueFactory<StudentGradeData,Integer>("Percent"));
		Feedback.setCellValueFactory(new PropertyValueFactory<StudentGradeData,String>("Feedback"));
		TableView.setItems(list);
		calculateTotalGrade();
	}
	
	private void calculateTotalGrade() {
        int totalPoints = 0;

        for (StudentGradeData data : list) {
            totalPoints += data.getPercent();
        }

        double averagePercent = totalPoints / (double) list.size();
        String letterGrade = calculateLetterGrade(averagePercent);

        PercentageLabel.setText(String.format("%.2f%%", averagePercent));
        LetterLabel.setText(letterGrade);
    }
	
    private String calculateLetterGrade(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTempScene(e);
	}
	
}
