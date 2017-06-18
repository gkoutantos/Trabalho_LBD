package controller.professor;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.CadastrarMaterialDAO;
import dao.MateriaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Window;
import javafx.util.StringConverter;
import objetos.CadastroMaterial;
import objetos.Materia;

public class MaterialComplementarController {
	@FXML private TextField txtValor;
	@FXML private TextArea txtDesc;
	@FXML ComboBox<Materia> cmbMateria;
	private CadastrarMaterialDAO cadastrarmaterialDAO;
	
	public void initialize(){
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		MateriaDAO materia = new MateriaDAO(conexao);
		cadastrarmaterialDAO = new CadastrarMaterialDAO(conexao);
		
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
		
		cmbMateria.getSelectionModel().selectFirst();
	}
	
	public void clickOnCadastrar(ActionEvent ae){
		CadastroMaterial cadastromaterial = new CadastroMaterial();
		Materia materia = cmbMateria.getSelectionModel().getSelectedItem();
		
		if(!txtValor.getText().isEmpty() && !txtDesc.getText().isEmpty()){
			cadastromaterial.setValor(Integer.parseInt(txtValor.getText()));
			cadastromaterial.setDescricao(txtDesc.getText());
		}
		
		cadastromaterial.setId_materia(materia.getId_materia());
		
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
