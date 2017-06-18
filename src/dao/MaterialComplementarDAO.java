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
        
        String sql = "select m.descricao_material, materia.nome_materia from aluno_mc JOIN material_complementar as m ON aluno_mc.id_material_ref=m.id_material_complementar JOIN materia_mc ON m.id_material_complementar=materia_mc.id_material_ref JOIN materia ON materia.id_materia=materia_mc.id_materia_ref WHERE aluno_mc.id_aluno_ref=101;";
        		
        
        ArrayList<MaterialComplementar> lista = new ArrayList<MaterialComplementar>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                	MaterialComplementar mc = new MaterialComplementar();
                	mc.setTitulo(rs.getString("nome_materia"));
                	mc.setConteudo(rs.getString("descricao_material"));
                    lista.add(mc);
                }
            }
        }
        return lista;       
    }

}
