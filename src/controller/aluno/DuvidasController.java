package controller.aluno;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.DuvidaAlunoDAO;
import dao.MateriaDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.util.StringConverter;
import main.Utils;
import objetos.Duvidas;
import objetos.Materia;

public class DuvidasController {
	@FXML private TextField txtTitulo;
	@FXML private TextField txtDuvida;
	@FXML ComboBox<Materia> cmbMateria;
	
	private DuvidaAlunoDAO duvidasalunoDAO;
		
	
	public void initialize(){
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		MateriaDAO materia = new MateriaDAO(conexao);
		duvidasalunoDAO = new DuvidaAlunoDAO(conexao);
		
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
		
		Duvidas duvidas = new Duvidas();
		Materia materia = cmbMateria.getSelectionModel().getSelectedItem();
		
		if(!txtTitulo.getText().isEmpty() && !txtDuvida.getText().isEmpty() ){
			duvidas.setTitulo(txtTitulo.getText());
			duvidas.setDuvida(txtDuvida.getText());
		}else{
			Utils.showError("Erro", "Os campos não podem ser nulos.");
		}
		
		duvidas.setId_materia(materia.getId_materia());
		
		try {
			duvidasalunoDAO.salva(duvidas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Utils.showInformation("Dúvida","Dúvida cadastrada com sucesso");
		
		Node source = (Node) ae.getSource();
		Window thisStage = source.getScene().getWindow();
		thisStage.hide();
	}
}
