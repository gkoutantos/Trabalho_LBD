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
        
        String sql = "select data_realizado, rendimento, progresso from aluno_simulado WHERE id_aluno_ref = 101;";
        
        ArrayList<Desempenho> lista = new ArrayList<Desempenho>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                	Desempenho desempenho = new Desempenho();
                	desempenho.setData(rs.getString("data_realizado"));
                    desempenho.setRendimento(rs.getString("rendimento"));
                    desempenho.setProgresso(rs.getString("progresso"));
                    lista.add(desempenho);
                }
            }
        }
        return lista;       
    }
    
  public void salva(Desempenho desempenho) throws SQLException{        
        
        String sql = "INSERT INTO aluno_simulado (id_aluno_ref, id_simulado_ref, progresso, rendimento, data_realizado) VALUES (?, ?, ?, ?, ?);";
      
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            
                	stmt.setInt(1, 101);
                	stmt.setInt(2, Integer.parseInt(desempenho.getId()));
                	stmt.setInt(3, Integer.parseInt(desempenho.getProgresso()));
                	stmt.setInt(4, Integer.parseInt(desempenho.getRendimento()));
                	stmt.setString(5, desempenho.getData());
                	stmt.execute();
                
            
        }       
    }
}
