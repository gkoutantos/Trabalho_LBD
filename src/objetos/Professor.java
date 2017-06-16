package objetos;

import java.util.Date;

public class Professor extends Pessoa{
    
    private String instituicao_ensino;
    
    public Professor(int id_pessoa, String nome_pessoa, String telefone_pessoa, String email_pessoa, Date dt_nascimento_pessoa, String login, String senha, String instituicao_ensino){
        super(id_pessoa, nome_pessoa, telefone_pessoa, email_pessoa, dt_nascimento_pessoa, login, senha);
        this.instituicao_ensino = instituicao_ensino;
    }

    public String getInstituicao_ensino() {
        return instituicao_ensino;
    }

    public void setInstituicao_ensino(String instituicao_ensino) {
        this.instituicao_ensino = instituicao_ensino;
    }
}