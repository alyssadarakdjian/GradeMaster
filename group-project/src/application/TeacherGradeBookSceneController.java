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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class TeacherGradeBookSceneController implements Initializable{
	//test
	  @FXML
	    private TableColumn<AssData,String> Ass;

	    @FXML
	    private Label AssNameLabel;

	    @FXML
	    private TextField AssTextField;
	    
	    @FXML
	    private Label CreateAssLabel;

	    @FXML
	    private Label PointsPossLabel;
	    
	    @FXML
	    private Label PointsRecievedLabel;

	    @FXML
	    private Label FeedBackLabel;

	    @FXML
	    private Button BackButton;

	    @FXML
	    private Button CreateAssButton;

	    @FXML
	    private TableColumn<GradeData,Integer> PointsRecieved;

	    @FXML
	    private TextField PointsRecievedTextField;

	    @FXML
	    private Button InputGradeButton;

	    @FXML
	    private Button RemoveAssButton;

	    @FXML
	    private Button RemoveGradeButton;

	    @FXML
	    private TableColumn<GradeData,String> FeedBack;

	    @FXML
	    private TextField FeedBackTextField;

	    @FXML
	    private TableView<AssData> TableView1;

	    @FXML
	    private TableView<GradeData> TableView2;

	    @FXML
	    private TableColumn<AssData,Integer> PointsPoss;

	    @FXML
	    private TextField PointsPossTextField;

	    @FXML
	    private Button btnMode;

	    @FXML
	    private ImageView imgMode;

	    @FXML
	    private TabPane parent;
	    
	    @FXML 
	    private ChoiceBox<String> CourseChoiceBox;
	    
	    @FXML 
	    private ChoiceBox<String> AssChoiceBox;
	    
	    @FXML 
	    private Label ChooseAssLabel;
	    


	@Override
	public void initialize(URL url, ResourceBundle ResourceBundel) {
		Ass.setCellValueFactory(new PropertyValueFactory<AssData,String>("Ass"));
		PointsPoss.setCellValueFactory(new PropertyValueFactory<AssData,Integer>("PointsPoss"));
		
		PointsRecieved.setCellValueFactory(new PropertyValueFactory<GradeData,Integer>("PointsRecieved"));  
		FeedBack.setCellValueFactory(new PropertyValueFactory<GradeData,String>("FeedBack"));
		
		AssChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if(newValue != null) {
	            ChooseAssLabel.setText(newValue); // Update label text with selected assignment name
	        }
	    });
		
		try {
            loadDataFromDatabase();
        } catch (SQLException e) {
            showAlert("Error", "Database Error", "An error occurred while loading data from the database.");
        }
		try {
			loadCoursesIntoChoiceBox();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadAssignmentsIntoChoiceBox();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void loadCoursesIntoChoiceBox() throws SQLException {
	    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	    String databaseUser = "GradeMaster";
	    String databasePassword = "Justice_League";

	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sqlCourses = "SELECT course_name FROM courses";
	        try (PreparedStatement statementCourses = connection.prepareStatement(sqlCourses)) {
	            ResultSet resultSetCourses = statementCourses.executeQuery();
	            ObservableList<String> coursesList = FXCollections.observableArrayList();
	            while (resultSetCourses.next()) {
	                String courseName = resultSetCourses.getString("course_name");
	                coursesList.add(courseName);
	            }
	            CourseChoiceBox.setItems(coursesList);
	        }
	    }
	}

	private void loadAssignmentsIntoChoiceBox() throws SQLException {
	    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	    String databaseUser = "GradeMaster";
	    String databasePassword = "Justice_League";

	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sqlAssignments = "SELECT assignment_name FROM assignments";
	        try (PreparedStatement statementAssignments = connection.prepareStatement(sqlAssignments)) {
	            ResultSet resultSetAssignments = statementAssignments.executeQuery();
	            ObservableList<String> assignmentsList = FXCollections.observableArrayList();
	            while (resultSetAssignments.next()) {
	                String assignmentName = resultSetAssignments.getString("assignment_name");
	                assignmentsList.add(assignmentName);
	            }
	            AssChoiceBox.setItems(assignmentsList);
	        }
	    }
	}
	
	@FXML
	void CreateAss(ActionEvent event) {
		String AssText = AssTextField.getText().trim();
	    String PointsPossText= PointsPossTextField.getText().trim();
	    
	    if (AssText.isEmpty()&& PointsPossText.isEmpty()) {
	        showAlert("Error", "Empty Fields", "Please enter Assignment Name and Points Possible");
	        return;
	    }    
	    
	    if (AssText.isEmpty()) {
	        showAlert("Error", "Empty Fields", "Please enter Assignment Name");
	        return;
	    
	    }    
	    if (PointsPossText.isEmpty()) {
		        showAlert("Error", "Empty Fields", "Please enter Points Possible");
		        return;
	    }
	    try {
	    	// parse the course num
			int PointsPoss = Integer.parseInt(PointsPossText);
			// create ClassData obj with user input
			AssData assData = new AssData(AssText, PointsPoss);

			// Add the class data to the table view
			ObservableList<AssData> assDatas = TableView1.getItems();
			assDatas.add(assData);
			TableView1.setItems(assDatas);
			saveAssToDatabase(AssText, PointsPoss);
	    } catch (NumberFormatException e) {
	        showAlert("Error", "Invalid Number", "Please enter a valid Points Possible Number."); 
	    }
	    try {
			loadAssignmentsIntoChoiceBox();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	
	@FXML
	void RemoveAss(ActionEvent event) {
	    if (TableView1.getItems().isEmpty()) {
	        showAlert("Error", "TableView1 is empty", "Please add items to Table before removing.");
	        return;
	    }

	    int selectedID = TableView1.getSelectionModel().getSelectedIndex();
	    if (selectedID == -1) {
	        showAlert("Error", "No item selected", "Please select an Assignment from the Table before removing.");
	        return;
	    }

	    AssData selectedAss = TableView1.getItems().get(selectedID);
	    String assName = selectedAss.getAss();

	    deleteAssFromDatabase(assName);

	    TableView1.getItems().remove(selectedID);
	}
	@FXML
	void InputGrade(ActionEvent event) {
	    String selectedAssignment = AssChoiceBox.getValue();
	    if (selectedAssignment == null) {
	        showAlert("Error", "No Assignment Selected", "Please select an assignment.");
	        return;
	    }

	    String pointsReceivedText = PointsRecievedTextField.getText().trim();
	    String feedbackText = FeedBackTextField.getText().trim();

	    if (pointsReceivedText.isEmpty() || feedbackText.isEmpty()) {
	        showAlert("Error", "Empty Fields", "Please enter points received and feedback.");
	        return;
	    }

	    int pointsReceived;
	    try {
	        pointsReceived = Integer.parseInt(pointsReceivedText);
	    } catch (NumberFormatException e) {
	        showAlert("Error", "Invalid Number", "Please enter a valid points received number.");
	        return;
	    }

	    try {
	        // Get the assignment ID from the database based on the selected assignment name
	        int assignmentId = getAssignmentId(selectedAssignment);
	        if (assignmentId == -1) {
	            showAlert("Error", "Assignment Not Found", "Selected assignment not found in the database.");
	            return;
	        }

	        // Insert the grade into the database
	        saveGradeToDatabase(pointsReceived, feedbackText, assignmentId);

	        // Refresh TableView or do any other necessary UI updates
	        loadDataFromDatabase(); // Assuming this method refreshes the TableView

	    } catch (SQLException e) {
	        e.printStackTrace();
	        showAlert("Error", "Database Error", "An error occurred while saving the grade to the database.");
	    }
	}
	
	private int getAssignmentId(String assignmentName) throws SQLException {
	    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	    String databaseUser = "GradeMaster";
	    String databasePassword = "Justice_League";

	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sql = "SELECT assignment_id FROM assignments WHERE assignment_name = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, assignmentName);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getInt("assignment_id");
	            }
	        }
	    }
	    return -1; // Return -1 if assignment is not found
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

	    GradeData selectedGrade = TableView2.getItems().get(selectedID);
	    int pointsReceived = selectedGrade.getPointsRecieved();

	    deleteGradeFromDatabase(pointsReceived);

	    TableView2.getItems().remove(selectedID);
	}


	private void showAlert(String title, String header, String content) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle(title);
	    alert.setHeaderText(header);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	
	// method for saving the class to the database
			private void saveAssToDatabase(String Ass, int PointsPoss) {
				// database connection creds
				String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
				String databaseUser = "GradeMaster";
				String databasePassword = "Justice_League";

				//try connection
				try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
					//proper values within the database
					String sql = "INSERT INTO assignments (`assignment_name`, `grade_range`) VALUES (?, ?)";
					try (PreparedStatement statement = connection.prepareStatement(sql)) {
						statement.setString(1, Ass);
						statement.setInt(2, PointsPoss);

						int rowsInserted = statement.executeUpdate();
						if (rowsInserted > 0) {
							System.out.println("Ass inserted successfully!");
						} else {
							System.out.println("Failed to insert Ass");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					showAlert("Error", "Database Error", "An error occurred while saving the course to the database.");
				}
			}

			// adding getter methods so that DBClassCreationFallBack2 can access this class
			public TextField getAssTextField() {
				return AssTextField;
			}

			public TextField getPointsPossTextField() {
				return PointsPossTextField;
			}
			
			// method for saving the class to the database
			private void saveGradeToDatabase(int pointsReceived, String feedback, int assignmentId) throws SQLException {
			    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
			    String databaseUser = "GradeMaster";
			    String databasePassword = "Justice_League";

			    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
			        String sql = "INSERT INTO grades (points_recieved, feedback, assignment_id) VALUES (?, ?, ?)";
			        try (PreparedStatement statement = connection.prepareStatement(sql)) {
			            statement.setInt(1, pointsReceived);
			            statement.setString(2, feedback);
			            statement.setInt(3, assignmentId);

			            int rowsInserted = statement.executeUpdate();
			            if (rowsInserted > 0) {
			                System.out.println("Grade inserted successfully!");
			            } else {
			                System.out.println("Failed to insert grade");
			            }
			        }
			    }
			}			
			
			private void loadDataFromDatabase() throws SQLException {
			    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
			    String databaseUser = "GradeMaster";
			    String databasePassword = "Justice_League";

			    try (Connection connection1 = DriverManager.getConnection(url, databaseUser, databasePassword)) {
			        // Load assignments
			        String sqlAssignments = "SELECT assignment_name, grade_range FROM assignments";
			        try (PreparedStatement statementAssignments = connection1.prepareStatement(sqlAssignments)) {
			            ResultSet resultSetAssignments = statementAssignments.executeQuery();
			            ObservableList<AssData> assDataList = TableView1.getItems();
			            while (resultSetAssignments.next()) {
			                String assignmentName = resultSetAssignments.getString("assignment_name");
			                int gradeRange = resultSetAssignments.getInt("grade_range");
			                assDataList.add(new AssData(assignmentName, gradeRange));
			            }
			            TableView1.setItems(assDataList);
			        }

			        // Load grades
			        String sqlGrades = "SELECT points_recieved, feedback FROM grades";
			        try (PreparedStatement statementGrades = connection1.prepareStatement(sqlGrades)) {
			            ResultSet resultSetGrades = statementGrades.executeQuery();
			            ObservableList<GradeData> gradeDataList = TableView2.getItems();
			            while (resultSetGrades.next()) {
			                int pointsReceived = resultSetGrades.getInt("points_recieved");
			                String feedback = resultSetGrades.getString("feedback");
			                gradeDataList.add(new GradeData(pointsReceived, feedback));
			            }
			            TableView2.setItems(gradeDataList);
			        }
			    }
			}
			
			
			private void deleteAssFromDatabase(String assName) {
			    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
			    String databaseUser = "GradeMaster";
			    String databasePassword = "Justice_League";

			    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
			        String sql = "DELETE FROM assignments WHERE assignment_name = ?";
			        try (PreparedStatement statement = connection.prepareStatement(sql)) {
			            statement.setString(1, assName);

			            int rowsDeleted = statement.executeUpdate();
			            if (rowsDeleted > 0) {
			                System.out.println("Assignment deleted successfully!");
			            } else {
			                System.out.println("Failed to delete assignment");
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        showAlert("Error", "Database Error", "An error occurred while deleting the assignment from the database.");
			    }
			}

			// adding getter methods so that DBClassCreationFallBack2 can access this class
			public TextField getPointsRecieveTextField() {
				return PointsRecievedTextField;
			}

			public TextField getFeedBackextField() {
				return FeedBackTextField;
			}
			
			private void deleteGradeFromDatabase(int pointsReceived) {
			    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
			    String databaseUser = "GradeMaster";
			    String databasePassword = "Justice_League";

			    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
			        String sql = "DELETE FROM grades WHERE points_recieved = ?";
			        try (PreparedStatement statement = connection.prepareStatement(sql)) {
			            statement.setInt(1, pointsReceived);

			            int rowsDeleted = statement.executeUpdate();
			            if (rowsDeleted > 0) {
			                System.out.println("Grade deleted successfully!");
			            } else {
			                System.out.println("Failed to delete grade");
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        showAlert("Error", "Database Error", "An error occurred while deleting the grade from the database.");
			    }
			}

			
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherMenuScene(e);
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
	    	PointsPossLabel.setTextFill(paint2);
	    	PointsRecievedLabel.setTextFill(paint2);
	    	FeedBackLabel.setTextFill(paint2);
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
	    	PointsPossLabel.setTextFill(paint2);
	    	PointsRecievedLabel.setTextFill(paint2);
	    	FeedBackLabel.setTextFill(paint2);
	    	
	    }

}
