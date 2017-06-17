package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.MaterialComplementar;

public class MaterialComplementarDAO {
private Connection con;
	
    public MaterialComplementarDAO(Connection con) {
        this.con = con;
    }
    
    public ArrayList<MaterialComplementar> lista() throws SQLException{        
        
        String sql = "select b.descricao_material, t.nome_materia"
        		+ "from aluno_mc a INNER JOIN material_complementar b"
        		+ "ON a.id_material_ref=b.id_material_complementar"
        		+ "INNER JOIN materia_mc m"
        		+ "ON b.id_material_complementar=m.id_material_ref"
        		+ "INNER JOIN materia t"
        		+ "ON t.id_materia=m.id_materia_ref"
        		+ ";"; 
        
        ArrayList<MaterialComplementar> lista = new ArrayList<MaterialComplementar>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                	MaterialComplementar mc = new MaterialComplementar();
                	mc.setTitulo(rs.getString("id_material_complementar"));
                	mc.setConteudo(rs.getString("descricao_material"));
                    lista.add(mc);
                }
            }
        }
        return lista;       
    }

}
