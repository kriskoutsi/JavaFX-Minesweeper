package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// This is a class that reads a txt file that contains 4 lines,
// all numeric and returns an integer array of length 5.
public class ReadInput {
	
	// Check if the given string is a number
	public static boolean isNumeric(String str) { 
		try {  
			Integer.parseInt(str);  
			return true;
		} 
		catch(NumberFormatException e) {  
			return false;  
		}  
	}
	
	public static int[] readInput(String fileName) {
		String initialFile = "D:\\downloads\\eclipse\\Eclipse-Workspace\\MediaLab\\medialab\\";
		int [] inpInt = new int[5];
		int levelInt, minesInt, timeInt, hyperMineInt;
		File file = new File(initialFile+fileName+".txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			String level = input.readLine();
			// Check if the 1st line is empty, meaning that the file ended earlier than expected
			if (level == null) {
				input.close();
				throw new InvalidDescriptionException("No level inputted!");
			}
			// Check if level is an integer in range
			if (isNumeric(level)) {
				levelInt = Integer.parseInt(level);
				if (levelInt == 1 || levelInt == 2) {
					inpInt[0] = levelInt;
				}
				// level isn't in range
				else {
					input.close();
					throw new InvalidValueException("Level can be either 1 or 2!");
				}
			}
			// level isn't an integer
			else {
				input.close();
				throw new InvalidValueException("Level value has to be an int!");
			}
			// Check if the 2nd line is empty
			String mines = input.readLine();
			if (mines == null) {
				input.close();
				throw new InvalidDescriptionException("No mines inputted!");
			}
			// Check if "mines" is an integer an in range
			if(isNumeric(mines)) {
				minesInt = Integer.parseInt(mines);
				//level 1 mines in range
				if(levelInt == 1 && minesInt > 8 && minesInt < 12) {
					inpInt[1] = minesInt;
				}
				// level 1 mines out of range
				else if (levelInt == 1 && (minesInt < 9 || minesInt > 11)) {
					input.close();
					throw new InvalidValueException("In level 1, mines are between 9 and 11!");
				}
				// level 2 mines in range
				else if (levelInt == 2 && minesInt > 34 && minesInt < 46) {
					inpInt[1] = minesInt;
				}
				// level 2 mines out of range
				else {
					input.close();
					throw new InvalidValueException("In level 2, mines are between 35 and 45!");
				}
			}
			// "mines" isn't an integer
			else {
				input.close();
				throw new InvalidValueException("Mines value has to be an int!");
			}
			// Check if the 3rd line is empty
			String time = input.readLine();
			if (time == null) {
				input.close();
				throw new InvalidDescriptionException("No time inputted!");
			}
			// Check if "time" is an integer and in range
			if(isNumeric(time)) {
				timeInt = Integer.parseInt(time);
				// level 1 time in range
				if(levelInt == 1 && timeInt > 119 && timeInt < 181) {
					inpInt[2] = timeInt;
				}
				// level 1 time out of range
				else if (levelInt == 1 && (timeInt < 120 || timeInt > 180)) {
					input.close();
					throw new InvalidValueException("In level 1, time is between 120 and 180!");
				}
				// level 2 time in range
				else if (levelInt == 2 && timeInt > 239 && timeInt < 361) {
					inpInt[2] = timeInt;
				}
				// level 2 time out of range
				else {
					input.close();
					throw new InvalidValueException("In level 2, time is between 240 and 360!");
				}
 			}
			// "time" isn't an integer
			else {
				input.close();
				throw new InvalidValueException("Time value has to be an int!");
			}
			// Check if the 4th line is empty
			String hyperMine = input.readLine();
			if (hyperMine == null) {
				input.close();
				throw new InvalidDescriptionException("No Hyper-mine inputted!");
			}
			// Check if Hyper-mine is an integer and in range
			if(isNumeric(hyperMine)) {
				hyperMineInt = Integer.parseInt(hyperMine);
				// Valid level 1 value
				if(levelInt == 1 && hyperMineInt == 0) {
					inpInt[3] = hyperMineInt;
				}
				// Invalid level 1 value
				if(levelInt == 1 && hyperMineInt == 1) {
					input.close();
					throw new InvalidValueException("In level 1, we can't have a Hyper-mine!");
				}
				// Invalid Hyper-mine value
				if(hyperMineInt != 0 && hyperMineInt != 1) {
					input.close();
					throw new InvalidValueException("Hyper-mine value has to be either 0 or 1!");
				}
				if(levelInt == 2 && hyperMineInt == 1) {
					inpInt[3] = hyperMineInt;
				}
			}
			// Hyper-mine isn't an integer
			else {
				input.close();
				throw new InvalidValueException("Hyper-mine value has to be an int!");
			}
			// Check if an extra line exists, meaning that more data than needed were put in
			String extraLine = input.readLine();
			// No extra line was put in
			if (extraLine == null) {
				// In level 1, dimension is 9
				if(levelInt == 1) {
					inpInt[4] = 9;
				}
				// In level 2, dimension is 16
				else {
					inpInt[4] = 16;
				}
				input.close();
				return inpInt;
			}
			else {
				input.close();
				throw new InvalidDescriptionException("Only 4 lines have to be inputted!");
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidDescriptionException e) {
			e.printStackTrace();
		}
		catch (InvalidValueException e) {
			e.printStackTrace();
		}
		// We reach this block of code after an exception occurred
		int[] err = {0};
		return err;
	}
}
