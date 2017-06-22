package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Simulado;
import objetos.SimuladoBQ;

public class MontarSimuladoDAO {
private Connection con;
    
    public MontarSimuladoDAO(Connection con) {
        this.con = con;
    }
    
    public void salvaSimulado(Simulado simulado) throws SQLException{
    	ArrayList<String> lista = new ArrayList<String>();
    	String sql = "select id_simulado from simulado";
    	try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.executeQuery();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                    lista.add(rs.getString("id_simulado"));
                }
            }
        }
    	
    	sql = "INSERT INTO simulado(id_simulado, quantidade_de_questoes) VALUES (?,?)";
    	//ArrayList<String> nome_materia = new ArrayList<String>();
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, lista.size() + 100);
            simulado.setId_simulado(lista.size() + 100);
            stmt.setInt(2, simulado.getQnt_questoes());
            stmt.execute();  
        }

//        sql = "INSERT INTO simulado_materia(id_simulado_ref, id_materia_ref) VALUES (?, ?)";
//
//        try(PreparedStatement stmt2 = con.prepareStatement(sql)){
//     	   String sql2 = "select id_materia from materia where nome_materia=?;";
//     	   nome_materia = simulado.get_materia();
//     	   int cont = 0;
//     	   try(PreparedStatement stmt3 = con.prepareStatement(sql2)){
//     		   while(cont < nome_materia.size()){
//     			   stmt3.setString(1, nome_materia.get(cont));
//     			   stmt3.executeQuery();
//     			   try(ResultSet rs = stmt3.getResultSet() ){
//     				   stmt2.setInt(1, simulado.getId_simulado());
//     				   stmt2.setInt(2, rs.getInt("id_materia"));
//     				   stmt2.execute();  
//     				   cont++;
//     			   }               
//                    
//     		   }
//                                   
//            }
//        }
//        
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
