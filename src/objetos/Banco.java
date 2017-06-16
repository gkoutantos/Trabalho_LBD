package objetos;

public class Banco {

    private String usuario;
    private String senha;
    private static Banco instancia;

    private Banco(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    private Banco() {}
    
    public static Banco getInstance(){
        if(instancia == null)
            instancia = new Banco();
        
        return instancia;
    }
    
    public static Banco getInstance(String usuario, String senha){
        if(instancia == null)
            instancia = new Banco(usuario, senha);
        
        return instancia;
    }

    public String getUsuario() {
        return usuario;
    }
    public String getSenha() {
        return senha;
    }
}