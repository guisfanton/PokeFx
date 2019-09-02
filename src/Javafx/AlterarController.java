package Javafx;

import bd.DaoEspecie;
import bd.DaoPokemon;
import bd.Especie;
import bd.Pokemon;
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

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlterarController implements Initializable {
    private DaoPokemon daoPokemon = new DaoPokemon();
    private DaoEspecie daoEspecie = new DaoEspecie();
    private int usuarioID, pokemonID;

    @FXML
    private ImageView pokemonImageView;
    @FXML
    private Button alterarPokemonButton, meusPokemonsButton;
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
    public void alterarPokemonButton(ActionEvent event) {
        Pokemon pokemon = new Pokemon();
        if (tudoPreenchido()) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Está tudo correto?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> bt = alerta.showAndWait();
            if (bt.get() == ButtonType.YES) {
                pokemon.setId(pokemonID);
                pokemon.setNome(nomeTextField.getText());
                pokemon.setCp(Integer.parseInt(cpTextField.getText()));
                pokemon.setPs(Integer.parseInt(psTextField.getText()));
                pokemon.setAtaqueRápido(ataqueRapidoTextField.getText());
                pokemon.setAtaqueCarregado(ataqueCarregadoTextField.getText());
                pokemon.setIdEspecie(especieComboBox.getSelectionModel().getSelectedItem().getId());
                pokemon.setIdUsuario(usuarioID);
                if (daoPokemon.altera(pokemon)) {
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
                    stage = (Stage) alterarPokemonButton.getScene().getWindow();
                    stage.close();
                }
            }
        } else {
            System.out.println("preencha os campos");
            ;
        }
    }

    @FXML
    public void meusPokemonsButton(ActionEvent event) {
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
    public void especieComboBox(ActionEvent event) {
        Image image = new Image(especieComboBox.getSelectionModel().getSelectedItem().getUrlFoto());
        pokemonImageView.setImage(image);
    }


    private boolean tudoPreenchido() {
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

    public void passaId(int usuarioID, int pokemonID) {
        this.usuarioID = usuarioID;
        this.pokemonID = pokemonID;
        Pokemon pokemon = daoPokemon.pesquisaPokemonComID(pokemonID);
        nomeTextField.setText(pokemon.getNome());
        psTextField.setText(Integer.toString(pokemon.getPs()));
        cpTextField.setText(Integer.toString(pokemon.getCp()));
        ataqueRapidoTextField.setText(pokemon.getAtaqueRápido());
        ataqueCarregadoTextField.setText(pokemon.getAtaqueCarregado());
        pokemonImageView.setImage(new Image(daoEspecie.pesquisaUsandoId(pokemon.getIdEspecie()).getUrlFoto()));
        especieComboBox.getSelectionModel().select(daoEspecie.pesquisaUsandoId(pokemon.getIdEspecie()));
    }
}
