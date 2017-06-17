package controller.professor;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.DesempenhoDAO;
import dao.DuvidasDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class DuvidasController {
	@FXML private TableView<String> tableDuvidas;
	@FXML private TableColumn<String, String> duvidasColumn;
	
	private ObservableList<String> data;
	
	public void initialize(){
		tableDuvidas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableDuvidas.setPlaceholder(new Label("Não há dúvidas não respondidas nas matérias que este professor ministra."));
		
		duvidasColumn.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<String, String> p) {
	            return new SimpleStringProperty(p.getValue());
	        }
	    });
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		DuvidasDAO duvidas = new DuvidasDAO(conexao);
		
		try {
			data = FXCollections.observableArrayList(duvidas.lista());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tableDuvidas.setItems(FXCollections.observableArrayList(data));
	}
}
