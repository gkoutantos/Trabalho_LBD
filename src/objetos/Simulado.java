package objetos;

import java.util.ArrayList;

public class Simulado {
    
    private int id_simulado;
    private int qnt_questoes;
    private ArrayList<String> nome_materia = new ArrayList<String>();
 

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
    
    public ArrayList<String> getId_materias() {
        return nome_materia;
    }

    public void set_materia (String materia) {
    	this.nome_materia.add(materia);
    }
}
