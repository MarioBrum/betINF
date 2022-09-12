
public class Aposta {
	
	private int valorDaAposta;
	private Cliente cliente1;
	private Cliente cliente2;
	private String nomeDaAposta;

	//refazer essa aposta, onde uma aposta simples possui apenas cliente/valor/nome e 
	//uma aposta concluida possui cliente/valor/nome e outro cliente


	//modificar nome da aposta para ter os times ou numero de gols como strings separadas?
	//eh o que eu tava pensando,  a principio a ideia eh pegar essa classe aposta e mistificar em apostas diferentes
	//aposta por vencedor, aposta por vencedor/empate, aposta por perdedor, aposta por perdedor/empate, aposta por empate
	//aposta por vencedor gols, aposta por perdedor gols(? melhor n), aposta por numero de gols conjunto
	//ai mais pra frente aposta em numero de gols por tempo de jogo(super interessante, caso de tempo, atualizacao futura)
	
	//usando essa classificacao de apostas, ao inves de se atualizar apenas 1 aposta por vez, atualizasse o resultado do jogo que 
	//automaticamente atualiza a lista de apostas daquele jogo
	//(nome do jogo/descricao aposta ex.: nome>Brasil vs Servia P1 descricao> Brasil 3 gols)
	//ai ele atualiza as apostas do jogo e ja lanca os valores excluindo as apostas da lista de apostas com resultados nao terminados
	//enquanto deve-se ter uma lista de jogos futuros, para os clientes fazerem as apostas(implementado depois, por enquanto deixar
	//autonomia pro cliente criar o nome padronizado ex.: "NomeTime1 vs NomeTime2 P(partida)2(numero da partida)"
	
	//_______________________________________________________________________________________________________________
	//precisa de um construtor ja que o metodo criar aposta cria uma aposta?
	//o metodo cria aposta eh um metodo do model que vai criar uma instancia aposta, precisa sim de construtor
	
	public Aposta(String nomeDaAposta,int valorDaAposta, Cliente cliente1) {
		this.valorDaAposta = valorDaAposta;
		this.cliente1 = cliente1;
		this.nomeDaAposta = nomeDaAposta;
		//deixar cliente2 nulo, pois ninguem aceita a aposto ao cria-la
		//criando cliente "nulo"
		//this.cliente2 = new Cliente("vazio","vazio","vazio");
		this.cliente2 = null;
		
		/* usar essa verificacao no model e no view, se imprime na tela eh do view
		if(cliente1.getCarteira() < valorDaAposta)
		{
			System.out.println("Saldo Insuficiente");
		}
		else
		{
			cliente1.subCarteira(.valorDaAposta);
		}
		*/
	}

	/*
	 //construtor errado
	public Aposta criarAposta(String nomeDaAposta,int valorDaAposta, Cliente cliente1) {
		Aposta novaAposta = new Aposta();
		novaAposta.valorDaAposta = valorDaAposta;
		novaAposta.cliente1 = cliente1;
		novaAposta.nomeDaAposta = nomeDaAposta;
		
		
		if(cliente1.getCarteira() < novaAposta.valorDaAposta)
		{
			System.out.println("Saldo Insuficiente");
			return null;
		}
		else
		{
			cliente1.subCarteira(novaAposta.valorDaAposta);
			return novaAposta;
		}
	}
	*/
	
	/*
	//funcao de entrar em uma aposta ja existente/retorna uma aposta que atualiza a lista de apostas
	//apos uma aposta fechar ela nao eh alterada
	public Aposta EntrarAposta(Cliente clienteEntraAposta,Aposta aposta)
	{
		if(clienteEntraAposta.getCarteira()< aposta.valorDaAposta)
		{
			System.out.println("Saldo insuficiente para participar da aposta");
			return aposta;
		}
		else {	
//essas operacoes so estao mexendo no cliente dentro desse escopo
//tem que fazer tratamento apos essa chamada(usar a aposta que so retornada para atualizar o saldo do cliente) ou passar o cliente por referencia ou algo do tipo?
			aposta.cliente2 = clienteEntraAposta;
			aposta.cliente2.subCarteira(valorDaAposta);
			
			return aposta;
		}
	}
	*/
	
	public void EntrarAposta(Cliente clienteEntraAposta) {
		if(this.cliente2 != null){
			//chama uma interrupcao do view
			//fazer essa condicao e TODAS AS CONDICOES sobre interrupcoes e erros no controller, sendo visualizadas no view
		}
		else {
			this.cliente2 = clienteEntraAposta;
		}
	
		/*
		if(this.cliente2.getNome().equals("vazio")) {
			this.cliente2 = clienteEntraAposta;
			//fazer uma transferencia de local dessa aposta da lista de apostas abertas para as apostas fechadas
		}
		else {
			//chama uma interrupcao do view
			//fazer essa condicao e TODAS AS CONDICOES sobre interrupcoes e erros no controller, sendo vizualidas no view
		}
		*/
		
	}
	
	
	public int getValorDaAposta() {
		return valorDaAposta;
	}
	//sem metodo set, depois da aposta criada nao mudasse o valor
	/*
	public void setValorDaAposta(int valorDaAposta) {
		this.valorDaAposta = valorDaAposta;
	}
	*/
	
	public Cliente getCliente1() {
		return cliente1;
	}
	//sem metodo set, depois do cliente 1 criar aposta ele nao vai mudar
	/*
	public void setCliente1(Cliente cliente1) {
		this.cliente1 = cliente1;
	}
	*/
	
	public Cliente getCliente2() {
		return cliente2;
	}
	//sem metodo set, depois do cliente 2 aceitar a aposta ele nao vai mudar
	//ou vai dependendo das atualizacoes do nosso programa
		/*
	public void setCliente2(Cliente cliente2) {
		this.cliente2 = cliente2;
	}
	*/
	
	public String getNomeDaAposta() {
		return nomeDaAposta;
	}
	
	//sem metodo set, depois do nome da aposta ser criado ele nao vai mudar
		/*
	public void setNomeDaAposta(String nomeDaAposta) {
		this.nomeDaAposta = nomeDaAposta;
	}
	
		*/

	public String toString(){
		return "Aposta: " + this.nomeDaAposta + "\n"
				+ "Valor: " + "R$ " + this.valorDaAposta + "\n"
				+ "Aposta de : " + this.cliente1.getNomeUsuario();
	}
	
}
