package dao;

import objetos.Questoes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuestoesDAO {
    
    private Connection con;
    
    public QuestoesDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Questoes questoes) throws SQLException{
        
        String sql = "INSERT INTO Banco_de_Questos(Conteudo_questao, dificuldade, respota_certa, id_materia) VALUES (?, ?, ?, ?)";             
        String sql2 = "select id_materia from materia where nome_materia LIKE ?;";
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
        try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
            stmt.setString(1, questoes.getConteudo_questao());
            stmt.setInt(2, questoes.getDificuldade());
            stmt.setString(3,  questoes.getResposta_correta());
            
            stmt2.setString(1, questoes.getMateria());
            stmt2.executeQuery();
            
            try(ResultSet rs = stmt2.getResultSet() ){                     
                rs.next();
                int id = rs.getInt("Id_materia");
                stmt.setInt(4, id);      
            }            
            
            stmt.execute();                        
        }
        }
    }
    
    public ArrayList<Questoes> lista() throws SQLException{        
        
        String sql = "select * from Questoes as a join Materia as b on a.id_materia = b.id_materia;";
        ArrayList<Questoes> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                                        
                    int id = rs.getInt("Id_questao");
                    String conteudo = rs.getString("conteudo_questao");
                    int dificuldade = rs.getInt("dificuldade");
                    String resposta_correta = rs.getString("resposta_correta");
                    String nome_materia = rs.getString("nome_materia");

                    Questoes q = new Questoes(id, conteudo, dificuldade, resposta_correta, nome_materia);
                    
                    lista.add(q);                    
                }
            }
        }
        return lista;
        
    }
    
    public void alterar(Questoes questoes) throws SQLException{
        
        String sql = "UPDATE Banco_de_Questos SET Conteudo_questao = ?, dificuldade = ?, respota_certa = ?, id_materia = ? WHERE id_questao = ?";             
        String sql2 = "select id_materia from materia where nome_materia LIKE ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
        try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
            
            stmt.setString(1, questoes.getConteudo_questao());
            stmt.setInt(2, questoes.getDificuldade());
            stmt.setString(3,  questoes.getResposta_correta());
            
            stmt2.setString(1, questoes.getMateria());
            stmt2.executeQuery();
            
            try(ResultSet rs = stmt2.getResultSet() ){                     
                rs.next();
                int id = rs.getInt("Id_materia");
                stmt.setInt(4, id);      
            }            
            
            stmt.executeUpdate();                        
        }
        }
    }
    
    public void excluir(Questoes questoes) throws SQLException{
        String sql = "DELETE FROM Banco_de_Questoes WHERE id_questao = ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){                
            stmt.setInt(1, questoes.getId_questao());
            stmt.execute();
        }
    }
    
}
