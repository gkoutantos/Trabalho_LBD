package controller.aluno;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import dao.DesempenhoDAO;
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
	private MontarSimuladoDAO montarSimulado;
	private Connection conexao;
	
	public void setQuestoes(List<Questoes> questoes) {
		this.questoes = questoes;
		lblQuestao.setText(this.questoes.get(cont).getConteudo_questao());
		cont++;
		lblQuantidade.setText("1/" + questoes.size());
	}
	public void initialize(){
		 final ToggleGroup group = new ToggleGroup();
		 rdbA.setToggleGroup(group);
		 rdbB.setToggleGroup(group);
		 rdbC.setToggleGroup(group);
		 rdbD.setToggleGroup(group);
		 rdbE.setToggleGroup(group);
		 
		ConnectionDB conecta = new ConnectionDB();
		conexao= conecta.conecta();
		montarSimulado = new MontarSimuladoDAO(conexao);
	}
	
	public void clickOnAvancar(ActionEvent ae) throws SQLException{
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
			lblQuantidade.setText(cont + "/" + questoes.size());
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
			lblQuestao.setText(questoes.get(cont++).getConteudo_questao());
			lblQuantidade.setText(cont++ + "/" + questoes.size());
			
			Utils.showInformation("Simulado Concluído", "Verifique seu desempenho na tela de consulta.");
			Node source = (Node) ae.getSource();
			Window thisStage = source.getScene().getWindow();
			thisStage.hide();
			
			Simulado simulado = new Simulado();
			simulado.setQnt_questoes(questoes.size());
			
			montarSimulado.salvaSimulado(simulado);

			Desempenho desempenho = new Desempenho();

			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			Date minhaDate = new Date(System.currentTimeMillis());
			//System.out.println(Integer.toString(Calendar.HOUR_OF_DAY));
			String novoFormato = formatador.format(minhaDate)+" - id: "+(Integer.toString(simulado.getId_simulado()));
			desempenho.setData(novoFormato);
			double acertos2 = acertos;
			double tamanho = questoes.size();
			int rendimento2 = (int) ((acertos2 / tamanho)*100);
			System.out.println(simulado.getId_simulado());
			desempenho.setRendimento(Integer.toString(rendimento2));
			desempenho.setId(Integer.toString(simulado.getId_simulado()));
			desempenho.setProgresso(Integer.toString(100));
			DesempenhoDAO desempenhoDAO = new DesempenhoDAO(conexao);
			desempenhoDAO.salva(desempenho);
		}
	}
}
