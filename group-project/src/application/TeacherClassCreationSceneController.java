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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			try {
				loadDataFromDatabase();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@FXML
		void Create(ActionEvent event) {
			// retrieve course name and numbers from text fields
			String courseName = CourseNameTextField.getText().trim();
			String courseNumText = CourseNumTextField.getText().trim();

			// validate user input
			if (courseName.isEmpty()&& courseNumText.isEmpty()) {
		        showAlert("Error", "Empty Fields", "Please enter Course Name and Course Number");
		        return;
		    }    
		    
		    if (courseName.isEmpty()) {
		        showAlert("Error", "Empty Fields", "Please enter Course Name");
		        return;
		    
		    }    
		    if (courseNumText.isEmpty()) {
			        showAlert("Error", "Empty Fields", "Please enter Course Number");
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
				

				// Call method to save the class to the database
				saveClassToDatabase(courseName, courseNum);
			} catch (NumberFormatException e) {
				showAlert("Error", "Invalid Number", "Please enter a valid Course Number.");
			}
		}

		@FXML
		void Remove(ActionEvent event) {
		    if (TableView.getItems().isEmpty()) {
		        showAlert("Error", "TableView is empty", "Please add a Course to Table before removing.");
		        return;
		    }
		    int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
		    if (selectedIndex == -1) {
		        showAlert("Error", "No item selected", "Please select a Course from the Table before removing.");
		        return;
		    }

		    // Get the selected class data
		    ClassData selectedClass = TableView.getItems().get(selectedIndex);

		    // Remove from TableView
		    TableView.getItems().remove(selectedIndex);

		    // Delete from the database
		    deleteClassFromDatabase(selectedClass.getCourseNum());
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
		
		//method for loading Data From Database
		private void loadDataFromDatabase() throws SQLException {
	        String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	        String databaseUser = "GradeMaster";
	        String databasePassword = "Justice_League";

	        try (Connection connection1 = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	            String sql = "SELECT course_id, course_name FROM courses";
	            try (PreparedStatement statement = connection1.prepareStatement(sql)) {
	                ResultSet resultSet = statement.executeQuery();
	                ObservableList<ClassData> classDataList = FXCollections.observableArrayList();
	                while (resultSet.next()) {
	                    int courseId = resultSet.getInt("course_id");
	                    String courseName = resultSet.getString("course_name");
	                    classDataList.add(new ClassData(courseName, courseId));
	                TableView.setItems(classDataList);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert("Error", "Database Error", "An error occurred while loading data from the database.");
	        }
	    }
		}
		
		//method for deleting from database
		private void deleteClassFromDatabase(int courseNum) {
		    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
		    String databaseUser = "GradeMaster";
		    String databasePassword = "Justice_League";

		    String sql = "DELETE FROM courses WHERE course_id = ?";

		    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword);
		         PreparedStatement statement = connection.prepareStatement(sql)) {
		        statement.setInt(1, courseNum);

		        int rowsDeleted = statement.executeUpdate();
		        if (rowsDeleted > 0) {
		            System.out.println("Course deleted successfully!");
		        } else {
		            System.out.println("Failed to delete course");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        showAlert("Error", "Database Error", "An error occurred while deleting the course from the database.");
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
