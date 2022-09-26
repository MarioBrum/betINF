import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DemoViewFX extends Application{

    private static DemoViewFX instancia;

    public static DemoViewFX getInstancia() throws Exception {
        if (instancia == null ) {

            instancia = new DemoViewFX();

        }

        return instancia;
    }

    public static void main(String [] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        //String caminho = getClass().getResource("cadastroLoginLayout.fxml");
        //System.out.println(getClass().getResource("layout.fxml"));
         
        
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("layoutCadastroLogin.fxml"));
        Parent root = fxmlloader.load();
        Scene tela = new Scene(root);
        
        /*
        fxmlloader = new FXMLLoader(getClass().getResource("layoutCadastroLogin.fxml"));
        root = fxmlloader.load();
        tela = new Scene(root);
        */
        primaryStage.setTitle("betINF");
        primaryStage.setScene(tela);
        primaryStage.show();
        
    }
    
    @FXML
    void fazCadastro(ActionEvent event) throws Exception {
        System.out.println("FEZ CADASTRO");
        //CadastroClienteFX ccFX = new CadastroClienteFX();
        //ccFX.start(new Stage());
    }

    @FXML
    void fazLogin(ActionEvent event) {
        System.out.println("FEZ LOGIN");
    }

}
