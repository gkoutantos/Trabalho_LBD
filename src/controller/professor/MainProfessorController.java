package controller.professor;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainProfessorController {
	
	@FXML TableView<?> tableNoticias;
	
	public void initialize(){
		tableNoticias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableNoticias.setPlaceholder(new Label("Não há notícias para exibir."));
	}
	
	public void clickOnMontarSimulado(){
		try {
			AnchorPane montarSimulado = FXMLLoader.load(getClass().getResource("/fxml/professor/MontarSimulado.fxml"));
			Scene scene = new Scene(montarSimulado);
			Stage montarSimuladoStage = new Stage();
			montarSimuladoStage.setTitle("Examtl - Simulado");
			montarSimuladoStage.setScene(scene);
			montarSimuladoStage.setMinWidth(400);
			montarSimuladoStage.setMinHeight(600);
			montarSimuladoStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnQuestoes(){
		try {
			AnchorPane questoes = FXMLLoader.load(getClass().getResource("/fxml/professor/Questoes.fxml"));
			Scene scene = new Scene(questoes);
			Stage questoesStage = new Stage();
			questoesStage.setTitle("Examtl - Questões");
			questoesStage.setScene(scene);
			questoesStage.setResizable(false);
			questoesStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnMaterialComplementar(){
		try {
			AnchorPane materialComplementar = FXMLLoader.load(getClass().getResource("/fxml/professor/MaterialComplementar.fxml"));
			Scene scene = new Scene(materialComplementar);
			Stage materialComplementarStage = new Stage();
			materialComplementarStage.setTitle("Examtl - Material Complementar");
			materialComplementarStage.setScene(scene);
			materialComplementarStage.setResizable(false);
			materialComplementarStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnDuvidas(){
		try {
			AnchorPane duvidas = FXMLLoader.load(getClass().getResource("/fxml/professor/Duvidas.fxml"));
			Scene scene = new Scene(duvidas);
			Stage duvidasStage= new Stage();
			duvidasStage.setTitle("Examtl - Dúvidas");
			duvidasStage.setScene(scene);
			duvidasStage.setMinWidth(400);
			duvidasStage.setMinHeight(500);
			duvidasStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAbout(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre");
		alert.setHeaderText(null);
		alert.setContentText("............");
		alert.showAndWait();
	}
}
