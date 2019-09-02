/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Javafx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import bd.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 05200242
 */
public class AdicionarController implements Initializable {

    private DaoPokemon daoPokemon = new DaoPokemon();
    private DaoEspecie daoEspecie = new DaoEspecie();
    private int usuarioID;

    @FXML
    private ImageView pokemonImageView;
    @FXML
    private Button adicionarPokemonButton, meusPokemonsButton;
    @FXML
    private TextField nomeTextField, cpTextField, psTextField, ataqueRapidoTextField, ataqueCarregadoTextField;
    @FXML
    private ComboBox<Especie> especieComboBox;
    private ObservableList<Especie> especieObservableList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                cpTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        psTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                psTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        especieObservableList = daoEspecie.pesquisaTodos();
        especieComboBox.setItems(especieObservableList);
        especieComboBox.setCellFactory((list) -> {
            return new ListCell<Especie>() {
                @Override
                protected void updateItem(Especie item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getNome());
                    }
                }
            };
        });
    }

    @FXML
    public void adicionarPokemonButton(ActionEvent event){
        Pokemon pokemon = new Pokemon();
        if (tudoPreenchido()) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Está tudo correto?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> bt = alerta.showAndWait();
            if (bt.get() == ButtonType.YES) {
                pokemon.setNome(nomeTextField.getText());
                pokemon.setCp(Integer.parseInt(cpTextField.getText()));
                pokemon.setPs(Integer.parseInt(psTextField.getText()));
                pokemon.setAtaqueRápido(ataqueRapidoTextField.getText());
                pokemon.setAtaqueCarregado(ataqueCarregadoTextField.getText());
                pokemon.setIdEspecie(especieComboBox.getSelectionModel().getSelectedItem().getId());
                pokemon.setIdUsuario(usuarioID);
                if (daoPokemon.adiciona(pokemon)) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("Visualizacao.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    VisualizacaoController display = Loader.getController();
                    display.passaId(usuarioID);
                    Parent p = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    stage.show();
                    stage = (Stage) adicionarPokemonButton.getScene().getWindow();
                    stage.close();
                }
            }
        } else {
            System.out.println("preencha os campos");;
        }
    }

    @FXML
    public void meusPokemonsButton(ActionEvent event){
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("Visualizacao.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        VisualizacaoController display = Loader.getController();
        display.passaId(usuarioID);
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) meusPokemonsButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void especieComboBox(ActionEvent event){
        Image image = new Image(especieComboBox.getSelectionModel().getSelectedItem().getUrlFoto());
        pokemonImageView.setImage(image);
    }

    private boolean tudoPreenchido(){
        if (nomeTextField.getText().isEmpty()) {
            return false;
        }
        if (cpTextField.getText().isEmpty()) {
            return false;
        }
        if (psTextField.getText().isEmpty()) {
            return false;
        }
        if (ataqueRapidoTextField.getText().isEmpty()) {
            return false;
        }
        if (ataqueCarregadoTextField.getText().isEmpty()) {
            return false;
        }
        if (especieComboBox.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        return true;
    }

    public void passaId(int id){
        usuarioID = id;
    }



    
}
