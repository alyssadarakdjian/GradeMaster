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
		root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToStudentGradeBook(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StudentGradeBookScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherGradeBook(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TeacherGradeBookScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherClassCreate(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TeacherClassCreationScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToGraphScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Graphs.fxml"));
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
		root = FXMLLoader.load(getClass().getResource("LoginCreateMain.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToStudentMenuScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StudentMenuScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherMenuScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TeacherMenuScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToTeacherGraphScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("TeacherGraphs.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

