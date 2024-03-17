package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.Parent;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("LearnLink");
		primaryStage.setScene(new Scene(root, 900, 500));
		primaryStage.show();
		
	}
		
		
	public static void main(String[] args) {
		launch(args);
}
}