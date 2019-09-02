package Javafx;

import bd.DaoUsuario;
import bd.Usuario;
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

    private DaoUsuario daoUsuario = new DaoUsuario();

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
        if(daoUsuario.login(new Usuario(textfield_usuario.getText(), passwordfield_senha.getText()))) {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Visualizacao.fxml"));
            try {
                Loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            VisualizacaoController display = Loader.getController();
            display.passaId(daoUsuario.pesquisaId(textfield_usuario.getText()));
            Parent p = Loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p));
            stage.show();
            stage = (Stage) button_entrar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void button_registrar(ActionEvent event){
        if(!daoUsuario.verifica(textfield_usuario.getText())) {
            daoUsuario.adiciona(new Usuario(textfield_usuario.getText(), passwordfield_senha.getText()));
        }else{
            System.out.println("num pode");
        }
    }
}
