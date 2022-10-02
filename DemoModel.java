import java.util.ArrayList;

//aplicado singleton
public class DemoModel {

    //private static DemoView dv;
    private static DemoModel instancia;
    private static ArrayList<Cliente> clientesCadastrados;
    private static ArrayList<Admin> administradoresCadastrados;
    private static ArrayList<Aposta> apostasAbertas;
    private static ArrayList<ApostaCompleta> apostasConfirmadas;
    //private static ArrayList<JogoResultado> resultadoAtualizado;
    private DemoModel(){

    	clientesCadastrados = new ArrayList<Cliente>();
    	administradoresCadastrados = new ArrayList<Admin>();
    	apostasAbertas = new ArrayList<Aposta>();
    	apostasConfirmadas = new ArrayList<ApostaCompleta>();
        administradoresCadastrados.add(new Admin ("admin","admin","admin"));

        //instanciando coisas pra testes
    	clientesCadastrados.add(new Cliente ("Mario","mario1","senha1","10101010101"));
        clientesCadastrados.add(new Cliente ("Marcelo","marcelo1","senha1","10101010102"));
        clientesCadastrados.add(new Cliente ("Adilson","adilson1","senha1","10101010103"));
        apostasAbertas.add(new Aposta("Brasil vs Servia \n[2 a 0 Brasil]", 5, clientesCadastrados.get(2)));
        apostasConfirmadas.add(new ApostaCompleta("Japao vs Argentina \n[Minimo 3 gols]", 5, clientesCadastrados.get(2),clientesCadastrados.get(0)));
    }

    //padrao singleton
    public static DemoModel getInstancia(){
        if(instancia == null){
            instancia = new DemoModel();
        }
        return instancia;
    }
    
    //adiciona uma aposta composta/completa(com cliente2/fechada)
    public void addAposta(ApostaCompleta aposta)
    {
    	DemoModel.apostasConfirmadas.add(aposta);
    }

    //arrumas isso, valor da aposta verificacao e erro de view/controller/model
    //**************** importanteeee
    //adiciona uma aposta simples(sem cliente2/aberta)
    public void addAposta(Aposta aposta)
    {
    	//System.out.println(aposta.toString());
    	//System.out.println(aposta.getClass());
    	DemoModel.apostasAbertas.add(aposta);
    	//arrumar ******************************
    }

    //remove uma aposta abertas
    public void removeAposta(Aposta aposta)
    {
    	DemoModel.apostasAbertas.remove(aposta);
    }

    //remove uma aposta abertas
    public void removeAposta(ApostaCompleta aposta)
    {
    	DemoModel.apostasConfirmadas.remove(aposta);
    }

    //fazer cadastro cliente
    public boolean addCliente(Cliente cliente){
        return clientesCadastrados.add(cliente);
    }

    //verifica se nao possui um cliente com mesmo username
    public boolean usuarioValido(String usuario){
        boolean resultado = true;
        for(Cliente c:clientesCadastrados){
            //System.out.println(c.getNomeUsuario());
            //System.out.println(usuario);
            if(c.getNomeUsuario().equalsIgnoreCase(usuario)){
                //System.out.println(false);
                
                return false;
            }
        }
        return resultado;  
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
            if(cliente.getNomeUsuario().equalsIgnoreCase(usuarioNome)){
                return cliente;
            }
        }
        for(Admin adm : administradoresCadastrados){
            if(adm.getNomeUsuario().equalsIgnoreCase(usuarioNome)){
                return adm;
            }
        }
        return null;
    }

    public Usuario confirmaLogin(String usuarioLogin, String usuarioSenha) {
        Usuario usuario = confirmaUsuario(usuarioLogin);
        if(usuario != null){
            if(usuario.getNomeUsuario().equalsIgnoreCase(usuarioLogin) && usuario.getSenhaUsuario().equals(usuarioSenha)){
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
    
    public ArrayList<Aposta> getApostasAbertas(){
        return apostasAbertas;
    }
    public String listaDeApostasAbertas() {
    	String retorno = "";
        int contador = 0;
    	for(Aposta a : apostasAbertas ){
            retorno += "\n ID: "+ contador++ + " ";
    		retorno += a.toString() + " \n";
    	}
    	return retorno;
    }
    
    public String listaDeClientes() {
    	String retorno = "";
        int contador = 0;
    	for(Cliente a : clientesCadastrados ){
            retorno += "\n ID: "+ contador++ + " ";
    		retorno += a.toString() + " \n";
    	}
    	return retorno;
    }

    public boolean carregaBaseDeClientes() {
    	return false;
    }

    public String listaDeApostasFechadas() {
        String retorno = "";
        int contador = 0;
    	for(Aposta a : apostasConfirmadas ){
            retorno += "\n ID: "+ contador++ + " ";
    		retorno += a.toString() + " \n";
    	}
    	return retorno;
    }

    public ArrayList<ApostaCompleta> getApostasFechadas() {
        return apostasConfirmadas;
    }
    
}
