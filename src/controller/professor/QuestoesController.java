package controller.professor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.MateriaDAO;
import dao.QuestoesDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;
import javafx.util.StringConverter;
import main.Utils;
import objetos.Materia;
import objetos.Questoes;

public class QuestoesController {
	@FXML RadioButton rdbA;
	@FXML RadioButton rdbB;
	@FXML RadioButton rdbC;
	@FXML RadioButton rdbD;
	@FXML RadioButton rdbE;
	@FXML ComboBox<Materia> cmbMateria;
	@FXML ComboBox<Integer> cmbDificuldade;
	@FXML TextArea txtConteudo;
	
	private QuestoesDAO questoesDAO;
	
	public void initialize(){
		final ToggleGroup group = new ToggleGroup();
		rdbA.setToggleGroup(group);
		rdbB.setToggleGroup(group);
		rdbC.setToggleGroup(group);
		rdbD.setToggleGroup(group);
		rdbE.setToggleGroup(group);
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		MateriaDAO materia = new MateriaDAO(conexao);
		
		questoesDAO = new QuestoesDAO(conexao);
		
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
		
		List<Integer> dificuldade = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			dificuldade.add(i);
		}
		cmbDificuldade.setItems(FXCollections.observableArrayList(dificuldade));
		cmbDificuldade.getSelectionModel().selectFirst();
	}
	
	public void clickOnCadastrar(ActionEvent ae){
		Questoes questao = new Questoes();
		Materia materia = cmbMateria.getSelectionModel().getSelectedItem();
		
		if(!txtConteudo.getText().isEmpty()){
			questao.setConteudo_questao(txtConteudo.getText());
		}else{
			Utils.showError("Erro", "O campo conteúdo não pode ser null.");
		}
		
		questao.setDificuldade(cmbDificuldade.getSelectionModel().getSelectedItem());
		
		if(rdbA.isSelected()){
			questao.setResposta_correta("A");
		}else if(rdbB.isSelected()){
			questao.setResposta_correta("B");
		}else if(rdbC.isSelected()){
			questao.setResposta_correta("C");
		}else{
			questao.setResposta_correta("D");
		}
		
		questao.setId_materia(materia.getId_materia());
		
		try {
			questoesDAO.salva(questao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Utils.showInformation("Questões","Questão cadastrada com sucesso");
		
		Node source = (Node) ae.getSource();
		Window thisStage = source.getScene().getWindow();
		thisStage.hide();
	}
}
