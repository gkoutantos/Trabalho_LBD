package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DesempenhoDAO {
	private Connection con;
	
    public DesempenhoDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<String> lista() throws SQLException{        
        
        String sql = "select (id_simulado_ref) from aluno_simulado WHERE id_aluno_ref = 101;";
        
        ArrayList<String> listaID = new ArrayList<String>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                    String nome = rs.getString("id_simulado_ref");
                    listaID.add(nome);                    
                }
            }
        }
        return listaID;       
    }
    public ArrayList<String> acertos() throws SQLException{        
        
        String sql = "select (rendimento) from aluno_simulado WHERE id_aluno_ref = 101;";
        
        ArrayList<String> listaREND = new ArrayList<String>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                    String nome = rs.getString("rendimento");
                    listaREND.add(nome);                    
                }
            }
        }
        return listaREND;       
    }
    public ArrayList<String> progresso() throws SQLException{        
        
        String sql = "select (progresso) from aluno_simulado WHERE id_aluno_ref = 101;";
        
        ArrayList<String> listaPROG = new ArrayList<String>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                    String nome = rs.getString("progresso");
                    listaPROG.add(nome);                    
                }
            }
        }
        return listaPROG;       
    }
}
