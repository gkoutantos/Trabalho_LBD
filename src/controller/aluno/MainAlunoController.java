package controller.aluno;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;

public class MainAlunoController {
	
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
	
	public void showAbout(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre");
		alert.setHeaderText(null);
		alert.setContentText("Algumas informações básicas para a utilização do programa:\n\n\n"+	
				"Fazer Simulado: clique nesta opção para a realização de simulados\n"
				+ "- Simulados Prontos: são simulados já desenvolvidos por algum professor cadastrado no banco "
				+ "normalmente são de conteúdos mais específicos\n"
				+ "- Simulados Aleatórios: são simulados que serão gerados aleatóriamente com base nas matérias "
				+ "desejadas.\n\n"
				+ "Realizar -> Doações: Aqui você realizar uma doação e contribuir para todos os professores "
				+ "ativos no sistema.\n\n"
				+ "Realizar -> Dúvida: Aqui você pode deixar perguntas que serão respondidas por professores "
				+ "que dominam o conteúdo.\n\n"
				+ "Na aba notícias você pode conferir tudo o que está acontecendo nos "
				+ "principais vestibulares do Brasil\n\n"
				+ "Consultar -> Desempenho: Aqui você poderá consultar seu desempenho nos simulados realizados.\n\n"
				+ "Consultar -> Material complementar: Aqui você pode consultar materiais com conteúdos extras "
				+ "para te ajudar no estudo.");
		alert.showAndWait();
	}
	
	public void clickOnFazerSimulado(){
		try {
			AnchorPane simulado = FXMLLoader.load(getClass().getResource("/fxml/aluno/EscolhaSimulado.fxml"));
			Scene scene = new Scene(simulado);
			Stage simuladoStage = new Stage();
			simuladoStage.setTitle("Examtl - Escolher Simulado");
			simuladoStage.setScene(scene);
			simuladoStage.setResizable(false);
			simuladoStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnDoacoes(){
		try {
			AnchorPane doacao = FXMLLoader.load(getClass().getResource("/fxml/aluno/Doacao.fxml"));
			Scene scene = new Scene(doacao);
			Stage doacaoStage = new Stage();
			doacaoStage.setTitle("Examtl - Doação");
			doacaoStage.setScene(scene);
			doacaoStage.setResizable(false);
			doacaoStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnDuvidas(){
		try {
			AnchorPane duvidas = FXMLLoader.load(getClass().getResource("/fxml/aluno/Duvidas.fxml"));
			Scene scene = new Scene(duvidas);
			Stage duvidasStage = new Stage();
			duvidasStage.setTitle("Examtl - Dúvidas");
			duvidasStage.setScene(scene);
			duvidasStage.setMinWidth(800);
			duvidasStage.setMinHeight(600);
			duvidasStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnDesempenho(){
		try {
			AnchorPane desempenho = FXMLLoader.load(getClass().getResource("/fxml/aluno/Desempenho.fxml"));
			Scene scene = new Scene(desempenho);
			Stage desempenhoStage = new Stage();
			desempenhoStage.setTitle("Examtl - Desempenho");
			desempenhoStage.setScene(scene);
			desempenhoStage.setMinWidth(400);
			desempenhoStage.setMinHeight(500);
			desempenhoStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnMaterialComplementar(){
		try {
			AnchorPane consultaMaterial = FXMLLoader.load(getClass().getResource("/fxml/aluno/MaterialComplementar.fxml"));
			Scene scene = new Scene(consultaMaterial);
			Stage consultaMaterialStage = new Stage();
			consultaMaterialStage.setTitle("Examtl - Material Complememtar");
			consultaMaterialStage.setScene(scene);
			consultaMaterialStage.setMinWidth(400);
			consultaMaterialStage.setMinHeight(500);
			consultaMaterialStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
