package application;

// This is a class that fills the isBomb(board with the
// corresponding number of mines for each position
public class FillBoard {
	public static boolean isBomb(int pos) {
		if(pos == 9 || pos == 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int fillBoard(int row, int col, int dim, int ans, int[][] board) {
		// first row, first column
		if(row == 0 && col == 0) {
//			System.out.print("We are at (0.0): (" + row + "." + col + ")");
			if (isBomb(board[row+1][col+1])) ans++;
			if (isBomb(board[row][col+1])) ans++;
			if (isBomb(board[row+1][col])) ans++;
		}
		// first row, last column
		else if (row == 0 && col == dim-1) {
			if (isBomb(board[row+1][col])) ans++;
			if (isBomb(board[row][col-1])) ans++;
			if (isBomb(board[row+1][col-1])) ans++;
		}
		// last row, first column
		else if (row == dim-1 && col == 0) {
			if (isBomb(board[row-1][col])) ans++;
			if (isBomb(board[row-1][col+1])) ans++;
			if (isBomb(board[row][col+1])) ans++;
		}
		// last row, last column
		else if (row == dim-1 && col == dim-1) {
			if (isBomb(board[row][col-1])) ans++;
			if (isBomb(board[row-1][col-1])) ans++;
			if (isBomb(board[row-1][col])) ans++;
		}
		// first row
		else if (row == 0) {
			// check below
			if (isBomb(board[row+1][col-1])) ans++;
			if (isBomb(board[row+1][col])) ans++;
			if (isBomb(board[row+1][col+1])) ans++;
			// check left
			if (isBomb(board[row][col-1])) ans++;
			// check right
			if (isBomb(board[row][col+1])) ans++;
		}
		// last row
		else if (row == dim-1) {
			// check above
			if (isBomb(board[row-1][col-1])) ans++;
			if (isBomb(board[row-1][col])) ans++;
			if (isBomb(board[row-1][col+1])) ans++;
			// check left
			if (isBomb(board[row][col-1])) ans++;
			// check right
			if (isBomb(board[row][col+1])) ans++;
		}
		// first column
		else if (col == 0) {
			//check right (above, parallel and below)
			if (isBomb(board[row-1][col+1])) ans++;
			if (isBomb(board[row][col+1])) ans++;
			if (isBomb(board[row+1][col+1])) ans++;
			// check above
			if (isBomb(board[row-1][col])) ans++;
			// check below
			if (isBomb(board[row+1][col])) ans++;
		}
		// last column
		else if (col == dim-1) {
			//check left (above, parallel and below)
			if (isBomb(board[row-1][col-1])) ans++;
			if (isBomb(board[row][col-1])) ans++;
			if (isBomb(board[row+1][col-1])) ans++;
			// check above
			if (isBomb(board[row-1][col])) ans++;
			// check below
			if (isBomb(board[row+1][col])) ans++;
		}
		// somewhere in between
		else {
			// check above
			if (isBomb(board[row+1][col-1])) ans++;
			if (isBomb(board[row+1][col])) ans++;
			if (isBomb(board[row+1][col+1])) ans++;
			// check below
			if (isBomb(board[row-1][col-1])) ans++;
			if (isBomb(board[row-1][col])) ans++;
			if (isBomb(board[row-1][col+1])) ans++;
			// check left
			if (isBomb(board[row][col-1])) ans++;
			// check right
			if (isBomb(board[row][col+1])) ans++;
		}
		return ans;
	}
}
