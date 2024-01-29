package application;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class TeacherGradeBookSceneController implements Initializable{
	
	@FXML
	private TableView<AssData> TableView1;
	
	@FXML
	private TableView<AssData> TableView2;
	
	@FXML
	private TableColumn<AssData,String> Ass;
	
	@FXML
	private TableColumn<AssData,String> Type;
	
	@FXML
	private TableColumn<AssData,Integer> Weight;
	
	@FXML
	private TableColumn<AssData,String> Ass2;
	
	@FXML
	private TableColumn<AssData,String> Type2;
	
	@FXML
	private TableColumn<AssData,Integer> Weight2;
	
	@FXML
	private TextField AssTextField;
	
	@FXML
	private TextField TypeTextField;
	
	@FXML
	private TextField WeightTextField;
	
	@FXML
	private Button CreateAssButton;
	
	@FXML
	private Button RemoveAssButton;
	
	@FXML
	private Button InputGradeButton;
	
	@FXML
	private Button RemoveGradeButton;

	@Override
	public void initialize(URL url, ResourceBundle ResourceBundel) {
		Ass.setCellValueFactory(new PropertyValueFactory<AssData,String>("Ass"));
		Type.setCellValueFactory(new PropertyValueFactory<AssData,String>("Type"));
		Weight.setCellValueFactory(new PropertyValueFactory<AssData,Integer>("Weight"));
	}
	
	@FXML
	void CreateAss(ActionEvent event) {
	    
	    AssData assdata = new AssData(AssTextField.getText(),
	            TypeTextField.getText(),
	            Integer.parseInt(WeightTextField.getText()));

	    ObservableList<AssData> assdatas1 = TableView1.getItems();
	    assdatas1.add(assdata);
	    TableView1.setItems(assdatas1);
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

	private void showAlert(String title, String header, String content) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle(title);
	    alert.setHeaderText(header);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	

}
