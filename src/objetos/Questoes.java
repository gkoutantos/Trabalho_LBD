package objetos;

public class Questoes {
    
    private int id_questao;
    private String Conteudo_questao;
    private int dificuldade;
    private String resposta_correta;
    private Integer id_materia;
    
    public Questoes(){
    	
    }
    
    public Questoes(int id_questao, String Conteudo_questao, int dificuldade, String resposta_correta, Integer materia) {
        this.id_questao = id_questao;
        this.Conteudo_questao = Conteudo_questao;
        this.dificuldade = dificuldade;
        this.resposta_correta = resposta_correta;
        this.id_materia = materia;
    }

    public int getId_questao() {
        return id_questao;
    }

    public void setId_questao(int id_questao) {
        this.id_questao = id_questao;
    }

    public String getConteudo_questao() {
        return Conteudo_questao;
    }

    public void setConteudo_questao(String Conteudo_questao) {
        this.Conteudo_questao = Conteudo_questao;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getResposta_correta() {
        return resposta_correta;
    }

    public void setResposta_correta(String resposta_correta) {
        this.resposta_correta = resposta_correta;
    }

	public Integer getId_materia() {
		return id_materia;
	}

	public void setId_materia(Integer id_materia) {
		this.id_materia = id_materia;
	}
}
