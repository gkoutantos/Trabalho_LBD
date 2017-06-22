package controller.aluno;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.SimuladoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import main.Utils;
import objetos.Questoes;
import objetos.Simulado;

public class SimuladoProntoController {
	@FXML private TableView<Simulado> tableSimulados;
	@FXML private TableColumn<Simulado, String> simuladosColumn;
	@FXML private TableColumn<Simulado, String> materiasColumn;
	private List<Questoes> questoes;
	private SimuladoDAO simuladoDAO;
	
	private ObservableList<Simulado> data;
	
	public void initialize(){
		questoes = new ArrayList<>();
		
		tableSimulados.setPlaceholder(new Label("Não há simulados prontos."));
		
		simuladosColumn.setCellValueFactory(new Callback<CellDataFeatures<Simulado, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Simulado, String> p) {
	   
	            return new SimpleStringProperty(Integer.toString(p.getValue().getQnt_questoes()));
	        }
	    });
		materiasColumn.setCellValueFactory(new Callback<CellDataFeatures<Simulado, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Simulado, String> p) {
	        	String materias = "";
	        	for (int i = 0; i < p.getValue().getId_materias().size(); i++) {
					materias += p.getValue().getId_materias().get(i) + " ";
				}
	            return new SimpleStringProperty(materias);
	        }
	    });
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		simuladoDAO = new SimuladoDAO(conexao);
		
		try {
			data = FXCollections.observableArrayList(simuladoDAO.lista());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tableSimulados.setItems(FXCollections.observableArrayList(data));
	}
	
	public void clickOnFazer(ActionEvent ae) throws SQLException{
		if(!tableSimulados.getItems().isEmpty()){
			if(tableSimulados.getSelectionModel().getSelectedItem() != null){
				int id = tableSimulados.getSelectionModel().getSelectedItem().getId_simulado();
				questoes = simuladoDAO.listaPronto(id);
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/aluno/Simulado.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					((SimuladoController) fxmlLoader.getController()).setQuestoes(questoes);
					Scene scene = new Scene(root);
					Stage simuladoStage = new Stage();
					simuladoStage.setTitle("Examtl - Simulado Pronto");
					simuladoStage.setScene(scene);
					simuladoStage.setResizable(false);
					simuladoStage.show();
					
					Node source = (Node) ae.getSource();
					Window thisStage = source.getScene().getWindow();
					thisStage.hide();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				Utils.showError("Erro", "Selecione pelo menos um simulado.");
				return;
			}
		}
	}
}
