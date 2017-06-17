package controller.aluno;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.DesempenhoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import objetos.Desempenho;

public class DesempenhoController {
	@FXML private TextField txtValor;
	@FXML private TableView<Desempenho> tableDesempenho;
	@FXML private TableColumn<Desempenho, String> simuladoidColumn;
	@FXML private TableColumn<Desempenho, String> acertosColumn;
	@FXML private TableColumn<Desempenho, String> progressoColumn;
	private ObservableList<Desempenho> data;
	
	public void initialize(){
		tableDesempenho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableDesempenho.setPlaceholder(new Label("Não há simulados realizados."));
		
		simuladoidColumn.setCellValueFactory(new Callback<CellDataFeatures<Desempenho, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Desempenho, String> p) {
	            return new SimpleStringProperty(p.getValue().getId());
	        }
	    });
		acertosColumn.setCellValueFactory(new Callback<CellDataFeatures<Desempenho, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Desempenho, String> p) {
	            return new SimpleStringProperty(p.getValue().getRendimento());
	        }
	    });
		progressoColumn.setCellValueFactory(new Callback<CellDataFeatures<Desempenho, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Desempenho, String> p) {
	            return new SimpleStringProperty(p.getValue().getProgresso());
	        }
	    });
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		DesempenhoDAO desempenho = new DesempenhoDAO(conexao);
		
		try {
			data = FXCollections.observableArrayList(desempenho.lista());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tableDesempenho.setItems(FXCollections.observableArrayList(data));
	}
}

	
	