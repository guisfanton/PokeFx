package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class telainicialController {

    @FXML
    private Button b1;


    @FXML
    public void click(ActionEvent actionEvent) {
        System.out.println("Cliquei");
    }
}
