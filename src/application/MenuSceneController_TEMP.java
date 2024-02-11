package application;

import java.io.IOException;

import application.SwitchSceneController;
import javafx.event.ActionEvent;

public class MenuSceneController_TEMP {

	public void TeacherGradeBook(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherGradeBook(e);
	}
	
	public void StudentGradeBook(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToStudentGradeBook(e);
	}
	
	public void TeacherClassCreate(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherClassCreate(e);
	}
	
}
