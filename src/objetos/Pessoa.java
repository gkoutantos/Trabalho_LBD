package objetos;

import java.util.Date;

public class Pessoa {
    private int id_pessoa;
    private String nome_pessoa;
    private String telefone_pessoa;
    private String email_pessoa;
    private Date dt_nascimento_pessoa;
    private String login;
    private String senha;
    
    public Pessoa(int id_pessoa, String nome_pessoa, String telefone_pessoa, String email_pessoa, Date dt_nascimento_pessoa, String login, String senha){
        this.id_pessoa = id_pessoa;
        this.nome_pessoa = nome_pessoa;
        this.telefone_pessoa = telefone_pessoa;
        this.email_pessoa = email_pessoa;
        this.dt_nascimento_pessoa = dt_nascimento_pessoa;
        this.login = login;
        this.senha = senha;        
    }            
    
    public int getId_pessoa() {
        return id_pessoa;
    }
    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }
    public void setNome_pessoa(String nome_pessoa) {
        this.nome_pessoa = nome_pessoa;
    }

    public String getTelefone_pessoa() {
        return telefone_pessoa;
    }
    public void setTelefone_pessoa(String telefone_pessoa) {
        this.telefone_pessoa = telefone_pessoa;
    }

    public String getEmail_pessoa() {
        return email_pessoa;
    }
    public void setEmail_pessoa(String email_pessoa) {
        this.email_pessoa = email_pessoa;
    }

    public Date getDt_nascimento_pessoa() {
        return dt_nascimento_pessoa;
    }
    public void setDt_nascimento_pessoa(Date dt_nascimento_pessoa) {
        this.dt_nascimento_pessoa = dt_nascimento_pessoa;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }    
    
}