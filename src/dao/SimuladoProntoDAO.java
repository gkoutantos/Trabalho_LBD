package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SimuladoProntoDAO {
private Connection con;
    
    public SimuladoProntoDAO(Connection con) {
        this.con = con;
    }
    
 public ArrayList<String> lista() throws SQLException{        
        
        String sql = "select quantidade_de_questoes from simulado";
        
        ArrayList<String> lista = new ArrayList<String>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                }
            }
        }
        return lista;       
    }
}
