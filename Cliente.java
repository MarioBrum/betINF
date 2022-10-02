public class Cliente extends Usuario{
    private String cpf;
    private double carteira; //dinheiro/saldo

    public Cliente(String nome,String usuarioLogin,String senhaLogin, String cpf){
    	super(nome,usuarioLogin,senhaLogin);
        this.cpf = cpf;
        this.carteira = 0.00;

    }
    
    /*
    //metodo construtor vazio, apenas para nao ter comparacao de null
    //provavelmente nao usado, testar se codigo aposta funciona com null
    public Cliente(String nome, String usuarioLogin, String senhaLogin) {
    	super(nome,usuarioLogin,senhaLogin);
    }
    */

    public void addCarteira(double valor){
        this.carteira = this.carteira + valor;
    }

    public void subCarteira(double valor){
        this.carteira = this.carteira - valor;
    }

    public String getCpf(){
        return cpf;
    }

    public double getCarteira(){
        return carteira;
    }
    
    public String toString() {
    	return 
    			"\nNome do cliente: " + this.getNome() + "\n"
    			+ "Nome de usuario: " + this.getNomeUsuario() + "\n"
    			+ "CPF: " + this.cpf + "\n"
    			+ "Saldo: " + this.getCarteira() + "\n";
    }
}
