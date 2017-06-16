package controller.aluno;


import java.sql.Connection;

import connection.ConnectionDB;
import dao.DuvidasDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DuvidasController {
	@FXML private TextField txtValor;
	private DuvidasDAO duvidasDAO;
	
	public void initialize(){
		ConnectionDB conecta = new ConnectionDB();
		Connection conexao = conecta.conecta();
		duvidasDAO = new DuvidasDAO(conexao);
	}

}
