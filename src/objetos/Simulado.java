package objetos;

public class Simulado {
    
    private int id_simulado;
    private int qnt_questoes;

    public Simulado(){
    	
    }
    
    public Simulado(int id_simulado, int qnt_questoes) {
        this.id_simulado = id_simulado;
        this.qnt_questoes = qnt_questoes;
    }

    public int getId_simulado() {
        return id_simulado;
    }

    public void setId_simulado(int id_simulado) {
        this.id_simulado = id_simulado;
    }

    public int getQnt_questoes() {
        return qnt_questoes;
    }

    public void setQnt_questoes(int qnt_questoes) {
        this.qnt_questoes = qnt_questoes;
    }
}
