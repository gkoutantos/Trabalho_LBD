package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Simulado;

public class SimuladoProntoDAO {
	private Connection con;
	private ArrayList<String> lista = new ArrayList<String>();

	public SimuladoProntoDAO(Connection con) {
		this.con = con;
	}

//	public void lista() throws SQLException {
//
//		String sql = "select id_simulado, quantidade_de_questoes from simulado";
//		
//		try (PreparedStatement stmt = con.prepareStatement(sql)) {
//			stmt.execute();
//
//			try (ResultSet rs = stmt.getResultSet()) {
//				while (rs.next()) {
//					
//				}
//			}
//		}
//	}
	
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
                  lista.add(s);
                }
                
                for(int i = 0; i <= lista.size(); i++)
                {
                    if( lista.get(i + 1) != null){
                        if(lista.get(i + 1).getId_simulado() == lista.get(i).getId_simulado()){
                            lista.remove(i);
                        }
                    }
                }
            }
        }
        return lista;                
    }

	public ArrayList<String> getLista() {
		return lista;
	}
}
