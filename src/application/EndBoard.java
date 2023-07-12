package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class EndBoard {
	public static void lostBoard(Rectangle[][] tileList, Text[][] textList, int dimension) {
		for (int row=0; row<dimension; row++) {
			for (int col=0; col<dimension; col++) {
				if (textList[row][col].getText() == "X" || textList[row][col].getText() == "H") {
					textList[row][col].setVisible(true);
					tileList[row][col].setFill(Color.MEDIUMPURPLE);
				}
			}
		}
	}
	
	public static void wonBoard(Rectangle[][] tileList, Text[][] textList, int dimension) {
		for (int row=0; row<dimension; row++) {
			for (int col=0; col<dimension; col++) {
				if (textList[row][col].getText() == "X" || textList[row][col].getText() == "H") {
					textList[row][col].setVisible(true);
					tileList[row][col].setFill(Color.YELLOWGREEN);
				}
			}
		}
	}
}
