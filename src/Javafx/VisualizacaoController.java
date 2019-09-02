package Javafx;

import bd.DaoEspecie;
import bd.DaoPokemon;
import bd.Pokemon;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualizacaoController implements Initializable {

    private DaoPokemon daoPokemon = new DaoPokemon();
    private DaoEspecie daoEspecie = new DaoEspecie();

    private Pokemon selectedPokemon = new Pokemon();

    private int usuarioID;

    @FXML
    private Button adicionarButton, sairButton, excluirButton, alterarButton;

    @FXML
    private Label nomeLabel, especieLabel, psLabel, cpLabel, ataqueRapidoLabel, ataqueCarregadoLabel;

    @FXML
    private ListView<Pokemon> pokemonListView;
    private ObservableList<Pokemon> listaPokemon = FXCollections.observableArrayList();

    @FXML
    private ImageView pokemonImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        alterarButton.setVisible(false);
        excluirButton.setVisible(false);
        pokemonListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Pokemon> observable, Pokemon oldValue, Pokemon newValue) -> {
            alterarButton.setVisible(true);
            excluirButton.setVisible(true);
            selectedPokemon = newValue;
            try {
                nomeLabel.setText(newValue.getNome());
                especieLabel.setText(daoEspecie.pesquisaUsandoId(newValue.getIdEspecie()).getNome());
                psLabel.setText(Integer.toString(newValue.getPs()));
                cpLabel.setText(Integer.toString(newValue.getCp()));
                ataqueRapidoLabel.setText(newValue.getAtaqueRápido());
                ataqueCarregadoLabel.setText(newValue.getAtaqueCarregado());
                pokemonImageView.setImage(new Image(daoEspecie.pesquisaUsandoId(newValue.getIdEspecie()).getUrlFoto()));
            }catch(NullPointerException e){

            }
        });

    }

    @FXML
    void adicionarButton(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("Adicionar.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AdicionarController display = Loader.getController();
        display.passaId(usuarioID);
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) adicionarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void sairButton(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("telainicial.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) sairButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void excluirButton(ActionEvent event){
        daoPokemon.remove(selectedPokemon);
        alterarButton.setVisible(false);
        excluirButton.setVisible(false);
        nomeLabel.setText("");
        especieLabel.setText("");
        psLabel.setText("");
        cpLabel.setText("");
        ataqueRapidoLabel.setText("");
        ataqueCarregadoLabel.setText("");
        pokemonImageView.setImage(null);
        pokemonListView();
    }

    @FXML
    public void alterarButton(ActionEvent event){
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("Alterar.fxml"));
        try {
            Loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AlterarController display = Loader.getController();
        display.passaId(usuarioID, selectedPokemon.getId());
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.show();
        stage = (Stage) alterarButton.getScene().getWindow();
        stage.close();
    }

    public void passaId(int id){
        usuarioID = id;
        pokemonListView();
    }

    public void pokemonListView(){
        listaPokemon = daoPokemon.pesquisaPokemonsUsuario(usuarioID);
        pokemonListView.setItems(listaPokemon);
        pokemonListView.setCellFactory((list) -> {
            return new ListCell<Pokemon>() {
                @Override
                protected void updateItem(Pokemon item, boolean empty) {
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
}
