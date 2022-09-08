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
					int opcao = dc.view.mostrarOpcoesLogadoCliente((Cliente) usuarioLogado);
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
				//se admin
				else {
					int opcao = dc.view.mostrarOpcoesLogadoADMIN((Admin) usuarioLogado);
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
			}
    	}
    	
    }


}