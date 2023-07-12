package application;
	
import java.util.LinkedList;
import java.util.Queue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	// This will store the data of the last 5 games
	// We use queues so that we can remove the head
	// when game number 6 comes and shift left the rest
	public static Queue<Integer> totalMines = new LinkedList<>();
	public static Queue<Integer> goodClicks = new LinkedList<>();
	public static Queue<Integer> startingTime = new LinkedList<>();
	public static Queue<Integer> endingTime = new LinkedList<>();
	public static Queue<Boolean> weWon = new LinkedList<>();
	
	@Override
	public void start(Stage primaryStage) {
		try {	
			Parent root = FXMLLoader.load(getClass().getResource("Starting.fxml"));
			Scene scene = new Scene(root, 620, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("MediaLab Minesweeper");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
