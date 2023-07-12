package application;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	@FXML
    private AnchorPane topAnchor;
	@FXML
	private AnchorPane grid;
	@FXML
	private MenuBar myMenuBar;
	private Text[][] textList;
	private Rectangle[][] tileList;
	private Text[] text1List = new Text[3];
	private int dimension;
	private int clicks;
	private int flags;
	private IntegerProperty timeSeconds;
	private boolean won;
	private boolean lost;
	private int clicksOpened;
	private int solutionTime;

	// We make startString public static because we want to change it
	// from another controller class. For instance, in LoadController.
	public static String startString = new String("level_1_example");

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Timeline timeline = new Timeline();
	
	@FXML
	protected void initialize() {
		
		
		int[] input = ReadInput.readInput(startString);
		// If the first value is 0, then an exception occurred, so return
		if (input[0] == 0) {
			return;
		}
		int level = input[0];
		int mines = input[1];
		int time = input[2];
		int hyperMine = input[3];
		this.dimension = input[4];
		this.clicks = 0;
		this.flags = 0;
		timeSeconds = new SimpleIntegerProperty(time);
		solutionTime = time;
		
		int[][] board = CreateBoard.createBoard(dimension, mines, hyperMine);
		
		// Initialize end of game booleans and good clicks counter
		won = false;
		lost = false;
		clicksOpened = 0;
		
		
		GridPane stats = new GridPane();
		for (int k=0; k<3; k++) {
			Rectangle tile1 = new Rectangle(200, 45);
            tile1.setX(k);
            tile1.setY(0);	            
            tile1.setFill(Color.BURLYWOOD);
            tile1.setStroke(Color.BLACK);
            Text text1 = new Text();
            if (k==0) text1.setText("Mines: " + mines);
            if (k==1) text1.setText("Flags: " + this.flags);
            if (k==2) {
            	text1.setText("Time: " + timeSeconds);
            	text1.textProperty().bind(timeSeconds.asString());
            }
            text1.setFont(Font.font(30));
            stats.add(new StackPane(tile1, text1), k, 0);
            text1List[k] = text1;
		}
		topAnchor.setStyle("-fx-background-color: grey;");
		topAnchor.getChildren().add(stats);
		
		Stage stage = new Stage();
		Label label = new Label("Hi");
		Scene scene = new Scene(label);
		scene.setFill(Color.AZURE);
		stage.setScene(scene);
		
		
		timeSeconds.set(time);
        
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(time+1),
                new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        // If the time ends, we lose.
        timeline.setOnFinished((event) -> {
        	lost = true;
        	Main.totalMines.add(mines);
        	Main.goodClicks.add(clicksOpened);
        	Main.startingTime.add(time);
        	Main.endingTime.add(0);
        	Main.weWon.add(false);
        });
		     
		GridPane gameBoard = new GridPane();
		AnchorPane.setTopAnchor(gameBoard, 0.0);
		AnchorPane.setBottomAnchor(gameBoard, 0.0);
		AnchorPane.setLeftAnchor(gameBoard, 0.0);
		AnchorPane.setRightAnchor(gameBoard, 0.0);
		
		// This list contains the text displayed to the grid.
		// It's needed because we'll want to traverse it and 
		// make changes to it later (suck as make text visible).
		this.textList = new Text[dimension][dimension];
		
		// Same as above, we'll want to traverse the tile list
		// to make changes to a tile (suck as change color)
		this.tileList = new Rectangle[dimension][dimension];
		
		int width = 600;
//		int height = 317;
		int size = width / dimension;
	    for (int row = 0; row < dimension; row++) {
	        for (int col = 0; col < dimension; col++) {

	            Rectangle tile = new Rectangle(size, size);
	            tile.setX(row);
	            tile.setY(col);	            
	            tile.setFill(Color.BURLYWOOD);
	            tile.setStroke(Color.BLACK);
	            Text text = new Text();
	            // 10 means it's a hyper-mine
	            if(board[row][col] == 10) {
	            	text.setText("H");
	            	text.setFont(Font.font(20));
	            	text.setFill(Color.VIOLET);
	            }
	            // 9 means it's a mine
	            else if (board[row][col] == 9) {
		            text.setText("X");
	            	text.setFont(Font.font(20));
	            	text.setFill(Color.RED);
	            }
	            else if (board[row][col] == 0) {
	            	text.setText("");
	            }
	            else {
	            	text.setText(Integer.toString(board[row][col]));
	            	}
	            text.setFont(Font.font(20));
	            text.setVisible(false);
	            textList[row][col] = text;
	            tileList[row][col] = tile;
            	gameBoard.add(new StackPane(tile, text), col, row);
            	
            	// Handle a mouse click
	            tile.setOnMouseClicked(event -> 
	            	{
	            		// Mouse left click
	            		if(event.getButton() == MouseButton.PRIMARY) {
	            			// If the tile is flagged, we can still left click it.
	            			// If it's 0 (empty) then we check it in the condition below
	            			if (tile.getFill() == Color.YELLOW && board[(int) tile.getX()][(int) tile.getY()] != 0) {
	            				this.flags--;
	            				text1List[1].setText("Flags: " + Integer.toString(flags));
	            			}
	            			// This checks if the value of the tile that was clicked is 0 (empty tile)
	            			if(board[(int) tile.getX()][(int) tile.getY()] == 0) {
	            				// if a hyper mine set the text visible, make it invisible because emptyField wants it that way
	            				text.setVisible(false);
	            				// reduce the amount of flags that were wrongly placed
	            				flags -= EmptyField.emptyField(tileList, textList, board, (int) tile.getX(), (int) tile.getY(), dimension, 0);
	            				text1List[1].setText("Flags: " + Integer.toString(flags));
	            				clicksOpened++;
	            			}
	            			// If a tile is medium purple or yellow green it means that solution was clicked or we won
	            			else if (tile.getFill() == Color.MEDIUMPURPLE || tile.getFill() == Color.YELLOWGREEN) {
	            				; // do nothing
	            			}
	            			// The tile that was clicked isn't 0
	            			else {
	            				text.setVisible(true);
	            				tile.setFill(Color.PURPLE);
	            				clicksOpened++;
	            			}
	            			this.clicks++;
	            			// The second condition was set because if we clicked on a tile
	            			// after we had won, then it would have counted as a win again
	            			// (Or if we clicked on a tile after we had lost)
	            			if (checkIfWon(mines, dimension) && won == false && lost == false) {
	            				won = true;
	            				Main.totalMines.add(mines);
	            				Main.goodClicks.add(clicksOpened);
	            				Main.weWon.add(true);
	            				// If it's 6 then remove head of each queue
	            				// because we only want to keep last 5
	            				if (Main.totalMines.size() == 6) {
	            					Main.totalMines.remove();
	            					Main.goodClicks.remove();
	            					Main.weWon.remove();
	            					Main.startingTime.remove();
	            					Main.endingTime.remove();
	            				}
	            				timeline.pause();
	            				Main.startingTime.add(time);
	            				Main.endingTime.add(timeSeconds.get());
	            				EndBoard.wonBoard(tileList, textList, dimension);
	            			}
	            			// If a mine was pressed, we lost. The last condition is set for the same reason as above
	            			if ((text.getText().toString() == "X" || text.getText().toString() == "H") && lost == false && won == false) {
	            				lost = true;
	            				Main.totalMines.add(mines);
	            				// We remove one because the click that was a mine wasn't a good one
	            				Main.goodClicks.add(clicksOpened-1);
	            				Main.weWon.add(false);
	            				// If it's 6 then remove head of each queue
	            				// because we only want to keep last 5
	            				if (Main.totalMines.size() == 6) {
	            					Main.totalMines.remove();
	            					Main.goodClicks.remove();
	            					Main.weWon.remove();
	            					Main.startingTime.remove();
	            					Main.endingTime.remove();
	            				}
	            				timeline.pause();
	            				Main.startingTime.add(time);
	            				Main.endingTime.add(timeSeconds.get());
	            				EndBoard.lostBoard(tileList, textList, dimension);
	            			}
	            		}
	            		// Mouse right click
	            		if(event.getButton() == MouseButton.SECONDARY) {
	            			// Check if hyper-mine was flagged in first 4 clicks
	            			if (clicks<4 && board[(int) tile.getX()][(int) tile.getY()] == 10 && tile.isVisible()) {
	            				hyperFill(tileList, textList, (int) tile.getX(), (int) tile.getY(), dimension);
	            				this.flags++;
	            				text1List[1].setText("Flags: " + Integer.toString(flags));
	            				this.clicks++;
	            			}
	            			else if (tile.getFill() == Color.YELLOW) {
	            				tile.setFill(Color.BURLYWOOD);
	            				this.flags--;
	            				text1List[1].setText("Flags: " + Integer.toString(flags));
	            				this.clicks++;
	            			}
	            			// The second condition is set because we can't have more flags than mines
	            			else if (tile.getFill() == Color.BURLYWOOD && flags<mines) {
	            				tile.setFill(Color.YELLOW);
	            				this.flags++;
	            				text1List[1].setText("Flags: " + Integer.toString(flags));
	            				this.clicks++;
	            			}
	            			else {
	            				; // do nothing
	            			}
	            		}
	            	});
	           
	        }
	    }
	    gameBoard.setStyle("-fx-background-color: grey;");
	    grid.getChildren().add(gameBoard);

	}
	
	public void createProgram(ActionEvent e) throws IOException {
		timeline.pause();
		root = FXMLLoader.load(getClass().getResource("Create.fxml"));
		stage = (Stage) myMenuBar.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void loadProgram(ActionEvent e) throws IOException {
		timeline.pause();
		root = FXMLLoader.load(getClass().getResource("Load.fxml"));
		stage = (Stage) myMenuBar.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void startProgram(ActionEvent e) {
		timeline.pause();
		this.initialize();
	}
	@FXML
	public void exitProgram(ActionEvent e) {
		timeline.pause();
		Platform.exit();
	}
	@FXML
	public void showRounds(ActionEvent e) throws IOException{
		timeline.pause();
		root = FXMLLoader.load(getClass().getResource("Rounds.fxml"));
		stage = (Stage) myMenuBar.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	public void showSolution(ActionEvent e) {
		timeline.pause();
		int mines=0;
		for (int row=0; row<this.dimension; row++) {
			for (int col=0; col<this.dimension; col++) {
				if (this.textList[row][col].getText() == "X" || this.textList[row][col].getText() == "H") {
					mines++;
					this.textList[row][col].setVisible(true);
					this.tileList[row][col].setFill(Color.MEDIUMPURPLE);
				}
			}
		}
		// This condition is set because if we have won or lost and 
		// we click at solution, then it shouldn't count as a defeat.
		if (lost == false && won == false) {
			lost = true;
			Main.totalMines.add(mines);
			// We remove one because the click that was a mine wasn't a good one
			Main.goodClicks.add(clicksOpened);
			Main.weWon.add(false);
			Main.startingTime.add(solutionTime);
			Main.endingTime.add(timeSeconds.get());
			// If it's 6 then remove head of each queue
			// because we only want to keep last 5
			if (Main.totalMines.size() == 6) {
				Main.totalMines.remove();
				Main.goodClicks.remove();
				Main.weWon.remove();
				Main.startingTime.remove();
				Main.endingTime.remove();
			}
		}
	}
	
	public void hyperFill(Rectangle[][] tileList, Text[][] textList, int row, int col, int dimension) {
		// Fill row
		for(int i=0; i<dimension; i++) {
			// Remove flags that hyper mine shows 
			if (tileList[i][col].getFill() == Color.YELLOW) {
				this.flags--;
			}
			tileList[i][col].setFill(Color.PURPLE);
			textList[i][col].setVisible(true);
		}
		// Fill column
		for(int i=0; i<dimension; i++) {
			if (tileList[row][i].getFill() == Color.YELLOW) {
				this.flags--;
			}
			tileList[row][i].setFill(Color.PURPLE);
			textList[row][i].setVisible(true);
		}
	}
	// This method checks the amount of tiles that are visible
	// and compare it to the total amount (in a 9x9 board its
	// 81 - mines, in a 16x16 board it's 256 - mines)
	public boolean checkIfWon(int mines, int dimension) {
		
		// The amount of texts that have to be visible for us to win
		int visibleText = dimension*dimension - mines;
		int counter = 0;
		for (int row=0; row<dimension; row++) {
			for (int col=0; col<dimension; col++) {
				if (textList[row][col].isVisible() && textList[row][col].getText().toString() != "X" && textList[row][col].getText().toString() != "H") {
					counter++;
				}
			}
		}
	if (counter == visibleText) return true;
	else return false;
	}
}
