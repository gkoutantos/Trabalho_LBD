package controller.professor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

public class DuvidasController {
	@FXML private TableView<?> tableDuvidas;
	
	public void initialize(){
		tableDuvidas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableDuvidas.setPlaceholder(new Label("Não há dúvidas não respondidas nas matérias que este professor ministra."));
	}
}
