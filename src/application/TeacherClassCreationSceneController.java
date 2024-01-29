package application;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CourseName.setCellValueFactory(new PropertyValueFactory<ClassData,String>("CourseName"));
		CourseNum.setCellValueFactory(new PropertyValueFactory<ClassData,Integer>("CourseNum"));
	}
	
	@FXML 
	void Create(ActionEvent event){
		ClassData classdata = new ClassData(CourseNameTextField.getText(),
				Integer.parseInt(CourseNumTextField.getText()));
		ObservableList<ClassData> classdatas = TableView.getItems();
		classdatas.add(classdata);
		TableView.setItems(classdatas);
		
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
	

}
