
public class Aposta {
	
	private int valorDaAposta;
	private Cliente cliente1;
	private Cliente cliente2;
	private String nomeDaAposta;
	//modificar nome da aposta para ter os times ou numero de gols como strings separadas?
	
	//precisa de um construtor ja que o metodo criar aposta cria uma aposta?

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
	
	//fun��o de entrar em uma aposta ja existente/retorna uma aposta que atualiza a lista de apostas
	public Aposta EntrarAposta(Cliente clienteEntraAposta,Aposta aposta)
	{
		if(clienteEntraAposta.getCarteira()< aposta.valorDaAposta)
		{
			System.out.println("Saldo insuficiente para participar da aposta");
			return aposta;
		}
		else {	
//essas opera��es s� est�o mexendo no cliente dentro desse escopo
//tem que fazer tratamento apos essa chamada(usar a aposta que � retornada para atualizar o saldo do cliente) ou passar o cliente por referencia ou algo do tipo?
			aposta.cliente2 = clienteEntraAposta;
			aposta.cliente2.subCarteira(valorDaAposta);
			
			return aposta;
		}
	}
	
	public int getValorDaAposta() {
		return valorDaAposta;
	}
	public void setValorDaAposta(int valorDaAposta) {
		this.valorDaAposta = valorDaAposta;
	}
	
	public Cliente getCliente1() {
		return cliente1;
	}
	public void setCliente1(Cliente cliente1) {
		this.cliente1 = cliente1;
	}
	
	public Cliente getCliente2() {
		return cliente2;
	}
	public void setCliente2(Cliente cliente2) {
		this.cliente2 = cliente2;
	}
	
	public String getNomeDaAposta() {
		return nomeDaAposta;
	}
	public void setNomeDaAposta(String nomeDaAposta) {
		this.nomeDaAposta = nomeDaAposta;
	}
	
	
}
