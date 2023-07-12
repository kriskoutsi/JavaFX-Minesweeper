package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadController {
	
	@FXML
	TextField fileNameTextField;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void loadStart(ActionEvent event) throws IOException{
		
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
public void loadCancel(ActionEvent event) throws IOException{
		
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
