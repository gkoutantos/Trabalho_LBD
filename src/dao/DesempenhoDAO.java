package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Desempenho;

public class DesempenhoDAO {
	private Connection con;
	
    public DesempenhoDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<Desempenho> lista() throws SQLException{        
        
        String sql = "select id_simulado_ref, rendimento, progresso from aluno_simulado WHERE id_aluno_ref = 101;";
        
        ArrayList<Desempenho> lista = new ArrayList<Desempenho>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                	Desempenho desempenho = new Desempenho();
                	desempenho.setId(rs.getString("id_simulado_ref"));
                    desempenho.setRendimento(rs.getString("rendimento"));
                    desempenho.setProgresso(rs.getString("progresso"));
                    lista.add(desempenho);
                }
            }
        }
        return lista;       
    }
}
