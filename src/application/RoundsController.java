package application;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoundsController {
	
	@FXML
	Label roundMines;
	@FXML
	Label roundClicks;
	@FXML
	Label roundStartingTime;
	@FXML
	Label roundEndingTime;
	@FXML
	Label roundWinner;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Queue<String> wonList = new LinkedList<>();
	
	public void showResults(ActionEvent event) {
		roundMines.setText("Mines: " + Main.totalMines);
		roundClicks.setText("Good Clicks: " + Main.goodClicks);
		roundStartingTime.setText("Starting Time: " + Main.startingTime);
		roundEndingTime.setText("Ending Time: " + Main.endingTime);

		// Iterate weWon queue and display the correct value
		// It has boolean, but we want a string
		Iterator<Boolean> iterator = Main.weWon.iterator();
		while(iterator.hasNext()) {
			// This condition is set because if we click again at show
			// results, we don't want to keep adding data to wonList.
			if (wonList.size()<Main.weWon.size()) {
				if (iterator.next() == true) {
					wonList.add("You");
				}
				else {
					wonList.add("Game");
				}
			}
			else {
				break; // End while
			}
		}
		roundWinner.setText("Winner: " + wonList);
	}
	
	public void playAgain(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Starting.fxml"));
		root = loader.load();
		
		Controller controller = loader.getController();
		controller.startProgram(event);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
