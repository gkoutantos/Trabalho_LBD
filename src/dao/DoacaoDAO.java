package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objetos.Doacao;

public class DoacaoDAO {
	 private Connection con;
	    
	    public DoacaoDAO(Connection con) {
	        this.con = con;
	    }
	    
	    public void salva(Doacao doacao) throws SQLException{
	        
	        String sql = "INSERT INTO doacao(valor_doacao,id_aluno_ref) VALUES (?,?)";
	        try(PreparedStatement stmt = con.prepareStatement(sql)){
	            stmt.setInt(1, doacao.getValor());
	            stmt.setInt(2, 101);
	            stmt.execute();
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
