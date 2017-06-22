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
		tableNoticias.setPlaceholder(new Label("N�o h� not�cias para exibir."));
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
		alert.setContentText("Algumas informa��es b�sicas para a utiliza��o do programa:\n\n\n"+	
				"Fazer Simulado: clique nesta op��o para a realiza��o de simulados\n"
				+ "- Simulados Prontos: s�o simulados j� desenvolvidos por algum professor cadastrado no banco "
				+ "normalmente s�o de conte�dos mais espec�ficos\n"
				+ "- Simulados Aleat�rios: s�o simulados que ser�o gerados aleat�riamente com base nas mat�rias "
				+ "desejadas.\n\n"
				+ "Realizar -> Doa��es: Aqui voc� realizar uma doa��o e contribuir para todos os professores "
				+ "ativos no sistema.\n\n"
				+ "Realizar -> D�vida: Aqui voc� pode deixar perguntas que ser�o respondidas por professores "
				+ "que dominam o conte�do.\n\n"
				+ "Na aba not�cias voc� pode conferir tudo o que est� acontecendo nos "
				+ "principais vestibulares do Brasil\n\n"
				+ "Consultar -> Desempenho: Aqui voc� poder� consultar seu desempenho nos simulados realizados.\n\n"
				+ "Consultar -> Material complementar: Aqui voc� pode consultar materiais com conte�dos extras "
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
			doacaoStage.setTitle("Examtl - Doa��o");
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
			duvidasStage.setTitle("Examtl - D�vidas");
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
