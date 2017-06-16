package objetos;

public class Redacao {
    
    private int id_redacao;
    private int id_aluno;
    private String conteudo;
    private String tipo;
    private String tema;
    private String titulo;

    public Redacao(int id_redacao,int id_aluno, String conteudo, String tipo, String tema, String titulo) {
        this.id_redacao = id_redacao;
        this.id_aluno = id_aluno;      
        this.conteudo = conteudo;
        this.tipo = tipo;
        this.tema = tema;
        this.titulo = titulo;
    }

    public int getId_redacao() {
        return id_redacao;
    }

    public void setId_redacao(int id_redacao) {
        this.id_redacao = id_redacao;
    }    

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    
}
