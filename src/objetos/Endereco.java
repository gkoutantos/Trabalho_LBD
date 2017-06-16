package objetos;

public class Endereco {
    
    private int cep;
    private String nome_rua;
    private int numero_residencia;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    
    public Endereco (int cep, String nome_rua, int numero_residencia, String bairro, String cidade, String estado, String complemento){
        this.cep = cep;
        this.nome_rua = nome_rua;
        this.numero_residencia = numero_residencia;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }

    public int getCep() {
        return cep;
    }
    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getNome_rua() {
        return nome_rua;
    }
    public void setNome_rua(String nome_rua) {
        this.nome_rua = nome_rua;
    }

    public int getNumero_residencia() {
        return numero_residencia;
    }
    public void setNumero_residencia(int numero_residencia) {
        this.numero_residencia = numero_residencia;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}