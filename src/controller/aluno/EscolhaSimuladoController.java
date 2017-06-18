package controller.aluno;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EscolhaSimuladoController {
	@FXML private RadioButton rdbAleatorio;
	@FXML private RadioButton rdbPronto;
	
	public void initialize(){
		final ToggleGroup group = new ToggleGroup();
		rdbAleatorio.setToggleGroup(group);
		rdbPronto.setToggleGroup(group);
	}
	
	public void clickOnAvancar(ActionEvent ae){
		if (rdbAleatorio.isSelected()){
			try {
				AnchorPane simuladoAleatorio = FXMLLoader.load(getClass().getResource("/fxml/aluno/SimuladoAleatorio.fxml"));
				Scene scene = new Scene(simuladoAleatorio);
				Stage simuladoAleatorioStage = new Stage();
				simuladoAleatorioStage.setTitle("Examtl - Simulado Aleatório");
				simuladoAleatorioStage.setScene(scene);
				simuladoAleatorioStage.setResizable(false);
				simuladoAleatorioStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Node source = (Node) ae.getSource();
			Window thisStage = source.getScene().getWindow();
			thisStage.hide();
		}else{
			try {
				AnchorPane simuladoPronto = FXMLLoader.load(getClass().getResource("/fxml/aluno/SimuladoPronto.fxml"));
				Scene scene = new Scene(simuladoPronto);
				Stage simuladoProntoStage = new Stage();
				simuladoProntoStage.setTitle("Examtl - Simulado Pronto");
				simuladoProntoStage.setScene(scene);
				simuladoProntoStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Node source = (Node) ae.getSource();
			Window thisStage = source.getScene().getWindow();
			thisStage.hide();
		}
	}
}
