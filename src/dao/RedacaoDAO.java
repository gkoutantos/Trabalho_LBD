package dao;

import objetos.Redacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RedacaoDAO {
    
    private Connection con;
    
    public RedacaoDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Redacao redacao) throws SQLException{
        String sql = "INSERT INTO Redacao(id_pessoa, conteudo, tipo, tema, titulo) VALUES (?, ?, ?, ?, ?)";
        String sql2 = "select id_pessoa from Aluno where id_pessoa LIKE ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
        try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
            
            stmt2.setInt(1, redacao.getId_aluno());//MODIFICAR O redacao.getId_aluno() PARA INFOMRAÇÃO ENVIADA DA INTERFACE;
            stmt2.executeQuery();
            
            try(ResultSet rs = stmt2.getResultSet() ){                     
                rs.next();
                int id = rs.getInt("Id_pessoa");
                stmt.setInt(1, id);
            }            
            
            
            stmt.setString(2, redacao.getConteudo());
            stmt.setString(3, redacao.getTipo());
            stmt.setString(4,  redacao.getTema());
            stmt.setString(5,  redacao.getTitulo());
            
            stmt.execute();
        }
        }
    }
    
    public ArrayList<Redacao> lista() throws SQLException{        
        String sql = "select * from Redacao;";
        ArrayList<Redacao> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                    
                    int id = rs.getInt("Id_redacao");
                    int id_pessoa = rs.getInt("id_pessoa");
                    String conteudo = rs.getString("conteudo");
                    String tipo = rs.getString("tipo");
                    String tema = rs.getString("tema");
                    String titulo = rs.getString("titulo");

                    Redacao r = new Redacao(id, id_pessoa, conteudo, tipo, tema, titulo);
                    
                    lista.add(r);
                }
            }
        }
        return lista;
    }
    
    public void alterar(Redacao redacao) throws SQLException{
        
        String sql = "UPDATE Redacao id_pessoa = ?, conteudo = ?, tipo = ?, tema = ?, titulo = ? WHERE id_redacao = ?;";
        String sql2 = "select id_pessoa from Aluno where id_pessoa LIKE ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
        try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
            
            stmt2.setInt(1, redacao.getId_aluno());//MODIFICAR O redacao.getId_aluno() PARA INFOMRAÇÃO ENVIADA DA INTERFACE;
            stmt2.executeQuery();
            
            try(ResultSet rs = stmt2.getResultSet() ){
                rs.next();
                int id = rs.getInt("Id_pessoa");
                stmt.setInt(1, id);
            }                        
            
            stmt.setString(2, redacao.getConteudo());
            stmt.setString(3, redacao.getTipo());
            stmt.setString(4, redacao.getTema());
            stmt.setString(5, redacao.getTitulo());
            stmt.setInt(6, redacao.getId_redacao());
            
            stmt.executeUpdate();
        }
        }        
        
    }
    
    public void excluir(Redacao redacao) throws SQLException{
        String sql = "DELETE FROM Redacao WHERE id_redacao = ?;";        
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){                
            stmt.setInt(1, redacao.getId_redacao());                
            stmt.execute();
        }
    }
    
}
