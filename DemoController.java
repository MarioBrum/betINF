import java.util.ArrayList;

public class DemoController{

    private DemoView view;
    private DemoModel model;
	private static DemoController instancia;
    private static boolean logado;


    private DemoController(DemoModel model, DemoView view){
        this.model = model;
        this.view = view;
    }

	public static DemoController getInstance() {

        if (instancia == null ) {

            instancia = new DemoController(DemoModel.getInstancia(),DemoView.getInstancia());

        }

        return instancia;

    }

    public static void main(String [] args){
    	DemoController dc = new DemoController(DemoModel.getInstancia(), DemoView.getInstancia());
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
						//informar um placar
							break;
						case 2:
						//listar apostas abertas
							String listaImpressa = dc.model.listaDeApostasAbertas();
							DemoView.showApostasAbertas(listaImpressa);
							break;
						case 3:
						//listar apostar fechadas
							listaImpressa = dc.model.listaDeApostasFechadas();
							dc.view.showApostasFechadas(listaImpressa);
							break;
						case 4:
						//trocar senha
							String novaSenhaAdm = dc.view.showDigitaSenhaNova();
							adminLogado.setSenhaLogin(novaSenhaAdm);
							break;
						case 5:
						//teste para imprimir clientes
							String clientesCadastrados = dc.model.listaDeClientes();
							dc.view.showClientesCadastrados(clientesCadastrados);
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

	//implementar metodo pra trocar senha cliente ***
	public static void opcoesLogadoCliente(Cliente clienteLogado, DemoController dc){
		int opcao = dc.view.mostrarOpcoesLogadoCliente(clienteLogado);
					//sempre sai do programa
					switch(opcao){
						case 1:
						//criar aposta
							Aposta aposta = dc.view.showOpcoesCriaAposta(clienteLogado);
							//System.out.println(aposta.toString());
							//if de valor carteira < valor aposta/saldo insuficiente
							if(clienteLogado.getCarteira() < aposta.getValorDaAposta()){
								dc.view.showSaldoInsuficiente();
							}
							else{
								dc.model.addAposta(aposta);
								clienteLogado.subCarteira(aposta.getValorDaAposta());
								//dc.model.addAposta(dc.view.showOpcoesCriaAposta(clienteLogado));
							}

							break;
						case 2:
						//fazer retirada
							double valorRet = dc.view.showRetiradaCliente(clienteLogado);
							if(valorRet > clienteLogado.getCarteira()) {
								dc.view.mostraErroRetirada();
							}
							else {
								clienteLogado.subCarteira(valorRet);
							}
							break;
						case 3:
						//fazer deposito
							double valorDep = dc.view.showDepositoCliente(clienteLogado);
							clienteLogado.addCarteira(valorDep);
							break;
						case 4:
						//listar apostas
							//dc.view.showApostasAbertas();
							//dc.view.showUmaLista(dc.model.listaDeApostasAbertas());
							String listaImpressa = dc.model.listaDeApostasAbertas();
							DemoView.showApostasAbertas(listaImpressa);
							break;
						case 5:
						//aceitar aposta
						//ver se a aposta nao possui o proprio cliente
						//ver se o saldo eh valido
						//aceitar a aposta simples e transforma-la em completa/fazendo exclusoes nas listas do model
						
						ArrayList<Aposta> listaDeApostasAbertas = dc.model.getApostasAbertas();
						int apostaEscolhidaInt = dc.view.showOpcoesApostas(dc.model.listaDeApostasAbertas());
						if(apostaEscolhidaInt > listaDeApostasAbertas.size()){
							dc.view.showErroApostaEscolhida();
						}
						else{
							Aposta apostaEscolhida = listaDeApostasAbertas.get(apostaEscolhidaInt);
							if(clienteLogado.getNomeUsuario() != apostaEscolhida.getCliente1().getNomeUsuario() 
							&& clienteLogado.getCarteira() < apostaEscolhida.getValorDaAposta()){
								dc.view.showErroClienteValor();
							}
							//caso tudo ok
							else{
								ApostaCompleta apostaCompletaADD = new ApostaCompleta(apostaEscolhida, clienteLogado);
								clienteLogado.subCarteira(apostaEscolhida.getValorDaAposta());
								dc.model.removeAposta(apostaEscolhida);
								dc.model.addAposta(apostaCompletaADD);
							}
						}
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