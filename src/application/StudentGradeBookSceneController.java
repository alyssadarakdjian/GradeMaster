package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentGradeBookSceneController implements Initializable{
	@FXML
	private TableView<StudentGradeData> TableView;
	
	@FXML
	private TableColumn<StudentGradeData, String> Ass;
	
	@FXML
	private TableColumn<StudentGradeData, String>Weight;
	
	@FXML
	private TableColumn<StudentGradeData, Character>Grade;
	
	@FXML
	private TableColumn<StudentGradeData, Integer>Percent;
	
	@FXML
	private TableColumn<StudentGradeData, String>Feedback ;
	
	@FXML
    private Label PercentageLabel;

    @FXML
    private Label LetterLabel;

	
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
	
	
	
}
