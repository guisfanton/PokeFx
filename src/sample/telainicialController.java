package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class telainicialController {

    @FXML
    private Button button_entrar, button_registrar;
    private TextField textfield_usuario;
    private PasswordField passwordfield_senha;


    @FXML
    public void click(ActionEvent actionEvent) {
        System.out.println("Cliquei");
    }
}
