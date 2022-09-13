public class DemoController{

    private DemoView view;
    private DemoModel model;
    private static boolean logado;


    public DemoController(DemoModel model, DemoView view){
        this.model = model;
        this.view = view;

    }

    public static void main(String [] args){
    	DemoController dc = new DemoController(new DemoModel(), new DemoView());
		Usuario usuarioLogado = null;
    	while(true) {
    		if(!logado) {
    			int opcao = dc.view.showOpcoesLogin();
    			switch(opcao) {
    			case 0: 
    				System.exit(0);
    			case 1:
    				//pega usuario de login
					String usuario = dc.view.showUsuario();
					String senha = dc.view.showSenha();
					usuarioLogado = dc.model.confirmaLogin(usuario,senha);
					//criar um usuario pro metodo confirma login, e dividir dentro do case1 tela de adm e tela de cliente
					if(usuarioLogado != null){
						//fazer atividades 
						//usuarioLogado = usuario;
						logado = !logado;
						break;
					}
					else{
						//emitir tela de erro
						dc.view.showErroLogin();
					}
					//dc.view.pedeSenha(dc.model.confirmaUsuario(usuario))
    				break;
    				//caso cliente se cadastra, automaticamente � logado
    			case 2:
    				//logado = dc.model.addCliente(dc.view.showCadastro());
    				
    				usuarioLogado = dc.view.showCadastroCliente();
					 //verificar se já nao existe um usuario com o mesmo nomeDeLogin ** nao feito
    				if(usuarioLogado.getClass().getName().equals("Cliente")) {
    					dc.model.addCliente((Cliente) usuarioLogado);
    				}
    				else {
    					dc.view.showErroLogin();
    				}
    				//dc.model.addCliente(usuarioLogado);
					logado = !logado;
    				
    				break;
    			default:
					dc.view.mostrarErroOpcoes();
    				break;
    			}
    		}
			//caso usuario esteja logado, mostrar opcoes
			else{
				//se cliente
				if(usuarioLogado.getClass().getName().equals("Cliente")) {
					opcoesLogadoCliente(((Cliente) usuarioLogado), dc);
				}
				//se admin
				else {
					opcoesLogadoADMIN(((Admin) usuarioLogado), dc);
				}
			}
    	}
    	
    }

	public static void opcoesLogadoADMIN(Admin adminLogado,DemoController dc){
		int opcao = dc.view.mostrarOpcoesLogadoADMIN(adminLogado);
					//sempre sai do programa
					switch(opcao){
						case 1:
							break;
						case 2:
							break;
						case 0:
							logado = !logado;
							break;
						default:
							logado = !logado;
							dc.view.mostrarErroOpcoes();
							break;
					}
	}

	public static void opcoesLogadoCliente(Cliente clienteLogado, DemoController dc){
		int opcao = dc.view.mostrarOpcoesLogadoCliente(clienteLogado);
					//sempre sai do programa
					switch(opcao){
						case 1:
						//criar aposta
							break;
						case 2:
						//fazer retirada
							break;
						case 3:
						//fazer deposito
							break;
						case 4:
						//listar apostas
							break;
						case 0:
							logado = !logado;
							break;
						default:
							logado = !logado;
							dc.view.mostrarErroOpcoes();
							break;
					}
	}


}