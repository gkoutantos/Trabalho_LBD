package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.CadastroMaterial;

public class CadastrarMaterialDAO {
	 private Connection con;
	    
	    public CadastrarMaterialDAO(Connection con) {
	        this.con = con;
	    }
	    public void salva(CadastroMaterial cadastro) throws SQLException{
	    	ArrayList<String> lista = new ArrayList<String>();
	    	String sql = "select id_material_complementar from material_complementar";
	    	try(PreparedStatement stmt = con.prepareStatement(sql)){
	            stmt.execute();
	            
	            try(ResultSet rs = stmt.getResultSet() ){
	                while(rs.next()){
	                    lista.add(rs.getString("id_material_complementar"));
	                }
	            }
	        }
	        sql = "INSERT INTO material_complementar(id_material_complementar, descricao_material,valor_material) VALUES (?, ?,?)";
	        try(PreparedStatement stmt = con.prepareStatement(sql)){
	            stmt.setInt(1, lista.size()+1);
	            stmt.setString(2, cadastro.getDescricao());
	            stmt.setInt(3, cadastro.getValor());
	            stmt.execute();
	        }catch (Exception e) {
				e.printStackTrace();
			}
	        sql = "INSERT INTO materia_mc(id_materia_ref, id_material_ref) VALUES (?,?)";
	        try(PreparedStatement stmt = con.prepareStatement(sql)){
	            stmt.setInt(1, cadastro.getId_materia());
	            stmt.setInt(2, lista.size()+1);
	            stmt.execute();
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
