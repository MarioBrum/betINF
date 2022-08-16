import java.util.ArrayList;

public class DemoModel {

    private static ArrayList<Cliente> clientesCadastrados;
    public DemoModel(){
        clientesCadastrados = new ArrayList<Cliente>();
    }

    //fazer cadastro
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
    public Cliente confirmaUsuario(String usuarioNome){
        for(Cliente cliente : clientesCadastrados){
            if(cliente.getNomeUsuario().equals(usuarioNome)){
                return cliente;
            }
        }
        return null;
    }

    public boolean confirmaLogin(String usuarioNome, String senhaNome) {
        Cliente usuario = confirmaUsuario(usuarioNome);
        if(usuario != null){
            if(usuario.getNome().equals(usuarioNome) && usuario.getSenhaUsuario().equals(senhaNome)){
                return true;
            }
        }
        return false;
    }
    
    
}
