package application;

import application.SwitchSceneController;


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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentGradeBookSceneController implements Initializable{
	@FXML
    private TableColumn<StudentGradeData, String> Ass;

    @FXML
    private TableColumn<StudentGradeData, Integer> PointsPoss;

    @FXML
    private TableColumn<StudentGradeData, Integer> PointsRec;
    
    @FXML
    private TableColumn<StudentGradeData, String> FeedBack;

    @FXML
    private Label LetterLabel;

    @FXML
    private Label PercentageLabel;

    @FXML
    private TableView<StudentGradeData> TableView;

    @FXML
    private Label TotalGradeLable;
    
    @FXML
    private TableColumn<?,?> ButtonColumn;

    @FXML
    private Button btnBack;
    
    @FXML
    private Button refreshButton;

    @FXML
    private Button btnMode;

    @FXML
    private ScrollPane parent;
    
    @FXML
    private ImageView imgMode;

  private int userId = Controller.userId;

    private boolean isLightMode = true;

    public void changeMode(ActionEvent event) {
    	isLightMode = !isLightMode;
    	if(isLightMode) {
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
    	Paint paint2 = Paint.valueOf("black");
    	TotalGradeLable.setTextFill(paint2);
    	PercentageLabel.setTextFill(paint2);
    	LetterLabel.setTextFill(paint2);
    	Ass.setStyle("black");
    	
    	
    	
  
    }
    
 public void setDarkMode() {
 	parent.getStylesheets().remove("styles/lightMode.css");
    	parent.getStylesheets().add("styles/darkMode.css");
    	Image image = new Image("img/light.png");
    	imgMode.setImage(image);
    //	imgMode.setStyle("-fx-shape:round");
    	Paint paint = Paint.valueOf("black");
    	Paint paint2 = Paint.valueOf("white");
    	btnBack.setTextFill(paint);
    	TotalGradeLable.setTextFill(paint2);
    	PercentageLabel.setTextFill(paint2);
    	LetterLabel.setTextFill(paint2);
    	
    }
			
	@Override
    public void initialize(URL url, ResourceBundle ResourceBundel) {
        Ass.setCellValueFactory(new PropertyValueFactory<StudentGradeData, String>("Ass"));
        PointsPoss.setCellValueFactory(new PropertyValueFactory<StudentGradeData, Integer>("PointsPoss"));
        PointsRec.setCellValueFactory(new PropertyValueFactory<StudentGradeData, Integer>("PointsRec"));
        FeedBack.setCellValueFactory(new PropertyValueFactory<StudentGradeData, String>("feedback"));
        
        refreshButton.setOnAction(this::handleRefresh);
        try {
            loadDataFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        calculateTotalGrade();
    }
	
	private void handleRefresh(ActionEvent event) {
	    try {
	        loadDataFromDatabase(); // Reload data from the database
	        calculateTotalGrade(); // Recalculate total grade
	    } catch (SQLException e) {
	        e.printStackTrace();
	        showAlert("Error", "Database Error", "An error occurred while loading data from the database.");
	    }
	}

    private void loadDataFromDatabase() throws SQLException {
        System.out.println(userId);
        String url = "jdbc:mysql://grademaster-mysql-server.mysql.database.azure.com:3306/GradeMaster";
        String databaseUser = "GradeMaster";
        String databasePassword = "Justice_League";

        try (Connection connection1 = DriverManager.getConnection(url, databaseUser, databasePassword)) {
            String sql = "SELECT assignment_name, grade_range, points_recieved, feedback FROM assignments INNER JOIN grades ON assignments.assignment_id = grades.assignment_id WHERE student_id = ?";
            try (PreparedStatement statement = connection1.prepareStatement(sql)) {
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                ObservableList<StudentGradeData> StudentDataList = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    int pointsPoss = resultSet.getInt("grade_range");
                    int pointsRec = resultSet.getInt("points_recieved");
                    String ass = resultSet.getString("assignment_name");
                    String feedback = resultSet.getString("feedback");
                    StudentDataList.add(new StudentGradeData(ass, pointsPoss, pointsRec, feedback));
                    TableView.setItems(StudentDataList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "Database Error", "An error occurred while loading data from the database.");
            }
        }
    }

    private void calculateTotalGrade() {
        int totalPointsRec = 0;
        int totalPointsPoss = 0;

        ObservableList<StudentGradeData> list = TableView.getItems();

        for (StudentGradeData data : list) {
            totalPointsRec += data.getPointsRec();
            totalPointsPoss += data.getPointsPoss();
        }

        if (totalPointsPoss == 0) {
            showAlert("Error", "Invalid Data", "Total points possible cannot be zero.");
            return;
        }

        double percentage = (double) totalPointsRec / totalPointsPoss * 100;

        String letterGrade = calculateLetterGrade(percentage);

        PercentageLabel.setText(String.format("%.2f%%", percentage));
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

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void backButton(ActionEvent e) throws IOException {
        SwitchSceneController switchSceneController = new SwitchSceneController();
        switchSceneController.switchToStudentMenuScene(e);
    }
	
}
