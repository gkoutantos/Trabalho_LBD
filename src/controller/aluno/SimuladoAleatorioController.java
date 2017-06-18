package controller.aluno;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.SimuladoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SimuladoAleatorioController {
	@FXML private CheckBox chkMatematica;
	@FXML private CheckBox chkFisica;
	@FXML private CheckBox chkQuimica;
	@FXML private CheckBox chkPortugues;
	@FXML private CheckBox chkBiologia;
	@FXML private CheckBox chkGeografia;
	@FXML private CheckBox chkHistoria;
	
	List<String> questoes;
	
	public void initialize(){
		questoes = new ArrayList<>();
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		SimuladoDAO simuladoDAO = new SimuladoDAO(conexao);
	}
	
	public void clickOnAvancar(ActionEvent ae){
		if(chkMatematica.isSelected()){
			
		}
		
		if(chkFisica.isSelected()){
			
		}
		
		if(chkQuimica.isSelected()){
			
		}
		
		if(chkPortugues.isSelected()){
			
		}
		
		if(chkBiologia.isSelected()){
			
		}
		
		if(chkGeografia.isSelected()){
			
		}
		
		if(chkHistoria.isSelected()){
			
		}
		
		try {
			AnchorPane simulado = FXMLLoader.load(getClass().getResource("/fxml/aluno/Simulado.fxml"));
			Scene scene = new Scene(simulado);
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
