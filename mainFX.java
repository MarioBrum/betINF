import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainFX extends Application{

    public static void main(String [] args) throws Exception{
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        
        //System.out.println(getClass().getResource("layoutCadastroCliente.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("layoutCadastroLogin.fxml"));
        //System.out.println(root);
        Scene scene = new Scene(root);
        //stage.setTitle("betINF");
        stage.setScene(scene);
        stage.show();
        
    }

}
