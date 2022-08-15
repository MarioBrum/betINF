import java.util.ArrayList;

public class DemoModel {

    private ArrayList<Cliente> clientesCadastrados;
    public DemoModel(){
        clientesCadastrados = new ArrayList<Cliente>();
    }

    public void addCliente(Cliente cliente){
        clientesCadastrados.add(cliente);
    }

    public boolean removeClientePeloCPF(int cpf){
        for (Cliente cliente : clientesCadastrados) {
            if(cliente.getCpf() == cpf){
                clientesCadastrados.remove(cliente);
                return true;
            }
        }
        return false;
        
    }
}
