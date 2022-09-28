package application;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
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
 public void cenaCadastro(ActionEvent event) throws Exception {
  //URL url = getClass().getResource("layoutCadastroLogin.fxml");
  //System.out.println(getClass());
  //this.root = FXMLLoader.load(url);
  root = FXMLLoader.load(getClass().getResource("layoutCadastroCliente.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 
 @FXML
 public void cenaLogin(ActionEvent event) throws Exception {
  System.out.println(getClass().getResource("layoutClienteLogin.fxml"));
  //root = null;
  root = FXMLLoader.load(getClass().getResource("layoutClienteLogin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
  }

  @FXML
  void criaUmCliente(ActionEvent event) {
    System.out.println("deveria estar sendo criado um cliente");
  }

  @FXML
    void logarUsuario(ActionEvent event) {
      System.out.println("deveria estar sendo logado um cliente");
    }
}

