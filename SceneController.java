//package application;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
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
    launch(args);
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
        //troca cena menu principal
        root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        trocaCena(event);
      }
      
  }

  @FXML
  private PasswordField entradaLoginSenha;
  @FXML
  private TextField entradaLoginUsuario;
  @FXML
  private static Text nomeUsuario;
  @FXML
  private static Text saldoUsuario;
  private static Cliente clienteLogado; 
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
          updateMenu(SceneController.clienteLogado);
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

  private void updateMenu(Cliente clienteLogado) {
    SceneController.nomeUsuario.setText(SceneController.clienteLogado.getNomeUsuario()+""); 
    SceneController.saldoUsuario.setText("R$ "+SceneController.clienteLogado.getCarteira());
  }

  @FXML
  void cenaAdicionarSaldo(ActionEvent event) throws Exception {
    //nomeUsuario.setText(clienteLogado.getNomeUsuario()+""); 
    //saldoUsuario.setText(""+clienteLogado.getCarteira());
    root = FXMLLoader.load(getClass().getResource("AdicionarSaldo.fxml"));
    trocaCena(event);
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
  private static TextArea apostasCampo;

  @FXML
  private TextField entradaIdAposta;

  @FXML
  private Text nomeDoUsuario;

  @FXML
  void entrarApostaParticipante(ActionEvent event) {
    SceneController.apostasCampo = new TextArea(DemoModel.getInstancia().listaDeApostasAbertas());
  }

  @FXML
  void cenaListarApostasDisponiveis(ActionEvent event) throws Exception{
    root = FXMLLoader.load(getClass().getResource("ListarApostasExistentesCliente.fxml"));
    trocaCena(event);
    SceneController.apostasCampo = new TextArea(DemoModel.getInstancia().listaDeApostasAbertas());
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


  @FXML
  void userLogout(ActionEvent event) throws Exception {
    SceneController.clienteLogado = null;
    SceneController.adminLogado = null;
    cenaCadastroLogin(event);
  }


  //meu deus que bagunça nojenta
  @FXML
  private TableColumn<?, ?> listaApostasAbertas;

  @FXML
  private TableColumn<?, ?> listaApostasFechadas;

  @FXML
  private TableColumn<?, ?> listaCPF;

  @FXML
  private TableColumn<?, ?> listaCriadorAbertas;

  @FXML
  private TableColumn<?, ?> listaCriadorFechadas;

  @FXML
  private TableColumn<?, ?> listaIDsAbertas;

  @FXML
  private TableColumn<?, ?> listaIDsFechadas;

  @FXML
  private TableColumn<?, ?> listaNomes;

  @FXML
  private TableColumn<?, ?> listaParticipanteFechadas;

  @FXML
  private TableColumn<?, ?> listaUsuarios;

  @FXML
  private TableColumn<?, ?> listaValorAbertas;

  @FXML
  private TableColumn<?, ?> listaValorFechadas;

  @FXML
  void getClientList(ActionEvent event) {

  }

  @FXML
  void getClosedBetsList(ActionEvent event) {

  }

  @FXML
  void getOpenBetsList(ActionEvent event) {

  }

  @FXML
  void cenaInformarPlacar(ActionEvent event) {

  }

  @FXML
  void cenaTrocarSenha(ActionEvent event) throws Exception {
    root = FXMLLoader.load(getClass().getResource("TrocarSenhaADM.fxml"));
    trocaCena(event);
  }


  @FXML
  private TextField entradaNovaSenha;
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
}

