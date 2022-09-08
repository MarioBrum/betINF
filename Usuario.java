
public class Usuario {
	private String nome;
    private String usuarioLogin;
    private String senhaLogin;
    
    public Usuario(String nome, String usuarioLogin,String senhaLogin) {
    	this.nome = nome;
    	this.usuarioLogin = usuarioLogin;
    	this.senhaLogin = senhaLogin;
    }
    
    public String getNome(){
        return nome;
    }

    public String getNomeUsuario(){
        return usuarioLogin;
    }

    public String getSenhaUsuario(){
        return senhaLogin;
    }
    
    //troca senha usuario
    public void setSenhaLogin(String senhaLogin) {
    	this.senhaLogin = senhaLogin;
    }
}


