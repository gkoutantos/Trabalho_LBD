package controller.professor;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.CadastrarMaterialDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Window;
import objetos.CadastroMaterial;

public class MaterialComplementarController {
	@FXML private TextField txtValor;
	@FXML private TextArea txtDesc;
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
		alert.setTitle("Material Complementar");
		alert.setContentText("Cadastro efetuado com sucesso");
		alert.showAndWait();
		
		Node source = (Node) ae.getSource();
		Window thisStage = source.getScene().getWindow();
		thisStage.hide();
	}
}
