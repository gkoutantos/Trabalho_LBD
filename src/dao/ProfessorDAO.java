package dao;

import objetos.Endereco;
import objetos.Professor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProfessorDAO {
    
    private Connection con;
    
    public ProfessorDAO(Connection con) {
        this.con = con;
    }
    
    public void salva(Professor professor, Endereco endereco) throws SQLException{
        String sql = "INSERT INTO Pessoa(nome_pessoa, telefone_pessoa, email_pessoa, dt_nascimento_pessoa, login, senha) VALUES (?, ?, ?, ?, ?, ?)";             
        try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, professor.getNome_pessoa());
            stmt.setString(2, professor.getTelefone_pessoa());
            stmt.setString(3,  professor.getEmail_pessoa());
            stmt.setDate(4, (Date) professor.getDt_nascimento_pessoa());
            stmt.setString(5, professor.getLogin());
            stmt.setString(6, professor.getSenha());
            
            stmt.execute();
            
            try(ResultSet rs = stmt.getGeneratedKeys()){            
                if(rs.next()){
                    int id = rs.getInt(1);                    
                    professor.setId_pessoa(id);                                                                          
                }
            }
        }
        
        String sql2 = "INSERT INTO Professor(id_pessoa, instituicao_de_ensino) VALUES(?, ?)";        
        try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
            
            stmt2.setInt(1, professor.getId_pessoa());
            stmt2.setString(2, professor.getInstituicao_ensino());            
            
            stmt2.execute();            
        }
        
        String sql3 = "INSERT INTO Endereco(cep, nome_rua, numero_residencia, bairro, cidade, estado, complemento, id_pessoa) VALUE(?, ?, ?, ?, ?, ?, ? ,?)";        
        try(PreparedStatement stmt3 = con.prepareStatement(sql3)){
            stmt3.setInt(1, endereco.getCep());
            stmt3.setString(2, endereco.getNome_rua());
            stmt3.setInt(3,  endereco.getNumero_residencia());
            stmt3.setString(4, endereco.getBairro());
            stmt3.setString(5, endereco.getCidade());
            stmt3.setString(6, endereco.getEstado());
            stmt3.setString(7, endereco.getComplemento());
            stmt3.setInt(8, professor.getId_pessoa());
            
            stmt3.execute();
        }
    }
    
    public ArrayList<Professor> lista() throws SQLException{        
        
        String sql = "select * from Pessoa as a join Professor as b on a.id_pessoa = b.id_pessoa;";
        ArrayList<Professor> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.execute();
            
            try(ResultSet rs = stmt.getResultSet() ){
                while(rs.next()){
                                        
                    int id = rs.getInt("Id_pessoa");
                    String nome = rs.getString("nome_pessoa");
                    String telefone = rs.getString("telefone_pessoa");
                    String email = rs.getString("e_mail_pessoa");
                    Date dtNascimento = rs.getDate("data_nascimento_pessoa");
                    String Login = rs.getString("Login");
                    String Senha = rs.getString("Senha");
                    String instituicao = rs.getString("instituicao_de_ensino");
                    
                    
//                    String cep = rs.getString("Sexo_Aluno");
//                    String nome_rua = rs.getString("id_cursoFK");
//                    String num_resid = rs.getString("dt_inicio");
//                    String bairro = rs.getString("dt_inicio");
//                    String cidade = rs.getString("dt_inicio");
//                    String estado = rs.getString("dt_inicio");
//                    String complemento = rs.getString("dt_inicio");

                    Professor p = new Professor(id, nome, telefone, email, dtNascimento, Login, Senha, instituicao);
                    
                    lista.add(p);                    
                }
            }
        }
        return lista;
        
    }
    
    public void alterar(Professor professor, Endereco endereco) throws SQLException{
        
        String sql = "UPDATE Pessoa SET nome_pessoa = ?, telefone_pessoa = ?, email_pessoa = ?, dt_nascimento_pessoa = ?, login = ?, senha = ? where id_pessoa = ?;";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, professor.getNome_pessoa());
            stmt.setString(2, professor.getTelefone_pessoa());
            stmt.setString(3,  professor.getEmail_pessoa());
            stmt.setDate(4, (Date) professor.getDt_nascimento_pessoa());
            stmt.setString(5, professor.getLogin());
            stmt.setString(6, professor.getSenha());
            stmt.setInt(7, professor.getId_pessoa());
                                    
            stmt.executeUpdate();
           
        }
        
        String sql2 = "UPDATE Professor SET indice_aproveitamento = ?, curso_pretendido = ?;";
        try(PreparedStatement stmt2 = con.prepareStatement(sql2)){
                        
            stmt2.setString(1, professor.getInstituicao_ensino());            
            
            stmt2.executeUpdate();
            
        }
        
        String sql3 = "UPDATE Endereco SET nome_rua = ?, numero_residencia = ?, bairro = ?, cidade = ?, estado = ?, complemento = ? WHERE cep = ?";
        try(PreparedStatement stmt3 = con.prepareStatement(sql3)){            
            stmt3.setString(1, endereco.getNome_rua());
            stmt3.setInt(2,  endereco.getNumero_residencia());
            stmt3.setString(3, endereco.getBairro());
            stmt3.setString(4, endereco.getCidade());
            stmt3.setString(5, endereco.getEstado());
            stmt3.setString(6, endereco.getComplemento());
            stmt3.setInt(7, endereco.getCep());
            
            stmt3.executeUpdate();
        }
        
    }
    
    public void excluir(Professor professor) throws SQLException{
        String sql = "DELETE FROM Pessoa WHERE id_pessoa = ?;";        
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){                
            stmt.setInt(1, professor.getId_pessoa());                
            stmt.execute();        
        }
    }
}
