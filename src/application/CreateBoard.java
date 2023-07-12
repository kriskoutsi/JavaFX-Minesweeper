package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// This class creates a 2d integer array, that
// represents a board that will be used for minesweeper
public class CreateBoard {
	/**
	 * This method creates a 2 dimensional integer array, that
	 * represents a board that will be used for minesweeper. We know that a
	 * tile can't have a value greater than 8, therefore a mine has a value
	 * of 9 and a hypermine (if it exists) has a value of 10. These mines are
	 * randomly placed in the board, therefore we don't know where they'll end
	 * up beforehand.
	 * @param dimension This will be the dimension of the board. Its value
	 * is either 9 or 16.
	 * @param mines This is the number of mines in the board.
	 * @param hyperMine This is a mine that if it's right-clicked in the
	 * first 4 moves of the game, then it reveals the whole row and column.
	 * Not all boards have a hyperMine.
	 * @return A 2 dimensional integer array.
	 */
	public static int[][] createBoard(int dimension, int mines, int hyperMine) {
		int[][] board = new int[dimension][dimension];
		
		Integer[] minePos = new Integer[dimension*dimension];
		
		// Fill minePos with 0's
		Arrays.fill(minePos, 0);
		
		// Initialize minePos by inserting "mines-1" number of 1's and one 2 (if hyper-mine == 1)
		// If we have 10 mines, we insert 9 one's in the first 9 positions and one 2 at the end
		for(int i=0; i<mines; i++) {
			if(hyperMine == 1) {
				if (i<mines-1) {
					minePos[i] = 1;
				}
				else {
					minePos[i] = 2;
				}
			}
			else {
				minePos[i] = 1;
			}
		}
		
		// Shuffle the 1's and the 2 to random positions
		List<Integer> shuffled = Arrays.asList(minePos);
		Collections.shuffle(shuffled);
		shuffled.toArray(minePos);
		
		int counter = 0;
		// Place the mines to the board
		for(int row=0; row<dimension; row++) {
			for(int col=0; col<dimension; col++) {
				if (minePos[counter] == 1) {
					board[row][col] = 9;
				}
				if (minePos[counter] == 2) {
					board[row][col] = 10;
				}
				counter++;
			}
		}
//		System.out.println("Complete board:");
		
		// Fill the rest of the board
		for(int row = 0; row < dimension; row++) {
			for(int col = 0; col < dimension; col++) {
				if(board[row][col] == 0) {
					board[row][col] = FillBoard.fillBoard(row, col, dimension, 0, board);
				}
				//System.out.print(board[row][col] + " ");
			}
//			System.out.print("\n");
		}
		// Create a file and place the mines location
		try {
			File minesFile = new File("medialab\\mines.txt");
			boolean created = minesFile.createNewFile();
			// If the file exists, delete it and create a new one 
			if (!created) {
				minesFile.delete();
			}
			FileWriter writeMines = new FileWriter("medialab\\mines.txt");
			for (int row=0; row<dimension; row++) {
				for (int col=0; col<dimension; col++) {
					if (board[row][col] == 9) {
						writeMines.write(row+", "+ col + ", 0\n");
					}
					if (board[row][col] == 10) {
						writeMines.write(row+", "+ col + ", 1\n");
					}
				}
			}
			writeMines.close();
		    } 
		catch (IOException e) {
			System.out.println("An error occurred.");
		    e.printStackTrace();
		}
		return board;
	}
}
