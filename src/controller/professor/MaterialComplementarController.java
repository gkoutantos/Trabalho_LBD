package controller.professor;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.CadastrarMaterialDAO;
import dao.DoacaoDAO;
import dao.MaterialComplementarDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;
import objetos.CadastroMaterial;
import objetos.Doacao;

public class MaterialComplementarController {
	@FXML private TextField txtValor;
	@FXML private TextField txtDesc;
	private CadastrarMaterialDAO cadastrarmaterialDAO;
	
	public void initialize(){
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		cadastrarmaterialDAO = new CadastrarMaterialDAO(conexao);
	}
	
	public void clickOnCadastrar(ActionEvent ae){
		CadastroMaterial cadastromaterial = new CadastroMaterial();
		
		if(!txtValor.getText().isEmpty() && !txtDesc.getText().isEmpty()){
			cadastromaterial.setValor(Integer.parseInt(txtValor.getText()));
			cadastromaterial.setDescricao(txtDesc.getText());
		}
		
		try {
			cadastrarmaterialDAO.salva(cadastromaterial);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("");
		alert.setTitle("Doação");
		alert.setContentText("Cadastro efetuado com sucesso");
		alert.showAndWait();
		
		Node source = (Node) ae.getSource();
		Window loginStage = source.getScene().getWindow();
		loginStage.hide();
	}
}
