package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VisualizacaoController {

    @FXML
    private Button button_adicionar;

    @FXML
    private Button button_sair;

    @FXML
    void adicionar(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("Adicionar.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) button_adicionar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void sair(ActionEvent event) {

    }

}
