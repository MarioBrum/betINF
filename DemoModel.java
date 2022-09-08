import java.util.ArrayList;

public class DemoModel {

    private static ArrayList<Cliente> clientesCadastrados;
    private static ArrayList<Admin> administradoresCadastrados;
    private static ArrayList<Aposta> apostasRegistradas;
    //criado para ter uma divisao das apostas abertas(onde ninguem aceitou a aposta) e das fechadas(onde tem 2 clientes apostando)
    private static ArrayList<Aposta> apostasConsolidadas;
    //private static ArrayList<JogoResultado> resultadoAtualizado;
    public DemoModel(){
    	clientesCadastrados = new ArrayList<Cliente>();
    	administradoresCadastrados = new ArrayList<Admin>();
    	administradoresCadastrados.add(new Admin ("admin","admin","admin"));
    	clientesCadastrados.add(new Cliente ("Mario","mario1","senha1","10101010101"));
    }
    
    public boolean addAposta(Aposta aposta)
    {
    	return apostasRegistradas.add(aposta);
    }
    //fazer cadastro cliente
    public boolean addCliente(Cliente cliente){
        return clientesCadastrados.add(cliente);
    }

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
    
    public boolean carregaBaseDeClientes() {
    	return false;
    }
    
}
