package sample;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pokemon {

    private StringProperty id;
    private StringProperty nome;
    private ObjectProperty<PokemonEspecie> especie;

    public Pokemon(String id, String nome, String especie) {
        this.id = new SimpleStringProperty(id);
	this.nome = new SimpleStringProperty(nome);
	this.especie = new SimpleObjectProperty(especie);
    }

    public String getId() {
	return id.get();
    }

    public void setId(String id) {
	this.id.set(id);
    }
	
    public StringProperty idProperty() {
	return id;
    }
    
    public String getNome() {
	return nome.get();
    }

    public void setNome(String nome) {
	this.nome.set(nome);
    }
	
    public StringProperty nomeProperty() {
	return nome;
    }
    
    public PokemonEspecie getEspecie() {
	return especie.get();
    }

    public void setEspecie(PokemonEspecie especie) {
	this.especie.set(especie);
    }
	
    public ObjectProperty<PokemonEspecie> especieProperty() {
	return especie;
    }
}
