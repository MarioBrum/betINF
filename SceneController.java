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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController extends Application {
  private static SceneController instancia;
  //private static DemoModel model;
  @FXML
  private TextField cpfClienteEntrada;
  @FXML
  private TextField nomeClienteEntrada;
  @FXML
  private PasswordField senhaClienteEntrada;
  @FXML
  private TextField usuarioClienteEntrada;
  private Stage stage;
  private Scene scene;
  private Parent root;
  @FXML
  private Button botaoCadastro;
  @FXML
  private Button botaoLogin;
  @FXML
  private Button botaoLoginCliente;
  @FXML
  private PasswordField entradaLoginSenha;
  @FXML
  private TextField entradaLoginUsuario;

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
  public void criaUmCliente(ActionEvent event) throws Exception {
    //System.out.println(this.stage);
    /*
    System.out.println("deveria estar sendo criado um cliente");
    System.out.println( nomeClienteEntrada.getText() + "\n "
      + cpfClienteEntrada.getText() + "\n "
      + usuarioClienteEntrada.getText() + "\n "
      + senhaClienteEntrada.getText());
      */
      Cliente cliente = new Cliente(nomeClienteEntrada.getText(), 
      usuarioClienteEntrada.getText(), senhaClienteEntrada.getText(), cpfClienteEntrada.getText());
      //System.out.println(nomeClienteEntrada.getText());
      //System.out.println(usuarioClienteEntrada.getText());
      //System.out.print(DemoModel.getInstancia().usuarioValido(usuarioClienteEntrada.getText()));

      //System.out.print(stage);
      if(!DemoModel.getInstancia().usuarioValido(usuarioClienteEntrada.getText())){
        //System.out.print(DemoModel.getInstancia().containsUsuario(usuarioClienteEntrada.getText()));
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("ERRO");
        a.setHeaderText("Já existe um usuário com esse login, cliente não criado!");
        a.showAndWait();
        //cenaCadastro(event);
        //stage.hide();
        //System.out.print(this.stage);
        cenaCadastroLogin(event);
        //start(stage);
      }
      else{
        DemoModel.getInstancia().addCliente(cliente);
        //System.out.println(DemoModel.getInstancia().listaDeClientes());
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
  public void logarUsuario(ActionEvent event) throws Exception {
    /* 
    System.out.println("deveria estar sendo logado um cliente");
    System.out.println( entradaLoginUsuario.getText() + "\n "
    + entradaLoginSenha.getText());
    */
    Usuario user = DemoModel.getInstancia().confirmaUsuario(entradaLoginUsuario.getText());
    if(user != null){
      //troca cena menu principal
      root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
      trocaCena(event);
    }
    else{
      Alert a = new Alert(AlertType.WARNING);
      a.setTitle("ATENÇÃO");
      a.setHeaderText("Usuário não encontrado!");
      a.showAndWait();
    }

  }

  @FXML
  private Text nomeUsuario;

  @FXML
  private Text saldoUsuario;

  @FXML
  void adicionarSaldo(ActionEvent event) {

  }

  @FXML
  void criarNovaAposta(ActionEvent event) {

  }

  @FXML
  void listarApostasDisponiveis(ActionEvent event) {

  }

  @FXML
  void retirarSaldo(ActionEvent event) {

  }

  @FXML
  void userLogout(ActionEvent event) {

  }

}

