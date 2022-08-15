public class Cliente {
    private String nome;
    private int cpf;
    private float carteira; //dinheiro/saldo
    private String usuarioLogin;
    private String senhaLogin;

    public Cliente(String nome, int cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.carteira = 0;

    }
    
    public void criarLogin(String usuarioLogin, String senhaLogin){
        this.usuarioLogin = usuarioLogin;
        this.senhaLogin = senhaLogin;
    }

    public void addCarteira(float valor){
        this.carteira = this.carteira + valor;
    }

    public void subCarteira(float valor){
        this.carteira = this.carteira - valor;
    }

    public String getNome(){
        return nome;
    }

    public int getCpf(){
        return cpf;
    }

    public float getCarteira(){
        return carteira;
    }

    //nesse metodo? provavelmente no model
    public boolean confirmaLogin(String usuario, String senha){
        if(usuario.equals(usuarioLogin) && senha.equals(senhaLogin)){
            return true;
        }
        else{
            return false;
        }

    }
}
