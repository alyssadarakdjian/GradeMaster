package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.SwitchSceneController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GraphController implements Initializable {
	
	@FXML
    private URL location;
	
    @FXML
    private ResourceBundle resources;
	
	@FXML
    private Button btnMode;
	
	@FXML
    private Button btnBack;
	
	@FXML
    private Label courseLabel;

    @FXML
    private ImageView imgMode;

    @FXML
    private AnchorPane parent;
    
    @FXML 
    private ChoiceBox<String> CourseChoiceBox;
    
    @FXML
    private BarChart<String, Integer> avgChart;
    
    @FXML
    private NumberAxis yAxis = new NumberAxis(0, 110, 10);
    
    @FXML
    private CategoryAxis xAxis = new CategoryAxis();
    

    GraphData[] graphDataList = new GraphData[20];
    
    GraphGradeData[] graphGradeDataList = new GraphGradeData[50];
    
    private int userId = Controller.userId;
    

    
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		CourseChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if(newValue != null) {
	            try {
	                // Fetch courseID based on the selected course name
	                int courseId = getCourseIdFromDatabase(newValue);
	                // Load assignments for the selected course
	                //System.out.println("ClassID: " + courseId);
	                loadAssignmentsForCourse(courseId);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                showAlert("Error", "Database Error", "An error occurred while fetching course information.");
	            }
	        }
	    });
		try {
			loadCoursesIntoChoiceBox(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void printAssignmentsInGraph() {
				
		avgChart.getData().clear();
		
		XYChart.Series<String, Integer> avgSeries1 = new XYChart.Series();
		avgSeries1.setName("Your Grade");
		XYChart.Series<String, Integer> avgSeries2 = new XYChart.Series();
		avgSeries2.setName("Class Avg.");
		
		int i = 0;
		
		while ((graphDataList[i] != null) && (graphDataList[i].isItValid() == true)) {
			
			avgSeries1.getData().add(new XYChart.Data(graphDataList[i].getAss(), getUserGrade(graphDataList[i].getAssID(), graphDataList[i].getPointsPoss())));
			avgSeries2.getData().add(new XYChart.Data(graphDataList[i].getAss(), getClassAvg(graphDataList[i].getAssID(), graphDataList[i].getPointsPoss())));
			
			i++;
			
		}
		
		avgChart.getData().addAll(avgSeries1, avgSeries2);
					
				
	}
	
	
	
	private int getUserGrade(int assID, int pointsPoss) {
		
		int i = 0;
		while ((graphGradeDataList[i] != null) && (graphGradeDataList[i].isItValid() == true)) {
			
			if ((graphGradeDataList[i].isItYours() == true) && (graphGradeDataList[i].getAssID() == assID)) {
				
				if (pointsPoss != 0) {
					return ((graphGradeDataList[i].getPointsRecieved() * 100) / pointsPoss);
				}
				
			}
			
			i++;
		}
		
		return 0;
		
	}
	
	
	
	private int getClassAvg(int assID, int pointsPoss) {
		
		int pointsTotal = 0;
		int gradeCount = 0;
		
		int i = 0;
		while ((graphGradeDataList[i] != null) && (graphGradeDataList[i].isItValid() == true)) {
			
			if (graphGradeDataList[i].getAssID() == assID) {
				
				pointsTotal = pointsTotal + graphGradeDataList[i].getPointsRecieved();
				gradeCount++;
				
			}
			
			i++;
		}
		
		if (gradeCount != 0 && pointsPoss != 0) {
			return (((pointsTotal * 100) / gradeCount) / pointsPoss);
		} else {
			return 0;
		}
		
	}
	
	
	
	private void loadCoursesIntoChoiceBox(int userId) throws SQLException {
	    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	    String databaseUser = "GradeMaster";
	    String databasePassword = "Justice_League";

	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sqlCourses = "SELECT course_name FROM courses";
	        try (PreparedStatement statementCourses = connection.prepareStatement(sqlCourses)) {
	            //statementCourses.setInt(1, userId); // Set the user_id as parameter
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
	
	
	
	private int getCourseIdFromDatabase(String courseName) throws SQLException {
	    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	    String databaseUser = "GradeMaster";
	    String databasePassword = "Justice_League";

	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sql = "SELECT course_id FROM courses WHERE course_name = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, courseName);
	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getInt("course_id");
	            } else {
	                // Course not found
	                return -1;
	            }
	        }
	    }
	}
	
	
	
	private void loadAssignmentsForCourse(int courseId) throws SQLException {
	    String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
	    String databaseUser = "GradeMaster";
	    String databasePassword = "Justice_League";

	    // Get assignment data from database
	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sqlAssignments = "SELECT assignment_name, assignment_id, grade_range FROM assignments WHERE course_id = ?";
	        try (PreparedStatement statementAssignments = connection.prepareStatement(sqlAssignments)) {
	            statementAssignments.setInt(1, courseId);
	            ResultSet resultSetAssignments = statementAssignments.executeQuery();
	            //graphDataList.clear(); // Clear existing data
	            int i = 0;
	            while (resultSetAssignments.next()) {
	                String assignmentName = resultSetAssignments.getString("assignment_name");
	                int assignmentID = resultSetAssignments.getInt("assignment_id");
	                int gradeRange = resultSetAssignments.getInt("grade_range");
	                //System.out.println(assignmentName + ", " + assignmentID + ", " + gradeRange);
	                this.graphDataList[i] = new GraphData(assignmentName, assignmentID, gradeRange);
	                i++;
	                //graphDataList.add(new GraphData(assignmentName, assignmentID, gradeRange));
	            }
	            while (this.graphDataList[i] != null) {
	            	this.graphDataList[i].makeInvalid();
	            	i++;
	            }
	        }
	    }
	    
	    // Get grade data from database
	    try (Connection connection = DriverManager.getConnection(url, databaseUser, databasePassword)) {
	        String sqlAssignments = "SELECT student_id, assignment_id, points_recieved FROM grades";
	        try (PreparedStatement statementAssignments = connection.prepareStatement(sqlAssignments)) {
	            //statementAssignments.setInt(1, 1);
	            ResultSet resultSetAssignments = statementAssignments.executeQuery();
	            //graphGradeDataList.clear(); // Clear existing data
	            int i = 0;
	            while (resultSetAssignments.next()) {
	                int studentID = resultSetAssignments.getInt("student_id");
	                int assignmentID = resultSetAssignments.getInt("assignment_id");
	                int pointsRecieved = resultSetAssignments.getInt("points_recieved");
	                this.graphGradeDataList[i] = new GraphGradeData(studentID, assignmentID, pointsRecieved);
	                i++;
	            }
	            while (this.graphGradeDataList[i] != null) {
	            	this.graphGradeDataList[i].makeInvalid();
	            	i++;
	            }
	        }
	    }
	    
	    printAssignmentsInGraph();
	    
	}
	
	
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToStudentMenuScene(e);
	}
	
	
	
	//private boolean isLightMode = true;

    public void changeMode(ActionEvent event) {
    	Controller.setLightMode(!Controller.isLightMode());
    	if(Controller.isLightMode()) {
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
    	
    	paint = Paint.valueOf("black");
    	yAxis.setTickLabelFill(paint);
    	xAxis.setTickLabelFill(paint);
    	courseLabel.setTextFill(paint);
    	Controller.setLightMode(true);
    	
    }
    
    public void setDarkMode() {
    	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    //	imgMode.setStyle("-fx-shape:round");
    	Paint paint = Paint.valueOf("black");
    	btnBack.setTextFill(paint);
    	
    	paint = Paint.valueOf("white");
    	yAxis.setTickLabelFill(paint);
    	xAxis.setTickLabelFill(paint);
    	courseLabel.setTextFill(paint);
    	Controller.setLightMode(false);
    	
    }
    
    
    
    private void showAlert(String title, String header, String content) {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle(title);
	    alert.setHeaderText(header);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
}
