import java.util.Scanner;
public class DemoView {
    private static Scanner entrada;
    public DemoView(){
        entrada = new Scanner(System.in);
        
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
    protected int mostrarOpcoesLogadoCliente(Cliente cliente) {
        limpaConsole();
        System.out.println("Ola  " + cliente.getNomeUsuario() + "!\n"
                         + "Seu saldo atual eh: " + cliente.getCarteira() + "\n"
                        + "1. Digite 1 para criar oferta: \n" 
						+ "2. Digite 2 para solicitar resgate(saldo): \n"
                        + "3. Digite 3 para ver lista de ofertas(apostas): \n"
						+ "0. Sair");
		//int entradaTeclado = entrada.nextInt();
		return entrada.nextInt();
    }
    
    protected int mostrarOpcoesLogadoADMIN(Admin admin) {
        limpaConsole();
        System.out.println("Ola  " + admin.getNomeUsuario() + "!\n"
                        + "1. Informar um placar: \n" 
						+ "2. Ver lista de apostas abertas: \n"
                        + "3. Ver lista de apostas fechadas: \n"
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
}
