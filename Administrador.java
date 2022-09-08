//import java.util.Scanner;

public class Administrador extends Usuario{
        
    public Administrador(String nome,String adminLogin,String adminSenha){
        super(nome,adminLogin,adminSenha);
    }
    
	/* 
    public void criarLoginAdm(String adminLogin, String adminSenha){
        this.adminLogin = adminLogin;
        this.adminSenha = adminSenha;
    }
    */

	
    //atualizar o model/view para lidar com o login de Admin
    
    /*
    public Cliente definirVencedor(Aposta aposta)
    {
        //scanner temporario ---> passar para a view depois
    	
    	Cliente clienteA = aposta.getCliente1(); //por algum motivo chamar aposta.getCliente1().getNome(); n�o funciona
    	//String nome = aposta.getCliente1().getNome();
    	Cliente clienteB = aposta.getCliente2();
    	Cliente clienteVencedor = new Cliente("","");
    	System.out.println("Selecionar o vencedor da aposta");
    	System.out.println(aposta.getNomeDaAposta() + "R$:"+ aposta.getValorDaAposta());
    	System.out.println("entre os clientes:"+ clienteA.getNomeUsuario()+" X "+clienteB.getNomeUsuario());
    	
    	//deve dar pra transformar isso em um bot�o no javafx
    	Scanner entrada = new Scanner(System.in);

		if(entrada.nextInt() == 1)
		{
    		clienteVencedor = aposta.getCliente1();
		}
    	if(entrada.nextInt() == 2)
    	{
    		clienteVencedor = aposta.getCliente2();
    	}
    	entrada.close();
    	clienteVencedor.addCarteira(aposta.getValorDaAposta());
    	//mesmo problema do entrarAposta, esse � mais simples de tratar 
    	//s� comparar o cliente retornado com os clientes da lista e substituir quando encontrar
    	return clienteVencedor;
    }
    */
}
