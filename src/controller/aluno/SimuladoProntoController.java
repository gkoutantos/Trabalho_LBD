package controller.aluno;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.SimuladoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import objetos.Simulado;

public class SimuladoProntoController {
	@FXML private TableView<String> tableSimulados;
	@FXML private TableColumn<String, String> simuladosColumn;
	
	private ObservableList<Simulado> data;
	
	public void initialize(){
		tableSimulados.setPlaceholder(new Label("Não há simulados prontos."));
		
		simuladosColumn.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<String, String> p) {
	            return new SimpleStringProperty(p.getValue());
	        }
	    });
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		SimuladoDAO simuladoDAO = new SimuladoDAO(conexao);
		
		try {
			data = FXCollections.observableArrayList(simuladoDAO.lista());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//tableSimulados.setItems(FXCollections.observableArrayList(data));
	}
}
