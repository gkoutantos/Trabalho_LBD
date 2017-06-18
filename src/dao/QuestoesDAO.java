package dao;

import objetos.Questoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestoesDAO {
    
    private Connection con;
    
    public QuestoesDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Questoes questoes) throws SQLException{
    	ArrayList<String> lista = new ArrayList<String>();
    	//String sql = "select id_questao from banco_de_questoes";
        String sql = "INSERT INTO banco_de_questoes(id_questao, conteudo_questao, dificuldade_questao, resposta_correta, id_materia_ref) VALUES (140, ?, ?, ?, ?)";             
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, questoes.getConteudo_questao());
            stmt.setInt(2, questoes.getDificuldade());
            stmt.setString(3,  questoes.getResposta_correta());
            stmt.setInt(4, questoes.getId_materia());          
            stmt.execute();                        
        }
    }
    
    public ArrayList<Questoes> lista() throws SQLException{        
        
        String sql = "select * from banco_de_questoes join materia on id_materia = id_materia_ref;";
        ArrayList<Questoes> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                                        
                    int id = rs.getInt("id_questao");
                    String conteudo = rs.getString("conteudo_questao");
                    int dificuldade = rs.getInt("dificuldade_questao");
                    String resposta_correta = rs.getString("resposta_correta");
                    Integer id_materia = rs.getInt("id_materia");

                    Questoes q = new Questoes(id, conteudo, dificuldade, resposta_correta, id_materia);
                    
                    lista.add(q);                    
                }
            }
        }
        return lista;
        
    }
    
    public void excluir(Questoes questoes) throws SQLException{
        String sql = "DELETE FROM Banco_de_Questoes WHERE id_questao = ?;";
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){                
            stmt.setInt(1, questoes.getId_questao());
            stmt.execute();
        }
    }
    
}
