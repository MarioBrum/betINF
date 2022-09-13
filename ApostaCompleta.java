public class ApostaCompleta extends Aposta{

    private Cliente cliente2;
    public ApostaCompleta(String nomeDaAposta,int valorDaAposta, Cliente cliente1,Cliente cliente2){
        super(nomeDaAposta, valorDaAposta, cliente1);
        this.cliente2 = cliente2;
    }

    public ApostaCompleta(Aposta aposta,Cliente cliente2){
        super(aposta.getNomeDaAposta(),aposta.getValorDaAposta(), aposta.getCliente1());
        this.cliente2 = cliente2;
    }
    
    public String toString(){
		return "-----------------------------------------------------------------\n"
                + "Aposta: " + this.getNomeDaAposta() + "\n"
				+ "Valor: " + "R$ " + this.getValorDaAposta() + "\n"
				+ "Aposta de : " + this.getCliente1().getNomeUsuario() + "\n"
                + "Aceita por: " + this.cliente2 + "\n"
                + "-----------------------------------------------------------------";
	}
	
    
}
