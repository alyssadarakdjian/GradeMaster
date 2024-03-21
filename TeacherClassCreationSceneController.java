package application;

import application.SwitchSceneController;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.Style;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class TeacherClassCreationSceneController implements Initializable{
	
	@FXML
	private TableView<ClassData> TableView;
	
	@FXML
	private TableColumn<ClassData,String> CourseName;
	
	@FXML
	private TableColumn<ClassData,Integer> CourseNum;
	
	@FXML
	private TextField CourseNameTextField;
	
	@FXML
	private TextField CourseNumTextField;
	
	@FXML
	private Button CreateButton;
	
	@FXML
	private Button RemoveButton;
	
	@FXML
	private Label CourseCreationLabel;
	
	@FXML
	private Label CourseNameLabel;
	
	@FXML
	private Label CourseNumberLabel;
	
	  @FXML
	    private Button btnMode;
	  
	  @FXML
	    private Button BackButton;

	    @FXML
	    private ImageView imgMode;

	    @FXML
	    private AnchorPane parent;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CourseName.setCellValueFactory(new PropertyValueFactory<ClassData,String>("CourseName"));
		CourseNum.setCellValueFactory(new PropertyValueFactory<ClassData,Integer>("CourseNum"));
	}
	
	@FXML
	void Create(ActionEvent event) {
	    String courseName = CourseNameTextField.getText().trim();
	    String courseNumText = CourseNumTextField.getText().trim();

	    if (courseName.isEmpty() || courseNumText.isEmpty()) {
	        showAlert("Error", "Empty Fields", "Please enter both Course Name and Course Number.");
	        return;
	    }

	    try {
	        int courseNum = Integer.parseInt(courseNumText);
	        ClassData classData = new ClassData(courseName, courseNum);
	        ObservableList<ClassData> classDatas = TableView.getItems();
	        classDatas.add(classData);
	        TableView.setItems(classDatas);
	    } catch (NumberFormatException e) {
	        showAlert("Error", "Invalid Number", "Please enter a valid Course Number.");
	    }
	}
	
	@FXML 
	void Remove(ActionEvent event){
		 if (TableView.getItems().isEmpty()) {
		        showAlert("Error", "TableView1 is empty", "Please add a Course to Table before removing.");
		        return;
		    }
		    int selectedID = TableView.getSelectionModel().getSelectedIndex();
		    if (selectedID == -1) {
		        showAlert("Error", "No item selected", "Please select a Course from the Table before removing.");
		        return;
		    }

		    TableView.getItems().remove(selectedID);
		}

		private void showAlert(String title, String header, String content) {
		    Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle(title);
		    alert.setHeaderText(header);
		    alert.setContentText(content);
		    alert.showAndWait();
		
	}
		
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTempScene(e);
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
    	Paint paint = Paint.valueOf("white");
    	Paint paint2 = Paint.valueOf("black");
    	CreateButton.setTextFill(paint);
    	RemoveButton.setTextFill(paint);
    	BackButton.setTextFill(paint);
    	//CourseNameTextField.setStyle("-fx-text-fill: black;");
    	CourseCreationLabel.setTextFill(paint2);
    	CourseNameLabel.setTextFill(paint2);
    	CourseNumberLabel.setTextFill(paint2);
   
    	
    	
  
    }
    
 private void setDarkMode() {
 	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    //	imgMode.setStyle("-fx-shape:round");
    	Paint paint = Paint.valueOf("black");
    	Paint paint2 = Paint.valueOf("white");
    	CreateButton.setTextFill(paint);
    	RemoveButton.setTextFill(paint);
    	BackButton.setTextFill(paint);
    	CourseCreationLabel.setTextFill(paint2);
    	CourseNameLabel.setTextFill(paint2);
    	CourseNumberLabel.setTextFill(paint2);
    	
    	
    }



}
