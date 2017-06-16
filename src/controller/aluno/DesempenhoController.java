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

public class DesempenhoController {
	@FXML private TextField txtValor;
	@FXML private TableView<String> tableDesempenho;
	@FXML private TableColumn<String, String> simuladoidColumn;
	@FXML private TableColumn<String, String> acertosColumn;
	@FXML private TableColumn<String, String> progressoColumn;
	private ObservableList<String> data;
	private ObservableList<String> data2;
	private ObservableList<String> data3;
	
	public void initialize(){
		tableDesempenho.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableDesempenho.setPlaceholder(new Label("Não há simulados realizados."));
		simuladoidColumn.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<String, String> p) {
	            return new SimpleStringProperty(p.getValue());
	        }
	    });
		acertosColumn.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<String, String> p) {
	            return new SimpleStringProperty(p.getValue());
	        }
	    });
		progressoColumn.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<String, String> p) {
	            return new SimpleStringProperty(p.getValue());
	        }
	    });
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		DesempenhoDAO desempenho = new DesempenhoDAO(conexao);
		try {
			data = FXCollections.observableArrayList(desempenho.lista());
			data2 = FXCollections.observableArrayList(desempenho.acertos());
			data3 = FXCollections.observableArrayList(desempenho.progresso());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//AQUI EU TENHO QUE SETTAR UM OBSERVABLE EM CADA COLUNA
		tableDesempenho.setItems(FXCollections.observableArrayList(data));
	}
}

	
	