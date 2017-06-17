package controller.aluno;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.MaterialComplementarDAO;
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
import objetos.MaterialComplementar;

public class MaterialComplementarController {
	@FXML private TextField txtValor;
	@FXML private TableView<MaterialComplementar> tableMC;
	@FXML private TableColumn<MaterialComplementar, String> tituloColumn;
	@FXML private TableColumn<MaterialComplementar, String> conteudoColumn;
	private ObservableList<MaterialComplementar> data;
	
	public void initialize(){
		tableMC.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableMC.setPlaceholder(new Label("Não há material complementar."));
		
		tituloColumn.setCellValueFactory(new Callback<CellDataFeatures<MaterialComplementar, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<MaterialComplementar, String> p) {
	            return new SimpleStringProperty(p.getValue().getTitulo());
	        }
	    });
		conteudoColumn.setCellValueFactory(new Callback<CellDataFeatures<MaterialComplementar, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<MaterialComplementar, String> p) {
	            return new SimpleStringProperty(p.getValue().getConteudo());
	        }
	    });
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		MaterialComplementarDAO mc = new MaterialComplementarDAO(conexao);
		
		try {
			data = FXCollections.observableArrayList(mc.lista());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tableMC.setItems(FXCollections.observableArrayList(data));
	}


}
