import java.util.ArrayList;

public class DemoModel {

    //private static DemoView dv;
    private static ArrayList<Cliente> clientesCadastrados;
    private static ArrayList<Admin> administradoresCadastrados;
    private static ArrayList<Aposta> apostasAbertas;
    private static ArrayList<ApostaCompleta> apostasConfirmadas;
    //private static ArrayList<JogoResultado> resultadoAtualizado;
    public DemoModel(){
    	clientesCadastrados = new ArrayList<Cliente>();
    	administradoresCadastrados = new ArrayList<Admin>();
    	administradoresCadastrados.add(new Admin ("admin","admin","admin"));
    	clientesCadastrados.add(new Cliente ("Mario","mario1","senha1","10101010101"));
    }
    
    //adiciona uma aposta composta/completa(com cliente2/fechada)
    public boolean addAposta(ApostaCompleta aposta)
    {
    	return apostasConfirmadas.add(aposta);
    }

    //arrumas isso, valor da aposta verificacao e erro de view/controller/model
    //**************** importanteeee
    //adiciona uma aposta simples(sem cliente2/aberta)
    public boolean addAposta(Aposta aposta)
    {
    	return apostasAbertas.add(aposta);
    }
    //fazer cadastro cliente
    public boolean addCliente(Cliente cliente){
        return clientesCadastrados.add(cliente);
    }
    
    /*
    public boolean depositoCliente(Cliente cliente,double valor) {
    	
    }
    */

    //remover cliente
    //nao usado
    public boolean removeClientePeloCPF(String cpf){
        for (Cliente cliente : clientesCadastrados) {
            if(cliente.getCpf() == cpf){
                clientesCadastrados.remove(cliente);
                return true;
            }
        }
        return false;
    }
    
    //verifica se existe usuario com tal login
    public Usuario confirmaUsuario(String usuarioNome){
        for(Cliente cliente : clientesCadastrados){
            if(cliente.getNomeUsuario().equals(usuarioNome)){
                return cliente;
            }
        }
        for(Admin adm : administradoresCadastrados){
            if(adm.getNomeUsuario().equals(usuarioNome)){
                return adm;
            }
        }
        return null;
    }

    public Usuario confirmaLogin(String usuarioLogin, String usuarioSenha) {
        Usuario usuario = confirmaUsuario(usuarioLogin);
        if(usuario != null){
            if(usuario.getNomeUsuario().equals(usuarioLogin) && usuario.getSenhaUsuario().equals(usuarioSenha)){
                return usuario;
            }
        }
        return null;
    }

    //retorna uma ApostaCompleta caso cliente tenha saldo, ou null caso ocorra algum erro/nao tenha saldo
    public ApostaCompleta entrarAposta(Aposta aposta, Cliente cliente){
        if(cliente.getCarteira() >= aposta.getValorDaAposta()){
            return new ApostaCompleta(aposta, cliente);
        }
        else{
            //lancar excessao na tela
            return null;
        }
    }
    

    public boolean carregaBaseDeClientes() {
    	return false;
    }
    
}
