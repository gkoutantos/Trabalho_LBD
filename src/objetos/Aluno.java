package objetos;

import java.util.Date;

public class Aluno extends Pessoa{
    
    private String indice_aproveitamento;
    private String curso_pretendido;
    
    public Aluno(int id_pessoa, String nome_pessoa, String telefone_pessoa, String email_pessoa, Date dt_nascimento_pessoa, String login, String senha, String I_A, String C_P) {
        
        super(id_pessoa, nome_pessoa, telefone_pessoa, email_pessoa, dt_nascimento_pessoa, login, senha);
        this.indice_aproveitamento = I_A;
        this.curso_pretendido = C_P;
        
    }

    public String getIndice_aproveitamento() {
        return indice_aproveitamento;
    }
    public void setIndice_aproveitamento(String indice_aproveitamento) {
        this.indice_aproveitamento = indice_aproveitamento;
    }

    public String getCurso_pretendido() {
        return curso_pretendido;
    }
public void setCurso_pretendido(String curso_pretendido) {
        this.curso_pretendido = curso_pretendido;
    }
}