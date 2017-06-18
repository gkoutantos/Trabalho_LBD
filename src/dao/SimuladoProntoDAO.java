package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SimuladoProntoDAO {
	private Connection con;
	private ArrayList<String> lista = new ArrayList<String>();

	public SimuladoProntoDAO(Connection con) {
		this.con = con;
	}

	public void lista() throws SQLException {

		String sql = "select id_simulado, quantidade_de_questoes from simulado";
		
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					
				}
			}
		}
	}

	public ArrayList<String> getLista() {
		return lista;
	}
}
