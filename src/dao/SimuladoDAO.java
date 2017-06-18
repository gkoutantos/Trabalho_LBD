package dao;

import objetos.Simulado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SimuladoDAO {
    
    private Connection con;
    
    public SimuladoDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Simulado simulado) throws SQLException{
        
        String sql = "INSERT INTO Simulado(qnt_questoes) VALUES (?)";
        
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, simulado.getQnt_questoes());
            
            stmt.execute();                        
        }
    }
    
    public ArrayList<Simulado> lista() throws SQLException{        
        
    	String sql = "select s.id_simulado, m.nome_materia, s.quantidade_de_questoes from simulado as s JOIN simulado_materia as sm ON s.id_simulado=sm.id_simulado_ref JOIN materia as m ON m.id_materia=sm.id_materia_ref;";
        
        ArrayList<Simulado> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
            	int id;
            	Simulado s = new Simulado();
                while(rs.next()){ 
                  id = rs.getInt("id_simulado");
                  s.setId_simulado(rs.getInt("id_simulado"));
                  s.setQnt_questoes(rs.getInt("quantidade_de_questoes"));
                  s.set_materia(rs.getString("nome_materia"));
                 while(rs.next()){
                	if(rs.getInt("id_simulado")==id){
                		s.set_materia(rs.getString("nome_materia"));
                	}
                	else{
                	}
                 }
                   
                    lista.add(s);                    
                }
            }
        }
        return lista;
                
    }
    
    public void alterar(Simulado simulado) throws SQLException{
        
        String sql = "UPDATE Simulado SET quantidade_de_questoes = ? WHERE id_simulado = ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setInt(1, simulado.getQnt_questoes());
            stmt.setInt(2, simulado.getId_simulado());
            
            stmt.executeUpdate();
        }
    }
    
    public void excluir(Simulado simulado) throws SQLException{
        String sql = "DELETE FROM simulado WHERE id_simulado = ?;";        
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){                
            stmt.setInt(1, simulado.getId_simulado());                
            stmt.execute();
        }
    }   
}
