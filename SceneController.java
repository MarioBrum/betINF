//package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController extends Application {
  private static SceneController instancia;
  //private static DemoModel model;
  private Stage stage;
  private Scene scene;
  private Parent root;

  public static SceneController getInstancia() {
    if (instancia == null ) {

        instancia = new SceneController();
        //instancia.model = DemoModel.getInstancia();

    }

    return instancia;
  }

  public static void main(String [] args) throws Exception{
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    root = FXMLLoader.load(getClass().getResource("layoutCadastroLogin.fxml"));
    scene = new Scene(root);
    stage.setTitle("betINF");
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  public void cenaCadastro(ActionEvent event) throws Exception {
    root = FXMLLoader.load(getClass().getResource("layoutCadastroCliente.fxml"));
    trocaCena(event);
  }
  
  @FXML
  public void cenaLogin(ActionEvent event) throws Exception {
    root = FXMLLoader.load(getClass().getResource("layoutClienteLogin.fxml"));
    trocaCena(event);
  }

  public void cenaCadastroLogin(ActionEvent event) throws Exception{
    this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    this.stage.hide();
    //System.out.println(this.stage);
    //this.stage.close();
    this.stage = new Stage();
    root = FXMLLoader.load(getClass().getResource("layoutCadastroLogin.fxml"));
    scene = new Scene(root);
    stage.setTitle("betINF");
    stage.setScene(scene);
    stage.show();
  }

  public void trocaCena(ActionEvent event){
    //System.out.println(this.stage);
    this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("betINF");
    stage.show();
    //System.out.println(this.stage);
  }

  @FXML
  private TextField cpfClienteEntrada;
  @FXML
  private TextField nomeClienteEntrada;
  @FXML
  private PasswordField senhaClienteEntrada;
  @FXML
  private TextField usuarioClienteEntrada;

  @FXML
  public void criaUmCliente(ActionEvent event) throws Exception {
      Cliente cliente = new Cliente(nomeClienteEntrada.getText(), 
      usuarioClienteEntrada.getText(), senhaClienteEntrada.getText(), cpfClienteEntrada.getText());
      if(!DemoModel.getInstancia().usuarioValido(usuarioClienteEntrada.getText())){
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("ERRO");
        a.setHeaderText("Já existe um usuário com esse login, cliente não criado!");
        a.showAndWait();
        cenaCadastroLogin(event);
      }
      else{
        DemoModel.getInstancia().addCliente(cliente);
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Confirmação");
        a.setHeaderText("Usuário criado com êxito!");
        a.showAndWait();
        clienteLogado = cliente;
        //troca cena menu principal
        root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        trocaCena(event);
      }
      
  }

  @FXML
  private PasswordField entradaLoginSenha;
  @FXML
  private TextField entradaLoginUsuario;
  //observer nomeUsuario saldoUsuario
  @FXML
  private Text nomeUsuario;
  @FXML
  private Text saldoUsuario;
  private static SimpleStringProperty nomeUsuarioObserver = new SimpleStringProperty();
  private static SimpleDoubleProperty saldoUsuarioObserver = new SimpleDoubleProperty();
  public static Cliente clienteLogado;
  private static Admin adminLogado; 
  //private static Usuario usuarioLogado; 
  
  @FXML
  public void logarUsuario(ActionEvent event) throws Exception {
    Usuario user = DemoModel.getInstancia().confirmaUsuario(entradaLoginUsuario.getText());
    if(user != null){
      //troca cena menu principal
      Usuario usuarioLogado = DemoModel.getInstancia().confirmaLogin(entradaLoginUsuario.getText(), entradaLoginSenha.getText());
      if(usuarioLogado != null){
        //se for cliente
        if(usuarioLogado.getClass().getName().equals("Cliente")) {
					root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
          SceneController.clienteLogado = (Cliente)usuarioLogado;
          trocaCena(event);
          updateMenu();

				}
				//se admin
				else {
					//
          root = FXMLLoader.load(getClass().getResource("MenuPrincipalADM.fxml"));
          SceneController.adminLogado = (Admin)usuarioLogado;
          trocaCena(event);
          //updateMenu(SceneController.adminLogado);

				}
      
      }
      else{
        /*senha errada */
        Alert a = new Alert(AlertType.WARNING);
        a.setTitle("ATENÇÃO");
        a.setHeaderText("Senha errada!");
        a.showAndWait();
        
      }
    }
    else{
      Alert a = new Alert(AlertType.WARNING);
      a.setTitle("ATENÇÃO");
      a.setHeaderText("Usuário não encontrado!");
      a.showAndWait();
    }

  }

  //private Cliente clienteLogado;

  private void updateMenu() {
      
  }

  @FXML
  void cenaAdicionarSaldo(ActionEvent event) throws Exception {
    //nomeUsuario.setText(clienteLogado.getNomeUsuario()+""); 
    //saldoUsuario.setText(""+clienteLogado.getCarteira());
    root = FXMLLoader.load(getClass().getResource("AdicionarSaldo.fxml"));
    trocaCena(event);
    //System.out.println(nomeUsuario.getText());
  }

  @FXML
  private TextField entradaSaldoAdd;
  @FXML
    void adicionarSaldo(ActionEvent event) throws Exception {
      //System.out.println(clienteLogado.toString());
      SceneController.clienteLogado.addCarteira(Double.parseDouble(this.entradaSaldoAdd.getText()));
      //System.out.println(entradaSaldoAdd);
      //System.out.println(Double.parseDouble(this.entradaSaldoAdd.getText()));
      root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
      trocaCena(event);
      
    }

  @FXML
  void cenaCriarNovaAposta(ActionEvent event) throws Exception {    
    root = FXMLLoader.load(getClass().getResource("CriarAposta.fxml"));
    trocaCena(event);
  }

  @FXML
  private TextArea descricaoAposta;

  @FXML
  private TextField entradaTime1;

  @FXML
  private TextField entradaTime2;

  @FXML
  private TextField entradaValorDaAposta;

  @FXML
  void criarAposta(ActionEvent event) throws Exception {
    String descricaoAposta = this.entradaTime1.getText() + " vs " + this.entradaTime2.getText() 
    + "\n" + this.descricaoAposta.getParagraphs().toString();
    double valorAposta = Double.parseDouble(this.entradaValorDaAposta.getText());
    Aposta apostaSimples = new Aposta(descricaoAposta, valorAposta, SceneController.clienteLogado);
    if(valorAposta <= SceneController.clienteLogado.getCarteira() && valorAposta > 0){
      DemoModel.getInstancia().addAposta(apostaSimples);
      Alert a = new Alert(AlertType.CONFIRMATION);
      a.setTitle("CONFIRMAÇÃO");
      a.setHeaderText("Aposta criada com sucesso!");
      a.showAndWait();
      clienteLogado.subCarteira(valorAposta);
      root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
      trocaCena(event);
      

    }
    else{
      Alert a = new Alert(AlertType.CONFIRMATION);
      a.setTitle("ATENÇÃO");
      a.setHeaderText("Saldo insuficiente! Você sera redirecionado.");
      a.showAndWait();
      root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
      trocaCena(event);
    }

  }
  
  @FXML
  private TextArea apostasCampo;

  @FXML
  private TextField entradaIdAposta;

  @FXML
  private Text nomeDoUsuario;

  private boolean mostra = false;

  @FXML
  void entrarApostaParticipante(ActionEvent event) throws Exception {
    //aceitar aposta
						//ver se a aposta nao possui o proprio cliente
						//ver se o saldo eh valido
						//aceitar a aposta simples e transforma-la em completa/fazendo exclusoes nas listas do model
						
						ArrayList<Aposta> listaDeApostasAbertas = DemoModel.getInstancia().getApostasAbertas();
						int idApostaEscolhida = Integer.parseInt(entradaIdAposta.getText());
						if(idApostaEscolhida > listaDeApostasAbertas.size()){
							Alert a = new Alert(AlertType.ERROR);
              a.setTitle("ERRO");
              a.setHeaderText("ID da aposta não existe ou valor inserido está errado!");
              a.showAndWait();
						}
						else{
							Aposta apostaEscolhida = listaDeApostasAbertas.get(idApostaEscolhida);
							if(clienteLogado.getNomeUsuario() == apostaEscolhida.getCliente1().getNomeUsuario()){
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("ERRO");
                a.setHeaderText("Não é possível entrar na própria aposta!");
                a.showAndWait();
							}
              if(clienteLogado.getCarteira() < apostaEscolhida.getValorDaAposta()){
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("ERRO");
                a.setHeaderText("Saldo insuficiente para esta aposta!");
                a.showAndWait();
              }
							//caso tudo ok
							if((clienteLogado.getCarteira() >= apostaEscolhida.getValorDaAposta()) &&
              clienteLogado.getNomeUsuario() != apostaEscolhida.getCliente1().getNomeUsuario()){
								ApostaCompleta apostaCompletaADD = new ApostaCompleta(apostaEscolhida, clienteLogado);
								clienteLogado.subCarteira(apostaEscolhida.getValorDaAposta());
								DemoModel.getInstancia().removeAposta(apostaEscolhida);
								DemoModel.getInstancia().addAposta(apostaCompletaADD);
                voltarMenuPrincipal(event);
							}
						}
  }

  
  @FXML
  void mostrarApostas(ActionEvent event) {
    mostra(event);
    //mostrar apostas
    //System.out.println(apostasCampo);
    apostasCampo.textProperty().setValue(DemoModel.getInstancia().listaDeApostasAbertas());
    //System.out.println(apostasCampo.getText());

    //apostasCampo.setText(DemoModel.getInstancia().listaDeApostasAbertas());
  }

  @FXML
  void voltarMenuPrincipal(ActionEvent event) throws Exception {
    root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
    trocaCena(event);
  }

  @FXML
  void cenaListarApostasDisponiveis(ActionEvent event) throws Exception{
    root = FXMLLoader.load(getClass().getResource("ListarApostasExistentesCliente.fxml"));
    trocaCena(event);
    //SceneController.apostasCampo = new TextArea(DemoModel.getInstancia().listaDeApostasAbertas());
    //SceneController.listaIdeAposta = new TableColumn<>(DemoModel.getInstancia().listaDeApostasAbertas());
  }

  @FXML
  void cenaRetirarSaldo(ActionEvent event)throws Exception {
    root = FXMLLoader.load(getClass().getResource("RetirarSaldo.fxml"));
    trocaCena(event);
  }

  @FXML
  private TextField entradaRetiradaSaldo;

  @FXML
  void retirarSaldoUsuario(ActionEvent event) throws Exception {
    double valorRet = Double.parseDouble(this.entradaRetiradaSaldo.getText());
		if(valorRet > SceneController.clienteLogado.getCarteira()) {
			Alert a = new Alert(AlertType.ERROR);
      a.setTitle("ERRO");
      a.setHeaderText("Saldo insuficiente! Você será redirecionado.");
      a.showAndWait();
      root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
      trocaCena(event);
		}
		else {
			SceneController.clienteLogado.subCarteira(valorRet);
      Alert a = new Alert(AlertType.CONFIRMATION);
      a.setTitle("CONFIRMAÇÃO");
      a.setHeaderText("Valor retirado com sucesso!");
      a.showAndWait();
      root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
      trocaCena(event);
		}

  }
  
  private static final DecimalFormat df = new DecimalFormat("0.00");
  @FXML
  void mostra(ActionEvent event) {
    if(mostra){
      nomeUsuario.setText("");
      saldoUsuario.setText("R$: ");
    }
    else{
      nomeUsuario.setText(clienteLogado.getNomeUsuario());
      saldoUsuario.setText("R$: "+df.format(clienteLogado.getCarteira()));
    }
    mostra = !mostra;

  }

  @FXML
  private TextArea admCampo;

  @FXML
  void cenaInformarPlacar(ActionEvent event) throws Exception {
    root = FXMLLoader.load(getClass().getResource("MenuInformaResult.fxml"));
    trocaCena(event);
  }

  @FXML
  void mostraApostasAbertas(ActionEvent event) {
    admCampo.textProperty().setValue(DemoModel.getInstancia().listaDeApostasAbertas());
  }

  @FXML
  void mostraApostasFechadas(ActionEvent event) {
    admCampo.textProperty().setValue(DemoModel.getInstancia().listaDeApostasFechadas());
  }

  @FXML
  void mostraClientes(ActionEvent event) {
    admCampo.textProperty().setValue(DemoModel.getInstancia().listaDeClientes());
  }

  @FXML
  void userLogout(ActionEvent event) throws Exception {
    SceneController.clienteLogado = null;
    SceneController.adminLogado = null;
    cenaCadastroLogin(event);
  }

  @FXML
  void cenaTrocarSenha(ActionEvent event) throws Exception {
    root = FXMLLoader.load(getClass().getResource("TrocarSenhaADM.fxml"));
    trocaCena(event);
  }


  @FXML
  private PasswordField entradaNovaSenha;
  @FXML
  void trocarSenhaAdm(ActionEvent event) throws Exception {
    SceneController.adminLogado.setSenhaLogin(this.entradaNovaSenha.getText());
    Alert a = new Alert(AlertType.CONFIRMATION);
    a.setTitle("CONFIRMAÇÃO");
    a.setHeaderText("Senha alterada com sucesso!");
    a.showAndWait();
    root = FXMLLoader.load(getClass().getResource("MenuPrincipalADM.fxml"));
    trocaCena(event);
  }

  @FXML
  void criadorGanhou(ActionEvent event) throws IOException {
    ArrayList<ApostaCompleta> ac = DemoModel.getInstancia().getApostasFechadas();
    ApostaCompleta apostaEscolhida = ac.get(Integer.parseInt(entradaIdAposta.getText()));
    apostaEscolhida.getCliente1().addCarteira(apostaEscolhida.getValorDaAposta());
    DemoModel.getInstancia().removeAposta(apostaEscolhida);
    Alert a = new Alert(AlertType.CONFIRMATION);
    a.setTitle("CONFIRMAÇÃO");
    a.setHeaderText("Criador a aposta ganhou, aposta finalizada!"
    +"/n Você será redirecionado.");
    root = FXMLLoader.load(getClass().getResource("MenuPrincipalADM.fxml"));
    trocaCena(event);
  }

  @FXML
  void participanteGanhou(ActionEvent event) throws IOException {
    ArrayList<ApostaCompleta> ac = DemoModel.getInstancia().getApostasFechadas();
    ApostaCompleta apostaEscolhida = ac.get(Integer.parseInt(entradaIdAposta.getText()));
    apostaEscolhida.getCliente2().addCarteira(apostaEscolhida.getValorDaAposta());
    DemoModel.getInstancia().removeAposta(apostaEscolhida);
    Alert a = new Alert(AlertType.CONFIRMATION);
    a.setTitle("CONFIRMAÇÃO");
    a.setHeaderText("Participante da aposta ganhou, aposta finalizada!"
    +"/n Você será redirecionado.");
    root = FXMLLoader.load(getClass().getResource("MenuPrincipalADM.fxml"));
    trocaCena(event);
  }

  @FXML
    void invalidoGanhou(ActionEvent event) {
      ArrayList<ApostaCompleta> ac = DemoModel.getInstancia().getApostasFechadas();
      ApostaCompleta apostaEscolhida = ac.get(Integer.parseInt(entradaIdAposta.getText()));
      apostaEscolhida.getCliente1().addCarteira(apostaEscolhida.getValorDaAposta());
      apostaEscolhida.getCliente2().addCarteira(apostaEscolhida.getValorDaAposta());
      DemoModel.getInstancia().removeAposta(apostaEscolhida);
      Alert a = new Alert(AlertType.WARNING);
      a.setTitle("ATENÇÃO");
      a.setHeaderText("A aposta não teve vencedores! O dinheiro foi reembolsado."
      +"/n Você será redirecionado.");
    }

  @FXML
  void selecionarAposta(ActionEvent event) {
    ArrayList<ApostaCompleta> ac = DemoModel.getInstancia().getApostasFechadas();
    if(Integer.parseInt(entradaIdAposta.getText()) >= ac.size() || Integer.parseInt(entradaIdAposta.getText()) < 0 ){
      Alert a = new Alert(AlertType.WARNING);
      a.setTitle("ATENÇÃO");
      a.setHeaderText("ID da aposta inexistente!");
      a.showAndWait();
    }
    else{
      descricaoAposta.textProperty().setValue(ac.get(Integer.parseInt(entradaIdAposta.getText())).toString());
    }
  }

}

