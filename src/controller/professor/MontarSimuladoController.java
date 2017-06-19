package controller.professor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.MateriaDAO;
import dao.MontarSimuladoDAO;
import dao.QuestoesDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import main.Utils;
import objetos.Materia;
import objetos.Questoes;
import objetos.Simulado;
import objetos.SimuladoBQ;

public class MontarSimuladoController {
	@FXML ComboBox<Materia> cmbMateria;
	@FXML TableView<Questoes> tableQuestoes;
	@FXML TableColumn<Questoes, String> questoesColumn;
	@FXML Label lblQuantidade;
	@FXML Button btnConcluir;
	
	private MontarSimuladoDAO montarSimulado;
	private MateriaDAO materia;
	private QuestoesDAO questoes;
	private List<Questoes> data;
	
	public void initialize(){
		data = new ArrayList<>();
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		materia = new MateriaDAO(conexao);
		questoes = new QuestoesDAO(conexao);
		
		montarSimulado = new MontarSimuladoDAO(conexao);
		
		cmbMateria.setConverter(new StringConverter<Materia>() {
			@Override
			public Materia fromString(String string) {
				return null;
			}

			@Override
			public String toString(Materia object) {
				return object.getNome_materia();
			}
			
        });
		
		try {
			cmbMateria.setItems(FXCollections.observableArrayList(materia.lista()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cmbMateria.getSelectionModel().selectFirst();try {
			cmbMateria.setItems(FXCollections.observableArrayList(materia.lista()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cmbMateria.getSelectionModel().selectFirst();
		
		questoesColumn.setCellValueFactory(new Callback<CellDataFeatures<Questoes, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Questoes, String> p) {
	            return new SimpleStringProperty(p.getValue().getConteudo_questao());
	        }
	    });
		
		tableQuestoes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		loadTableQuestoes();
		
		cmbMateria.valueProperty().addListener(new ChangeListener<Materia>() {
			@Override
			public void changed(ObservableValue<? extends Materia> observable, Materia oldValue, Materia newValue) {
				loadTableQuestoes();
			}
		});
	}
	
	private void loadTableQuestoes(){
		List<Questoes> listaQuestoes = null;
		data.clear();
		try {
			listaQuestoes = questoes.lista();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < listaQuestoes.size(); i++) {
			if(listaQuestoes.get(i).getId_materia().equals(cmbMateria.getSelectionModel().getSelectedItem().getId_materia())){
				data.add(listaQuestoes.get(i));
			}
		}
		
		tableQuestoes.setItems(FXCollections.observableArrayList(data));
		tableQuestoes.scrollTo(0);
	}
	
	public void onClickAdicionarAoSimulado(){
		if(!tableQuestoes.getItems().isEmpty()){
			if(!tableQuestoes.getSelectionModel().getSelectedItems().isEmpty()){
				int qtd = Integer.parseInt(lblQuantidade.getText()) + tableQuestoes.getSelectionModel().getSelectedItems().size();
				lblQuantidade.setText(Integer.toString(qtd));
			}else{
				Utils.showError("Erro", "Selecione uma questão para adicionar ao simulado.");
				return;
			}
		}
		if(btnConcluir.isDisable()){
			if(Integer.parseInt(lblQuantidade.getText()) >= 20){
				btnConcluir.setDisable(false);
			}
		}
	}
	
	public void onClickConcluir(ActionEvent ae){
		Simulado simulado = new Simulado();
		simulado.setQnt_questoes(Integer.parseInt(lblQuantidade.getText()));
		SimuladoBQ simuladoBQ = new SimuladoBQ();
		List<Integer> ids_questoes = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			ids_questoes.add(data.get(i).getId_questao());
		}
		simuladoBQ.setId_questao_ref(ids_questoes);
		try {
			montarSimulado.salvaSimulado(simulado);
			simuladoBQ.setId_simulado_ref(simulado.getId_simulado());
			montarSimulado.salvaSimuladoBQ(simuladoBQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Utils.showInformation("Adicionado!","Simulado adicionado com sucesso");
		
		Node source = (Node) ae.getSource();
		Window thisStage = source.getScene().getWindow();
		thisStage.hide();
	}
}
