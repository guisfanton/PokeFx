/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 05200242
 */
public class AdicionarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button button_meuspokemons;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void button_meuspokemos(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("Visualizacao.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) button_meuspokemons.getScene().getWindow();
        stage.close();
    }





    
}
