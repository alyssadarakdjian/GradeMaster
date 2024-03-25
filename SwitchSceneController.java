package application;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SwitchSceneController {

	//private static final boolean isLightMode = true;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void switchToLoginScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToStudentGradeBook(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentGradeBookScene.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		StudentGradeBookSceneController studentGradeBookSceneController = loader.getController();
		if(Controller.isLightMode) {
			studentGradeBookSceneController.setLightMode();
			}else {
				studentGradeBookSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherGradeBook(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherGradeBookScene.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		TeacherGradeBookSceneController teacherGradeBookSceneController = loader.getController();
		if(Controller.isLightMode) {
			teacherGradeBookSceneController.setLightMode();
			}else {
				teacherGradeBookSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherClassCreate(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherClassCreationScene.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		TeacherClassCreationSceneController teacherClassCreationSceneController = loader.getController();
		if(Controller.isLightMode) {
			teacherClassCreationSceneController.setLightMode();
			}else {
				teacherClassCreationSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToTempScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		MenuSceneController menuSceneController = loader.getController();
		if(Controller.isLightMode) {
			menuSceneController.setLightMode();
			}else {
				menuSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

