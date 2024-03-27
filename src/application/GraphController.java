package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class GraphController implements Initializable {

	@FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    public void backButton(ActionEvent e) throws IOException {
		SwitchSceneController switchSceneController = new SwitchSceneController();
		switchSceneController.switchToTempScene(e);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		XYChart.Series<String, Integer> barSeries1 = new XYChart.Series();
		barSeries1.setName("Your Grade");
		barSeries1.getData().add(new XYChart.Data("Ass. 1", 75));
		barSeries1.getData().add(new XYChart.Data("Ass. 2", 81));
		barSeries1.getData().add(new XYChart.Data("Ass. 3", 64));
		barSeries1.getData().add(new XYChart.Data("Ass. 4", 100));
		barSeries1.getData().add(new XYChart.Data("Ass. 5", 94));
		
		XYChart.Series<String, Integer> barSeries2 = new XYChart.Series();
		barSeries2.setName("Class Avg.");
		barSeries2.getData().add(new XYChart.Data("Ass. 1", 78));
		barSeries2.getData().add(new XYChart.Data("Ass. 2", 75));
		barSeries2.getData().add(new XYChart.Data("Ass. 3", 80));
		barSeries2.getData().add(new XYChart.Data("Ass. 4", 79));
		barSeries2.getData().add(new XYChart.Data("Ass. 5", 96));
		
		barChart.getData().addAll(barSeries1, barSeries2);
		
		
		XYChart.Series<String, Double> lineSeries = new XYChart.Series<String, Double>();
		lineSeries.setName("Your Grade");
		lineSeries.getData().add(new XYChart.Data("Ass. 1", 75));
		lineSeries.getData().add(new XYChart.Data("Ass. 2", 78));
		lineSeries.getData().add(new XYChart.Data("Ass. 3", 73.33));
		lineSeries.getData().add(new XYChart.Data("Ass. 4", 80));
		lineSeries.getData().add(new XYChart.Data("Ass. 5", 82));
		
		lineChart.getData().add(lineSeries);
		
	}

}
