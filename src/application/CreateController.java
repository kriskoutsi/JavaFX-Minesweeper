package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController {
	
	@FXML
	TextField fileNameTextField;
	@FXML
	TextField levelTextField;
	@FXML
	TextField minesTextField;
	@FXML
	TextField hyperMineTextField;
	@FXML
	TextField totalTimeTextField;
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void create(ActionEvent event) throws IOException{
		
		// Part 1. Create the file
		File newFile = new File("D:\\downloads\\eclipse\\Eclipse-Workspace\\MediaLab\\medialab\\" + fileNameTextField.getText() + ".txt");
		boolean created = newFile.createNewFile();
		// If the file exists, delete it and create a new one 
		if (!created) {
			newFile.delete();
		}
		FileWriter writeFile = new FileWriter("D:\\downloads\\eclipse\\Eclipse-Workspace\\MediaLab\\medialab\\" + fileNameTextField.getText() + ".txt");
		writeFile.write(levelTextField.getText() + "\n");
		writeFile.write(minesTextField.getText() + "\n");
		writeFile.write(totalTimeTextField.getText() + "\n");
		writeFile.write(hyperMineTextField.getText());
		writeFile.close();
		
		
		// Part 2. Start the game with new file's data
		String fileName = fileNameTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Starting.fxml"));
		root = loader.load();
		
		Controller.startString = fileName;
		Controller controller = loader.getController();
		controller.startProgram(event);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void createCancel(ActionEvent event) throws IOException{
		
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
