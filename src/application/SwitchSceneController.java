package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SwitchSceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	public void switchToLoginScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		root = loader.load();
		Controller Controller = loader.getController();
		if(Controller.isLightMode()) {
			Controller.setLightMode();
			}else {
				Controller.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToStudentGradeBook(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentGradeBookScene.fxml"));
		root = loader.load();
		StudentGradeBookSceneController studentGradeBookSceneController = loader.getController();
		if(Controller.isLightMode()) {
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
		if(Controller.isLightMode()) {
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
		if(Controller.isLightMode()) {
			teacherClassCreationSceneController.setLightMode();
			}else {
				teacherClassCreationSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToGraphScene(ActionEvent event) throws IOException {
		//root = FXMLLoader.load(getClass().getResource("Graphs.fxml"));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Graphs.fxml"));
				root = loader.load();
				//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
				GraphController graphController = loader.getController();
				if(Controller.isLightMode()) {
					graphController.setLightMode();
					}else {
						graphController.setDarkMode();
					}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/*
	public void switchToTempScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	*/
	
	public void switchToCreateLoginScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginCreateMain.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		LogController logController = loader.getController();
		if(Controller.isLightMode()) {
			logController.setLightMode();
			}else {
				logController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToStudentMenuScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentMenuScene.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		StudentMenuSceneController studentMenuSceneController = loader.getController();
		if(Controller.isLightMode()) {
			studentMenuSceneController.setLightMode();
			}else {
				studentMenuSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherMenuScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherMenuScene.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		TeacherMenuSceneController teacherMenuSceneController = loader.getController();
		if(Controller.isLightMode()) {
			teacherMenuSceneController.setLightMode();
			}else {
				teacherMenuSceneController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherGraphScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherGraphs.fxml"));
		root = loader.load();
		//root = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
		TeacherGraphController teacherGraphController = loader.getController();
		if(Controller.isLightMode()) {
			teacherGraphController.setLightMode();
			}else {
				teacherGraphController.setDarkMode();
			}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

