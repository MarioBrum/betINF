import java.text.DecimalFormat;
import java.util.Scanner;

public class DemoView {
    private static Scanner entrada;
    private static DemoView instancia;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private DemoView(){
        entrada = new Scanner(System.in);
        
    }

    public static DemoView getInstancia() {
        if (instancia == null ) {

            instancia = new DemoView();

        }

        return instancia;
    }
    //mostra tela de login
    //retorna true caso v� para janela de login
	public int showOpcoesLogin() {
        limpaConsole();
		System.out.println("1. Digite 1 para ir para aba de login: \n" 
						+ "2. Digite 2 para ir para aba de cadastro: \n"
						+ "0. Sair");
		//int entradaTeclado = entrada.nextInt();
		return entrada.nextInt();
	}

	/*
	System.out.println("Usuario: ");
    String usuario = entrada.next();
    ///senha aparece no terminal
    System.out.println("Senha: ");
    String senha = entrada.next();
	*/
	
	protected Cliente showCadastroCliente() {
        limpaConsole();
		System.out.println("Digite o nome: ");
		String nome = entrada.next();
        limpaConsole();
		System.out.println("Digite o cpf: ");
		String cpf = entrada.next();
        limpaConsole();
		System.out.println("Digite o usuario para login: ");
		String usuario = entrada.next();
        limpaConsole();
		System.out.println("Digite a senha para login: ");
		String senha = entrada.next();
		return new Cliente(nome,usuario,senha,cpf);
	}

    protected Admin showCadastroADMIN() {
        limpaConsole();
		System.out.println("Digite o nome: ");
		String nome = entrada.next();
        limpaConsole();
		System.out.println("Digite o usuario para login: ");
		String usuario = entrada.next();
        limpaConsole();
		System.out.println("Digite a senha para login: ");
		String senha = entrada.next();
		return new Admin(nome,usuario,senha);
	}

    //vai retornar o usuario do login
	protected String showUsuario() {
        limpaConsole();
        System.out.println("usuario: ");
		String usuario = entrada.next();
		return usuario;
	}

    //vai retornar a senha do login
	protected String showSenha() {
        limpaConsole();
        System.out.println("senha: ");
		String senha = entrada.next();
		return senha;
	}

    protected void showErroLogin() {
        limpaConsole();
        System.out.println("Ocorreu algum erro no login, verifique as informacoes ou faca seu cadastro! ");
    }

    //apenas saldo funcionando
    //lista de ofertas direcionada a outra aba
    //implementar metodo pra trocar senha cliente
    protected int mostrarOpcoesLogadoCliente(Cliente cliente) {
        //limpaConsole();
        System.out.println("Ola  " + cliente.getNomeUsuario() + "!\n"
                         + "Seu saldo atual eh: " + df.format(cliente.getCarteira()) + "\n"
                        + "1. Digite 1 para criar oferta: \n" 
						+ "2. Digite 2 para solicitar retirada(saldo): \n"
                        + "3. Digite 3 para criar um deposito(saldo): \n"
                        + "4. Digite 4 para ver lista de ofertas(apostas): \n"
                        + "5. Digite 5 para aceitar uma aposta da lista de ofertas(apostas): \n"
						+ "0. Sair");
		//int entradaTeclado = entrada.nextInt();
		return entrada.nextInt();
    }
    
    protected int mostrarOpcoesLogadoADMIN(Admin admin) {
        //limpaConsole();
        System.out.println("Ola  " + admin.getNomeUsuario() + "!\n"
                        + "1. Informar um placar: \n" 
						+ "2. Ver lista de apostas abertas: \n"
                        + "3. Ver lista de apostas fechadas: \n"
                        + "4. Trocar senha: \n"
                        + "5. Ver lista de clientes cadastrados: \n"
						+ "0. Sair");
		//int entradaTeclado = entrada.nextInt();
		return entrada.nextInt();
    }

    private static void limpaConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void mostrarErroOpcoes() {
        limpaConsole();
        System.out.println("Ocorreu algum erro na escolha de opcoes, reproduza a acao novamente! ");
    }
    
    public Aposta showOpcoesCriaAposta(Cliente clienteLogado) {
    	limpaConsole();
    	//Scanner s = new Scanner(System.in);
    	//comando pra limpar cache da variavel entrada
    	entrada.nextLine();
        System.out.println("Informe o nome da aposta(ex.: Brasil 2 x 0 Servia Jogo1): ");
        String nome = entrada.nextLine();
        //System.out.flush();
        System.out.println("Informe o valor da aposta(ex.: 2,80): \n");
        double valor = entrada.nextDouble();
                        
		//int entradaTeclado = entrada.nextInt();
        return new Aposta(nome,valor,clienteLogado);
    }
    
    public double showDepositoCliente(Cliente clienteLogado) {
    	limpaConsole();
        System.out.println("Informe o valor do deposito(ex.: 2,80): \n");
        return entrada.nextDouble();

    }
    
    public double showRetiradaCliente(Cliente clienteLogado) {
    	limpaConsole();
        System.out.println("Informe o valor de retirada(ex.: 2,80): \n");
        return entrada.nextDouble();

    }

	public void mostraErroRetirada() {
		System.out.println("Ocorreu um erro durante a retirada, valor invalido, verifique seu saldo e repita o processo!"
							+ "\n O seu saldo nao foi alterado!");
		
	}

	/*
	public void showApostasAbertas() {
		limpaConsole();
        System.out.println("Lista de Apostas: \n");

	}
	*/
	
	public static void showApostasAbertas(String lista) {
		limpaConsole();
        System.out.println("Lista de Apostas abertas: \n");
        System.out.println(lista);
        
	}
	
	public void showClientesCadastrados(String lista) {
		limpaConsole();
        System.out.println("Lista de Clientes cadastrados: \n");
        System.out.println(lista);
        
	}

    public String showDigitaSenhaNova() {
        limpaConsole();
        System.out.println("Digite a senha nova: \n");
        String senhaNova = entrada.next();
        return senhaNova;
    }

    public void showApostasFechadas(String lista) {
        limpaConsole();
        System.out.println("Lista de Apostas fechadas/confirmadas: \n");
        System.out.println(lista);
    }

    public void showSaldoInsuficiente() {
        System.out.println("Saldo insuficiente! \n");
    }

    public int showOpcoesApostas(String lista) {
        showApostasAbertas(lista);
        System.out.println("Digite o numero da aposta escolhida: ");
        return entrada.nextInt();
    }

    public void showErroApostaEscolhida() {
        System.out.println("Erro! Aposta escolhida não existe! \n");
    }

    public void showErroClienteValor() {
        System.out.println("Erro! Cliente não pode ser o mesmo e/ou Valor da carteira insuficiente! \n");

    }

    //retorna true se o ganhador foi o cliente que cirou a aposta
    public boolean showOpcoesGanhadorCriou() {
        System.out.println("Digite 1 se o ganhador foi quem criou a aposta, ou 0 se foi quem aceitou: \n");
        return entrada.nextInt() == 1;
    }
	
}


