package dao;

import objetos.Questoes;
import objetos.Simulado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

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
        String sql = "select s.id_simulado, s.quantidade_de_questoes from simulado;";
        String sql2 = "select m.nome_materia simulado_materia as sm JOIN materia as m ON m.id_materia = sm.id_materia_ref where sm.id_simulado_ref = ?;";
           
           ArrayList<Simulado> lista = new ArrayList();        
           
           try(PreparedStatement stmt = con.prepareStatement(sql)){
           try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
               stmt.execute();
               
               try(ResultSet rs = stmt.getResultSet() ){
                int id;
                Simulado s = new Simulado();
                   while(rs.next()){ 
                     id = rs.getInt("id_simulado");
                     s.setId_simulado(rs.getInt("id_simulado"));
                     s.setQnt_questoes(rs.getInt("quantidade_de_questoes"));
                     
                     
                     stmt2.setInt(1, id);
                     stmt2.execute();                                    
                     try(ResultSet rs2 = stmt2.getResultSet() ){
                         while(rs.next()){ 
                             s.set_materia(rs.getString("nome_materia"));
                         }
                     }
                     
                     lista.add(s);
                   }                
               }
           }
           }
           return lista;                
       }
    
	public ArrayList<Questoes> lista(String materia) throws SQLException {

		String sql = "select * from banco_de_questoes where id_materia_ref = ?;";
		String sql2 = "select id_materia from materia where nome_materia LIKE ?;";

		ArrayList<Questoes> lista = new ArrayList();

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (PreparedStatement stmt2 = con.prepareStatement(sql2)) {

				stmt2.setString(1, materia);
				stmt2.executeQuery();

				try (ResultSet rs = stmt2.getResultSet()) {
					rs.next();
					int id = rs.getInt("id_materia");
					stmt.setInt(1, id);
				}

				stmt.execute();

				try (ResultSet rs = stmt.getResultSet()) {
					while (rs.next()) {

						int id = rs.getInt("id_questao");
						String conteudo_questao = rs.getString("conteudo_questao");
						int dificuldade = rs.getInt("dificuldade_questao");
						String resposta_correta = rs.getString("resposta_correta");
						int id_materia = rs.getInt("id_materia_ref");

						Questoes q = new Questoes();
						
						q.setId_questao(id);
						q.setConteudo_questao(conteudo_questao);
						q.setDificuldade(dificuldade);
						q.setResposta_correta(resposta_correta);
						q.setId_materia(id_materia);

						lista.add(q);
					}
				}
			}
		}

		Collections.shuffle(lista);

		ArrayList<Questoes> lista_10 = new ArrayList();

		for (int i = 0; i <= 9; i++)
			lista_10.add(lista.get(i));

		return lista_10;
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
