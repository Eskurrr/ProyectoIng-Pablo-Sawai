package graph;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Graph extends Application {
    private static final AtomicInteger dataIndex = new AtomicInteger(0);
    private static final XYChart.Series<Number, Number> series = new XYChart.Series<>();
    protected int stop = 0 ;
    public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	@Override
    public void start(Stage stage) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(null);
        yAxis.setLabel(null);
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(null);
        lineChart.getData().add(series);

        Button stopButton = createStopButton();
        StackPane root = new StackPane();
        root.getChildren().addAll(lineChart, stopButton);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("ECG Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public int addData(double newData) {
        series.getData().add(new XYChart.Data<>(dataIndex.incrementAndGet(), newData));
        return getStop();
    }

    private Button createStopButton() {
        Button stopButton = new Button();
        Circle circle = new Circle(25, Color.RED);
        Text buttonText = new Text("STOP");
        buttonText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        buttonText.setFill(Color.WHITE);
        stopButton.setGraphic(new StackPane(circle, buttonText));
        stopButton.setOnAction(event -> {
            // Code to handle the stop action
        	setStop(11);
            System.out.println("STOP button pressed." + getStop());
        });
        stopButton.setLayoutX(740); // Adjust the position of the button
        stopButton.setLayoutY(520);
        return stopButton;
    }

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }
}