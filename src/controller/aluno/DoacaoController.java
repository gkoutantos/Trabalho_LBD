package controller.aluno;

import java.sql.SQLException;
import java.sql.Connection;

import connection.ConnectionDB;
import dao.DoacaoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Window;
import main.Utils;
import javafx.scene.control.TextField;
import objetos.Doacao;

public class DoacaoController {
	@FXML private TextField txtValor; 
	private DoacaoDAO doacaoDAO;
	
	public void initialize(){
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		doacaoDAO = new DoacaoDAO(conexao);
	}
	
	public void clickOnDoar(ActionEvent ae){
		Doacao doacao = new Doacao();
		
		if(!txtValor.getText().isEmpty()){
			doacao.setValor(Integer.parseInt(txtValor.getText()));
		}
		
		try {
			doacaoDAO.salva(doacao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Utils.showInformation("Doa��o","Doa��o realizada com sucesso");
		
		Node source = (Node) ae.getSource();
		Window thisStage = source.getScene().getWindow();
		thisStage.hide();
	}
}
