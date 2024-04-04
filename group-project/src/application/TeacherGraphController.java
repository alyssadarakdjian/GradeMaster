package application;

import java.io.IOException;

import javafx.event.ActionEvent;

public class TeacherGraphController {
public void barChart() {
		
	}
	
	public void pieChart() {
		
	}
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTeacherMenuScene(e);
	}
}
