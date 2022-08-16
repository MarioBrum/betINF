public class Cliente {
    private String nome;
    private String cpf;
    private double carteira; //dinheiro/saldo
    private String usuarioLogin;
    private String senhaLogin;

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.carteira = 0.00;

    }
    
    public Cliente(String nome, String cpf,String usuarioLogin,String senhaLogin){
        this.nome = nome;
        this.cpf = cpf;
        this.carteira = 0.00;
        this.usuarioLogin = usuarioLogin;
        this.senhaLogin = senhaLogin;

    }
    public void criarLogin(String usuarioLogin, String senhaLogin){
        this.usuarioLogin = usuarioLogin;
        this.senhaLogin = senhaLogin;
    }

    public void addCarteira(double valor){
        this.carteira = this.carteira + valor;
    }

    public void subCarteira(double valor){
        this.carteira = this.carteira - valor;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getNomeUsuario(){
        return usuarioLogin;
    }

    public String getSenhaUsuario(){
        return senhaLogin;
    }

    public double getCarteira(){
        return carteira;
    }
}
