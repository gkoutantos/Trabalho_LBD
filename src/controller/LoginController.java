package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {
	
	public void clickOnAluno(ActionEvent ae){
		try {
			Node source = (Node) ae.getSource();
			Window loginStage = source.getScene().getWindow();
			loginStage.hide();
			
			AnchorPane mainAluno = FXMLLoader.load(getClass().getResource("/fxml/aluno/MainAluno.fxml"));
			Scene scene = new Scene(mainAluno);
			Stage mainAlunoStage = new Stage();
			mainAlunoStage.setTitle("Examtl - Aluno");
			mainAlunoStage.setScene(scene);
			mainAlunoStage.setMinWidth(800);
			mainAlunoStage.setMinHeight(600);
			mainAlunoStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnProfessor(ActionEvent ae){
		try {
			Node source = (Node) ae.getSource();
			Window loginStage = source.getScene().getWindow();
			loginStage.hide();
			
			AnchorPane mainProfessor = FXMLLoader.load(getClass().getResource("/fxml/professor/MainProfessor.fxml"));
			Scene scene = new Scene(mainProfessor);
			Stage mainProfessorStage = new Stage();
			mainProfessorStage.setTitle("Examtl - Professor");
			mainProfessorStage.setScene(scene);
			mainProfessorStage.setMinWidth(800);
			mainProfessorStage.setMinHeight(600);
			mainProfessorStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
