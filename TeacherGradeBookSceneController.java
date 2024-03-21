package application;

import application.SwitchSceneController;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;

public class TeacherGradeBookSceneController implements Initializable{
	//test
	  @FXML
	    private TableColumn<AssData,String> Ass;

	    @FXML
	    private TableColumn<GradeData,String> Ass2;

	    @FXML
	    private Label AssNameLabel;

	    @FXML
	    private TextField AssTextField;

	    @FXML
	    private TextField AssTextField2;
	    
	    @FXML
	    private Label CreateAssLabel;

	    @FXML
	    private Label AssTypeLabel;

	    @FXML
	    private Label AssWeightLabel;

	    @FXML
	    private Button BackButton;

	    @FXML
	    private Button CreateAssButton;

	    @FXML
	    private TableColumn<GradeData,Integer> Grade;

	    @FXML
	    private TextField GradeTextField;

	    @FXML
	    private Button InputGradeButton;

	    @FXML
	    private Button RemoveAssButton;

	    @FXML
	    private Button RemoveGradeButton;

	    @FXML
	    private TableColumn<GradeData,String> Student;

	    @FXML
	    private TextField StudentTextField;

	    @FXML
	    private TableView<AssData> TableView1;

	    @FXML
	    private TableView<GradeData> TableView2;

	    @FXML
	    private TableColumn<AssData, String> Type;

	    @FXML
	    private TextField TypeTextField;

	    @FXML
	    private TableColumn<AssData,Integer> Weight;

	    @FXML
	    private TextField WeightTextField;

	    @FXML
	    private Button btnMode;

	    @FXML
	    private ImageView imgMode;

	    @FXML
	    private TabPane parent;


	@Override
	public void initialize(URL url, ResourceBundle ResourceBundel) {
		Ass.setCellValueFactory(new PropertyValueFactory<AssData,String>("Ass"));
		Type.setCellValueFactory(new PropertyValueFactory<AssData,String>("Type"));
		Weight.setCellValueFactory(new PropertyValueFactory<AssData,Integer>("Weight"));
		
		Ass2.setCellValueFactory(new PropertyValueFactory<GradeData,String>("Student"));
		Student.setCellValueFactory(new PropertyValueFactory<GradeData,String>("Student"));
		Grade.setCellValueFactory(new PropertyValueFactory<GradeData,Integer>("Grade"));  
	}
	
	@FXML
	void CreateAss(ActionEvent event) {
		String AssText = AssTextField.getText().trim();
	    String TypeText = TypeTextField.getText().trim();
	    String WeightText= WeightTextField.getText().trim();
	    
	    if (AssText.isEmpty() || TypeText.isEmpty()||WeightText.isEmpty()) {
	        showAlert("Error", "Empty Fields", "Please enter Assignment Name, Student Name, and Grade.");
	        return;
	    }
	    try {
	    AssData assdata = new AssData(AssTextField.getText(),
	            TypeTextField.getText(),
	            Integer.parseInt(WeightTextField.getText()));

	    ObservableList<AssData> assdatas1 = TableView1.getItems();
	    assdatas1.add(assdata);
	    TableView1.setItems(assdatas1);
	    } catch (NumberFormatException e) {
	        showAlert("Error", "Invalid Number", "Please enter a valid Weight Number."); 
	    }
	    
	    
	}

	
	@FXML 
	void RemoveAss(ActionEvent event){
	    if (TableView1.getItems().isEmpty()) {
	        showAlert("Error", "TableView1 is empty", "Please add items to Table before removing.");
	        return;
	    }
	    int selectedID = TableView1.getSelectionModel().getSelectedIndex();
	    if (selectedID == -1) {
	        showAlert("Error", "No item selected", "Please select an Assignment from the Table before removing.");
	        return;
	    }

	    TableView1.getItems().remove(selectedID);
	}
	
	@FXML
	void InputGrade(ActionEvent event) {
		 	String AssText = AssTextField2.getText().trim();
		    String StudentText = StudentTextField.getText().trim();
		    String GradeText= GradeTextField.getText().trim();
		    if (AssText.isEmpty() || StudentText.isEmpty()||GradeText.isEmpty()) {
		        showAlert("Error", "Empty Fields", "Please enter Assignment Name, Student Name, and Grade.");
		        return;
		    }
		    try {
	    	GradeData gradedata = new GradeData(AssTextField2.getText(),
		            StudentTextField.getText(),
		            Integer.parseInt(GradeTextField.getText()));
	    	ObservableList<GradeData> gradedatas1 = TableView2.getItems();
		    gradedatas1.add(gradedata);
		    TableView2.setItems(gradedatas1);
		    } catch (NumberFormatException e) {
		        showAlert("Error", "Invalid Number", "Please enter a valid Grade Number.");
		    }
		   

		   
	}


	@FXML
	void RemoveGrade(ActionEvent event) {
	    if (TableView2.getItems().isEmpty()) {
	        showAlert("Error", "TableView2 is empty", "Please add assignments with grades to Table before removing.");
	        return;
	    }

	    int selectedID = TableView2.getSelectionModel().getSelectedIndex();
	    if (selectedID == -1) {
	        showAlert("Error", "No assignment selected", "Please select an assignment from the Table before removing grades.");
	        return;
	    }

	    TableView2.getItems().remove(selectedID);
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
	    	BackButton.setTextFill(paint);
	    	CreateAssButton.setTextFill(paint);
	    	BackButton.setTextFill(paint);
	    	InputGradeButton.setTextFill(paint);
	    	RemoveAssButton.setTextFill(paint);
	    	RemoveGradeButton.setTextFill(paint);
	    	CreateAssLabel.setTextFill(paint2);
	    	AssNameLabel.setTextFill(paint2);
	    	AssTypeLabel.setTextFill(paint2);
	    	AssWeightLabel.setTextFill(paint2);

	    }
	    
	 private void setDarkMode() {
	 	parent.getStylesheets().remove("styles/lightMode.css");
	    	parent.getStylesheets().add("styles/darkMode.css");
	    	Image image = new Image("img/light.png");
	    	imgMode.setImage(image);
	    //	imgMode.setStyle("-fx-shape:round");
	    	Paint paint = Paint.valueOf("black");
	    	Paint paint2 = Paint.valueOf("white");
	    	BackButton.setTextFill(paint);
	    	CreateAssButton.setTextFill(paint);
	    	BackButton.setTextFill(paint);
	    	InputGradeButton.setTextFill(paint);
	    	RemoveAssButton.setTextFill(paint);
	    	RemoveGradeButton.setTextFill(paint);
	    	CreateAssLabel.setTextFill(paint2);
	    	AssNameLabel.setTextFill(paint2);
	    	AssTypeLabel.setTextFill(paint2);
	    	AssWeightLabel.setTextFill(paint2);
	    	
	    	
	    }

}

