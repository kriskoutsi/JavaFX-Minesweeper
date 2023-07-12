package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// This class is used to implement the empty tile recursion, as seen in the real minesweeper game.
// If the next tile is empty then go at it without setting it's text visible. If it's not empty 
// (has a numeric value) then set it's text visible. It returns the number of flags because if a flag 
// was set, then it shouldn't "block" us from using another flag. For instance, if we have used 9 flags
// and we have 10 mines and this recursion makes purple 2 tiles that were flagged, it should return 2,
// meaning that 2 flags should be reduced in the controller class.
// 
public class EmptyField {
	public static int emptyField(Rectangle[][] tileList, Text[][] textList, int[][] board, int row, int col, int dim, int flags) {
		if (textList[row][col].isVisible()) {
			return flags;
		}
		if (tileList[row][col].getFill() == Color.YELLOW) {
			flags++;
		}
		textList[row][col].setVisible(true);
		tileList[row][col].setFill(Color.PURPLE);
		// first row, first column
		if(row == 0 && col == 0) {
			if (board[row+1][col+1] != 0) {
				if (tileList[row+1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col+1].setVisible(true);
				tileList[row+1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			}
			if (board[row+1][col+1] == 0) flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			if (board[row][col+1] != 0) {
				if (tileList[row][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col+1].setVisible(true);
				tileList[row][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			}
			if (board[row][col+1] == 0) flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			if (board[row+1][col] != 0) {
				if (tileList[row+1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col].setVisible(true);
				tileList[row+1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			}
			if (board[row+1][col] == 0) flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
		}
		// first row, last column
		else if (row == 0 && col == dim-1) {
			if (board[row+1][col] != 0) {
				if (tileList[row+1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col].setVisible(true);
				tileList[row+1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			}
			if (board[row+1][col] == 0) flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			if (board[row][col-1] != 0) {
				if (tileList[row][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col-1].setVisible(true);
				tileList[row][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			}
			if (board[row][col-1] == 0) flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			if (board[row+1][col-1] != 0) {
				if (tileList[row+1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col-1].setVisible(true);
				tileList[row+1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			}
			if (board[row+1][col-1] == 0) flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
		}
		// last row, first column
		else if (row == dim-1 && col == 0) {
			if (board[row-1][col] != 0) {
				if (tileList[row-1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col].setVisible(true);
				tileList[row-1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			}
			if (board[row-1][col] == 0) flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			if (board[row-1][col+1] != 0) {
				if (tileList[row-1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col+1].setVisible(true);
				tileList[row-1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			}
			if (board[row-1][col+1] == 0) flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			if (board[row][col+1] != 0) {
				if (tileList[row][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col+1].setVisible(true);
				tileList[row][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			}
			if (board[row][col+1] == 0) flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
		}
		// last row, last column
		else if (row == dim-1 && col == dim-1) {
			if (board[row][col-1] != 0) {
				if (tileList[row][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col-1].setVisible(true);
				tileList[row][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			}
			if (board[row][col-1] == 0) flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			if (board[row-1][col-1] != 0) {
				if (tileList[row-1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col-1].setVisible(true);
				tileList[row-1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			}
			if (board[row-1][col-1] == 0) flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			if (board[row-1][col] != 0) {
				if (tileList[row-1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col].setVisible(true);
				tileList[row-1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			}
			if (board[row-1][col] == 0) flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
		}
		// first row
		else if (row == 0) {
			// check below
			if (board[row+1][col-1] != 0) {
				if (tileList[row+1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
			textList[row+1][col-1].setVisible(true);
			tileList[row+1][col-1].setFill(Color.PURPLE);
			flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			}
			if (board[row+1][col-1] == 0) flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			if (board[row+1][col] != 0) {
				if (tileList[row+1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col].setVisible(true);
				tileList[row+1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			}
			if (board[row+1][col] == 0) flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			if (board[row+1][col+1] != 0) {
				if (tileList[row+1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col+1].setVisible(true);
				tileList[row+1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			}
			if (board[row+1][col+1] == 0) flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			
			
			// check left
			if (board[row][col-1] != 0) {
				if (tileList[row][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col-1].setVisible(true);
				tileList[row][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			}
			if (board[row][col-1] == 0) flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			
			
			// check right
			if (board[row][col+1] != 0) {
				if (tileList[row][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col+1].setVisible(true);
				tileList[row][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			}
			if (board[row][col+1] == 0) flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
		}
		// last row
		else if (row == dim-1) {
			
			
			// check above
			if (board[row-1][col-1] != 0) {
				if (tileList[row-1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col-1].setVisible(true);
				tileList[row-1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			}
			if (board[row-1][col-1] == 0) flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			if (board[row-1][col] != 0) {
				if (tileList[row-1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col].setVisible(true);
				tileList[row-1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			}
			if (board[row-1][col] == 0) flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			if (board[row-1][col+1] != 0) {
				if (tileList[row-1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col+1].setVisible(true);
				tileList[row-1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			}
			if (board[row-1][col+1] == 0) flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			
			
			// check left
			if (board[row][col-1] != 0) {
				if (tileList[row][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col-1].setVisible(true);
				tileList[row][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			}
			if (board[row][col-1] == 0) flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			
			
			// check right
			if (board[row][col+1] != 0) {
				if (tileList[row][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col+1].setVisible(true);
				tileList[row][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			}
			if (board[row][col+1] == 0) flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
		}
		// first column
		else if (col == 0) {
			
			
			//check right (above, parallel and below)
			if (board[row-1][col+1] != 0) {
				if (tileList[row-1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col+1].setVisible(true);
				tileList[row-1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			}
			if (board[row-1][col+1] == 0) flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			if (board[row][col+1] != 0) {
				if (tileList[row][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col+1].setVisible(true);
				tileList[row][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			}
			if (board[row][col+1] == 0) flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			if (board[row+1][col+1] != 0) {
				if (tileList[row+1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col+1].setVisible(true);
				tileList[row+1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			}
			if (board[row+1][col+1] == 0) flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			
			
			// check above
			if (board[row-1][col] != 0) {
				if (tileList[row-1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col].setVisible(true);
				tileList[row-1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			}
			if (board[row-1][col] == 0) flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			
			
			// check below
			if (board[row+1][col] != 0) {
				if (tileList[row+1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col].setVisible(true);
				tileList[row+1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			}
			if (board[row+1][col] == 0) flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
		}
		// last column
		else if (col == dim-1) {
			
			
			//check left (above, parallel and below)
			if (board[row-1][col-1] != 0) {
				if (tileList[row-1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col-1].setVisible(true);
				tileList[row-1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			}
			if (board[row-1][col-1] == 0) flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			if (board[row][col-1] != 0) {
				if (tileList[row][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col-1].setVisible(true);
				tileList[row][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			}
			if (board[row][col-1] == 0) flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			if (board[row+1][col-1] != 0) {
				if (tileList[row+1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col-1].setVisible(true);
				tileList[row+1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			}
			if (board[row+1][col-1] == 0) flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			
			
			// check above
			if (board[row-1][col] != 0) {
				if (tileList[row-1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col].setVisible(true);
				tileList[row-1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			}
			if (board[row-1][col] == 0) flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			
			
			// check below
			if (board[row+1][col] != 0) {
				if (tileList[row+1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col].setVisible(true);
				tileList[row+1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			}
			if (board[row+1][col] == 0) flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
		}
		// somewhere in between
		else {
			
			
			// check above
			if (board[row+1][col-1] != 0) {
				if (tileList[row+1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col-1].setVisible(true);
				tileList[row+1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			}
			if (board[row+1][col-1] == 0) flags = emptyField(tileList, textList, board, row+1, col-1, dim, flags);
			if (board[row+1][col] != 0) {
				if (tileList[row+1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col].setVisible(true);
				tileList[row+1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			}
			if (board[row+1][col] == 0) flags = emptyField(tileList, textList, board, row+1, col, dim, flags);
			if (board[row+1][col+1] != 0) {
				if (tileList[row+1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row+1][col+1].setVisible(true);
				tileList[row+1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			}
			if (board[row+1][col+1] == 0) flags = emptyField(tileList, textList, board, row+1, col+1, dim, flags);
			
			
			// check below
			if (board[row-1][col-1] != 0) {
				if (tileList[row-1][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col-1].setVisible(true);
				tileList[row-1][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			}
			if (board[row-1][col-1] == 0) flags = emptyField(tileList, textList, board, row-1, col-1, dim, flags);
			if (board[row-1][col] != 0) {
				if (tileList[row-1][col].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col].setVisible(true);
				tileList[row-1][col].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			}
			if (board[row-1][col] == 0) flags = emptyField(tileList, textList, board, row-1, col, dim, flags);
			if (board[row-1][col+1] != 0) {
				if (tileList[row-1][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row-1][col+1].setVisible(true);
				tileList[row-1][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			}
			if (board[row-1][col+1] == 0) flags = emptyField(tileList, textList, board, row-1, col+1, dim, flags);
			
			
			// check left
			if (board[row][col-1] != 0) {
				if (tileList[row][col-1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col-1].setVisible(true);
				tileList[row][col-1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			}
			if (board[row][col-1] == 0) flags = emptyField(tileList, textList, board, row, col-1, dim, flags);
			
			
			// check right
			if (board[row][col+1] != 0) {
				if (tileList[row][col+1].getFill() == Color.YELLOW) {
					flags++;
				}
				textList[row][col+1].setVisible(true);
				tileList[row][col+1].setFill(Color.PURPLE);
				flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
			}
			if (board[row][col+1] == 0) flags = emptyField(tileList, textList, board, row, col+1, dim, flags);
		}
		return flags;
	}
}
