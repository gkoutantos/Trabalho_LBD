package controller.professor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;
import dao.NoticiasDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainProfessorController {
	
	@FXML private TableView<String> tableNoticias;
	@FXML private TableColumn<String, String> noticiasColumn;
	
	private ObservableList<String> data;
	
	public void initialize(){
		tableNoticias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableNoticias.setPlaceholder(new Label("Não há notícias para exibir."));
		
		noticiasColumn.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<String, String> p) {
	            return new SimpleStringProperty(p.getValue());
	        }
	    });
		
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		
		NoticiasDAO noticias = new NoticiasDAO(conexao);
		try {
			data = FXCollections.observableArrayList(noticias.lista());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tableNoticias.setItems(data);
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
		alert.setContentText("Algumas informações básicas para a utilização do programa:\n\n\n"+	
				"Montar simulado: clique na opção montar e simulado e adicione as questões "
				+ "desejadas, lembre-se o número mínimo de questões são 20 por simulado.\n\n"
				+ "Na aba notícias você pode conferir tudo o que está acontecendo nos "
				+ "principais vestibulares do Brasil\n\n"
				+ "Consultar -> Dúvidas: Nesta opção você poderá sanar as dúvidas dos alunos.\n\n"
				+ "Cadastrar -> Questões: Você poderá complementar nosso banco de questões através "
				+ "dessa opção.\n\n"
				+ "Cadastrar -> Material Complementar: Aqui você poderá publicar seus materiais "
				+ "desenvolvidos que complementam algum conteúdo.");
		alert.showAndWait();
	}
}
