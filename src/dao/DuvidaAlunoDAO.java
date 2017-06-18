package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Duvidas;

public class DuvidaAlunoDAO {
	private Connection con;
    
    public DuvidaAlunoDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Duvidas duvida) throws SQLException{
    	ArrayList<String> lista = new ArrayList<String>();
    	String sql = "select id_duvida from duvidas";
    	try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                    lista.add(rs.getString("id_duvida"));
                }
            }
        }
        sql = "INSERT INTO duvidas(id_duvida, duvida, id_aluno_ref, titulo, id_materia_ref) VALUES (?, ?, ?, ?, ?)";             
        try(PreparedStatement stmt = con.prepareStatement(sql)){
        	stmt.setInt(1, (lista.size()+2));
            stmt.setString(2, duvida.getDuvida());
            stmt.setInt(3, 101);
            stmt.setString(4,  duvida.getTitulo());
            stmt.setInt(5, duvida.getId_materia());          
            stmt.execute();                        
        }catch (Exception e) {
			e.printStackTrace();
		}
    }
}
