package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils {
	public static void showInformation(String title, String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("");
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	
	public static void showError(String title, String msg){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("");
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
