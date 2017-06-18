package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objetos.Simulado;
import objetos.SimuladoBQ;

public class MontarSimuladoDAO {
private Connection con;
    
    public MontarSimuladoDAO(Connection con) {
        this.con = con;
    }
    
    public void salvaSimulado(Simulado simulado) throws SQLException{
    	String sql = "INSERT INTO simulado(quantidade_de_questoes) VALUES (?) returning id_simulado";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, simulado.getQnt_questoes());
            stmt.execute();  
            try(ResultSet rs = stmt.executeQuery()){            
                if(rs.next()){
                    int id = rs.getInt("id_simulado");                    
                    simulado.setId_simulado(id);                                                             
                }
            }
        }
    }
    
    public void salvaSimuladoBQ(SimuladoBQ simuladoBQ) throws SQLException{
    	String sql = "INSERT INTO simulado_bq(id_simulado_ref, id_questao_ref) VALUES (?,?)";
    	
    	 try(PreparedStatement stmt = con.prepareStatement(sql)){
    		 int cont = 0;
    		 while(cont < simuladoBQ.getId_questao_ref().size()){
    			 stmt.setInt(1, simuladoBQ.getId_simulado_ref());
	             stmt.setInt(2, simuladoBQ.getId_questao_ref().get(cont));
	             stmt.execute();
	             cont++;
    		 }
         }
    }
}
