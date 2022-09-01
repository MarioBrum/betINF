
public class Gol {
	
	private String time;
	private int tempo; 
	//tempo dado em um minuto inteiro(nao levar em conta segundos) entre 1 - 90 + acrescimos(100 por exemplo)
	
	//uma vez criado um objeto gol nao pode ser alterado(retirar opção de mudar placar/gol apos a decisão final
	//imaginando-se que os resultados apenas são inseridos após o final do mesmo
	public Gol(String time,int tempo) {
		this.time = time;
		this.tempo = tempo;
	}

	public String getTime() {
		return time;
	}

	public int getTempo() {
		return tempo;
	}

}
