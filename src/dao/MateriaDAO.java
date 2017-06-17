package dao;

import objetos.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MateriaDAO {
 
    private Connection con;
    
    public MateriaDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Materia materia) throws SQLException{
        
        String sql = "INSERT INTO Materia(nome_materia) VALUES (?)";
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, materia.getNome_materia());
            
            stmt.execute();                        
        }
    }
    
    public ArrayList<Materia> lista() throws SQLException{        
        
        String sql = "select * from Materia;";
        
        ArrayList<Materia> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                                        
                    int id = rs.getInt("id_materia");
                    String nome = rs.getString("nome_materia");

                    Materia m = new Materia(id, nome);
                    
                    lista.add(m);                    
                }
            }
        }
        return lista;
                
    }
    
    public void alterar(Materia materia) throws SQLException{
        
        String sql = "UPDATE Materia SET nome_materia = ? WHERE id_materia = ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, materia.getNome_materia());
            stmt.setInt(2, materia.getId_materia());
            
            stmt.executeUpdate();
        }
        
    }
    
    public void excluir(Materia materia) throws SQLException{
        String sql = "DELETE FROM materia WHERE id_materia = ?;";        
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){                
            stmt.setInt(1, materia.getId_materia());                
            stmt.execute();
        }
    }
    
}
