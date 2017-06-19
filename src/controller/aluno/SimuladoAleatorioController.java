package controller.aluno;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.SimuladoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.Utils;
import objetos.Questoes;

public class SimuladoAleatorioController {
	@FXML private CheckBox chkMatematica;
	@FXML private CheckBox chkFisica;
	@FXML private CheckBox chkQuimica;
	@FXML private CheckBox chkPortugues;
	@FXML private CheckBox chkBiologia;
	@FXML private CheckBox chkGeografia;
	@FXML private CheckBox chkHistoria;
	
	private List<Questoes> questoes;
	private SimuladoDAO simuladoDAO;
	
	public void initialize(){
		questoes = new ArrayList<>();
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		simuladoDAO= new SimuladoDAO(conexao);
	}
	
	public void clickOnAvancar(ActionEvent ae){
		if(chkMatematica.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkMatematica.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(chkFisica.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkFisica.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(chkQuimica.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkQuimica.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(chkPortugues.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkPortugues.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(chkBiologia.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkBiologia.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(chkGeografia.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkGeografia.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(chkHistoria.isSelected()){
			try {
				questoes.addAll(simuladoDAO.lista(chkHistoria.getText()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(questoes.isEmpty()){
			Utils.showError("Erro", "Selecione pelo menos uma matéria.");
			return;
		}
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/aluno/Simulado.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			((SimuladoController) fxmlLoader.getController()).setQuestoes(questoes);
			Scene scene = new Scene(root);
			Stage simuladoStage = new Stage();
			simuladoStage.setTitle("Examtl - Simulado Aleatório");
			simuladoStage.setScene(scene);
			simuladoStage.setResizable(false);
			simuladoStage.show();
			
			Node source = (Node) ae.getSource();
			Window thisStage = source.getScene().getWindow();
			thisStage.hide();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
