package controller.aluno;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.MontarSimuladoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;
import main.Utils;
import objetos.Desempenho;
import objetos.Questoes;
import objetos.Simulado;

public class SimuladoController {
	
	@FXML private RadioButton rdbA;
	@FXML private RadioButton rdbB;
	@FXML private RadioButton rdbC;
	@FXML private RadioButton rdbD;
	@FXML private RadioButton rdbE;
	@FXML private Label lblQuestao;
	@FXML private Label lblQuantidade;
	@FXML private Label lblMateria;
	@FXML private Button btnAvancar;
	
	private List<Questoes> questoes = new ArrayList<>();
	private int cont = 0;
	private int acertos = 0;
	
	public void setQuestoes(List<Questoes> questoes) {
		this.questoes = questoes;
		lblQuestao.setText(this.questoes.get(cont).getConteudo_questao());
		lblQuantidade.setText("1/" + questoes.size());
	}
	public void initialize(){
		 final ToggleGroup group = new ToggleGroup();
		 rdbA.setToggleGroup(group);
		 rdbB.setToggleGroup(group);
		 rdbC.setToggleGroup(group);
		 rdbD.setToggleGroup(group);
		 rdbE.setToggleGroup(group);
	}
	
	public void clickOnAvancar(ActionEvent ae){
		if(cont != questoes.size() - 1){
			if (rdbA.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("A")){
					acertos++;
				}
			}else if (rdbB.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("B")){
					acertos++;
				}
			}else if (rdbC.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("C")){
					acertos++;
				}
			}else if (rdbD.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("D")){
					acertos++;
				}
			}else {
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("E")){
					acertos++;
				}
			}
			lblQuestao.setText(questoes.get(cont++).getConteudo_questao());
		}else{
			if (rdbA.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("A")){
					acertos++;
				}
			}else if (rdbB.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("B")){
					acertos++;
				}
			}else if (rdbC.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("C")){
					acertos++;
				}
			}else if (rdbD.isSelected()){
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("D")){
					acertos++;
				}
			}else {
				if (questoes.get(cont).getResposta_correta().equalsIgnoreCase("E")){
					acertos++;
				}
			}
			btnAvancar.setText("Concluir");
			Utils.showInformation("Simulado Concluído", "Verifique seu desempenho na tela de consulta.");
			Node source = (Node) ae.getSource();
			Window thisStage = source.getScene().getWindow();
			thisStage.hide();
			
			Simulado simulado = new Simulado();
			simulado.setQnt_questoes(questoes.size());
			ConnectionDB conecta = new ConnectionDB();
			Connection conexao = conecta.conecta();
			MontarSimuladoDAO montarSimulado = new MontarSimuladoDAO(conexao);
			montarSimulado.salvaSimulado(simulado);

			Desempenho desempenho = new Desempenho();

			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			Date minhaDate = new Date(System.currentTimeMillis());
			String novoFormato = formatador.format(minhaDate);
			desempenho.setData(novoFormato);
			desempenho.setRendimento(acertos / questoes.size());
			desempenho.setId(simulado.getId_simulado());
			desempenho.setProgresso(100);
		}
	}
	
	public void clickOnVoltar(){
		
	}
}
