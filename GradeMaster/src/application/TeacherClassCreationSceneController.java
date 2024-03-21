package application;

import application.SwitchSceneController;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

//added more modules for sql
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

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
	void Create(ActionEvent event) {
		// retrieve course name and numbers from text fields
		String courseName = CourseNameTextField.getText().trim();
		String courseNumText = CourseNumTextField.getText().trim();

		// validate user input
		if (courseName.isEmpty() || courseNumText.isEmpty()) {
			showAlert("Error", "Empty Fields", "Please enter both Course Name and Course Number.");
			return;
		}

		try {
			// parse the course num
			int courseNum = Integer.parseInt(courseNumText);
			// create ClassData obj with user input
			ClassData classData = new ClassData(courseName, courseNum);

			// Add the class data to the table view
			ObservableList<ClassData> classDatas = TableView.getItems();
			classDatas.add(classData);
			TableView.setItems(classDatas);

			// Call method to save the class to the database
			saveClassToDatabase(courseName, courseNum);
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

	// method for saving the class to the database
	private void saveClassToDatabase(String courseName, int courseNum) {
		// database connection creds
		String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
		String databaseUser = "GradeMaster";
		String databasePassword = "Justice_League";

		//try connection
		try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
			//proper values within the database
			String sql = "INSERT INTO courses (`course_name`, `course_id`) VALUES (?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, courseName);
				statement.setInt(2, courseNum);

				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("Course inserted successfully!");
				} else {
					System.out.println("Failed to insert course");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			showAlert("Error", "Database Error", "An error occurred while saving the course to the database.");
		}
	}

	// adding getter methods so that DBClassCreationFallBack2 can access this class
	public TextField getCourseNameTextField() {
		return CourseNameTextField;
	}

	public TextField getCourseNumTextField() {
		return CourseNumTextField;
	}

	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTempScene(e);
	}
}
