import java.util.ArrayList;

public class JogoResultado {
	
	private static String nome; //Jogo servia x brasil 1
	private static String time1;
	private static String time2;
	//implementacao a fim de especificar mais as apostas(evolucao do projeto/atualizacao)
	//private static ArrayList golPorMinuto ;
	private static int numeroGolsTime1;
	private static int numeroGolsTime2;
	
	public JogoResultado(String time1, String time2, int numeroGolsTime1, int numeroGolsTime2) {
		this.time1 = time1;
		this.time2 = time2;
		this.numeroGolsTime1 = numeroGolsTime1;
		this.numeroGolsTime2 = numeroGolsTime2;
	}
	
	//implementar uma categoria/comparator VITORIA,EMPATE,DERROTA(evolucao do projeto/atualizacao) não lembro como faz
	//implementacao simples
	//retorna 1 se time 1 ganhou
	//retorna 2 se time 2 ganhou
	//retorna 0 se empate
	
	public int resultado() {
		if(numeroGolsTime1 > numeroGolsTime2) {
			return 1;
		}
		else if(numeroGolsTime1 > numeroGolsTime2) {
			return 2;
		}
		//empate
		else {
			return 0;
		}
	}
	
}
