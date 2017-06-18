package objetos;

public class Duvidas {
	private String titulo;
	private String duvida;
	private Integer id_materia;
	
	public Duvidas(){
    	
    }
    
    public Duvidas(String titulo, String duvida, Integer materia) {
        this.titulo = titulo;
        this.duvida = duvida;
        this.id_materia = materia;
    }

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDuvida() {
		return duvida;
	}

	public void setDuvida(String duvida) {
		this.duvida = duvida;
	}
	
	public Integer getId_materia() {
		return id_materia;
	}

	public void setId_materia(Integer id_materia) {
		this.id_materia = id_materia;
	}
}
