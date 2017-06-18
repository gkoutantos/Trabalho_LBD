package objetos;

import java.util.List;

public class SimuladoBQ {
	
	private Integer id_simulado_ref;
	private List<Integer> id_questao_ref;
	
	public SimuladoBQ(){
		
	}

	public Integer getId_simulado_ref() {
		return id_simulado_ref;
	}

	public void setId_simulado_ref(Integer id_simulado_ref) {
		this.id_simulado_ref = id_simulado_ref;
	}

	public List<Integer> getId_questao_ref() {
		return id_questao_ref;
	}

	public void setId_questao_ref(List<Integer> id_questao_ref) {
		this.id_questao_ref = id_questao_ref;
	}
}
