package objetos;

public class Materia {
    
    private int id_materia;
    private String nome_materia;

    public Materia(int id_materia, String nome_materia) {
        this.id_materia = id_materia;
        this.nome_materia = nome_materia;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNome_materia() {
        return nome_materia;
    }

    public void setNome_materia(String nome_materia) {
        this.nome_materia = nome_materia;
    }
    
    
}
