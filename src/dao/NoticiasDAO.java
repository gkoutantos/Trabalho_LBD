package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoticiasDAO {
	 private Connection con;
	    
	    public NoticiasDAO(Connection con) {
	        this.con = con;
	    }
	    
	    public ArrayList<String> lista() throws SQLException{        
	        
	        String sql = "select (noticia) from noticias;";
	        
	        ArrayList<String> lista = new ArrayList<String>();
	        
	        try(PreparedStatement stmt = con.prepareStatement(sql)){
	            stmt.execute();
	            
	            try(ResultSet rs = stmt.getResultSet() ){
	                while(rs.next()){
	                    String nome = rs.getString("noticia");
	                    lista.add(nome);                    
	                }
	            }
	        }
	        return lista;       
	    }
}
