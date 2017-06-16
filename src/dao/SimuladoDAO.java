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
        
        String sql = "select * from Simulado;";
        
        ArrayList<Simulado> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                                        
                    int id = rs.getInt("id_simulado");
                    int qnt_questoes = rs.getInt("quantidade_de_questoes");

                    Simulado s = new Simulado(id, qnt_questoes);
                    
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
