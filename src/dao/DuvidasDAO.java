package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Desempenho;
import objetos.Doacao;

public class DuvidasDAO {
	private Connection con;

	public DuvidasDAO(Connection con) {
		this.con = con;
	}

	public void salva(Doacao doacao) throws SQLException {

		String sql = "INSERT INTO duvidas(valor_doacao,id_aluno_ref) VALUES (?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, doacao.getValor());
			stmt.setInt(2, 101);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> lista() throws SQLException {
		
		String sql = "Select distinct * from duvidas as a JOIN professor_materia as B ON A.id_materia_ref = b.id_materia_ref WHERE b.id_professor_ref = 2 and a.esclarecimento is null;";

		ArrayList<String> lista = new ArrayList<String>();

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					lista.add(rs.getString("duvida"));
				}
			}
		}
		return lista;
	}
}
