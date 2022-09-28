import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroClienteFX {

    @FXML
    private TextField cpfClienteEntrada;

    @FXML
    private TextField nomeClienteEntrada;

    @FXML
    private PasswordField senhaClienteEntrada;

    @FXML
    private TextField usuarioClienteEntrada;

    @FXML
    void criaUmCliente(ActionEvent event) throws IOException {
        System.out.println( nomeClienteEntrada + " /n "
                            + cpfClienteEntrada + " /n "
                            + usuarioClienteEntrada + " /n "
                            + senhaClienteEntrada);
    }

    public Scene start() throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("layoutCadastroClientefxml"));
        Parent root = fxmlloader.load();
        Scene tela = new Scene(root);
        return tela;
    }

}
