package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class telainicialController implements Initializable{

    @FXML
    private Button button_entrar, button_registrar;
    @FXML
    private TextField textfield_usuario;
    @FXML
    private PasswordField passwordfield_senha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void button_entrar(ActionEvent event){
        if (!textfield_usuario.getText().isEmpty() && !passwordfield_senha.getText().isEmpty()) {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("Visualizacao.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            stage = (Stage) button_entrar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void registrar(ActionEvent event) {
    }
}
