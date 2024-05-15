package graph;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class GraphD extends Application {
	protected List<Integer> data ;
    public GraphD(List<Integer> data) {
		this.data = data;
	}
    public GraphD() {
        // Default constructor
    }
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	@Override
    public void start(Stage primaryStage) {
        // Create the X and Y axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("ECG");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Data");
        for (int i = 0; i < getData().size(); i++) {
            series.getData().add(new XYChart.Data<>(i, getData().get(i)));
        }

        lineChart.getData().add(series);

        // Create the scene
        Scene scene = new Scene(lineChart, 800, 600);

        // Set the stage
        primaryStage.setTitle("Integer Line Graph");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void StartGraph(List<Integer> datas ) {
    	this.setData(datas);
        launch();
    }
}