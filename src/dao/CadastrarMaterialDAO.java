package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objetos.CadastroMaterial;

public class CadastrarMaterialDAO {
	 private Connection con;
	    
	    public CadastrarMaterialDAO(Connection con) {
	        this.con = con;
	    }
	    public void salva(CadastroMaterial cadastro) throws SQLException{
	        
	        String sql = "INSERT INTO material_complementar(descricao_material,valor_material) VALUES (?,?)";
	        try(PreparedStatement stmt = con.prepareStatement(sql)){
	            stmt.setString(1, cadastro.getDescricao());
	            stmt.setInt(2, cadastro.getValor());
	            stmt.execute();
	        }catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
