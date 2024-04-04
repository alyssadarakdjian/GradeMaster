package application;

import java.io.IOException;
import application.SwitchSceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class GraphController {

	public void barChart() {
		
	}
	
	public void pieChart() {
		
	}
	
	public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTempScene(e);
	}
}
