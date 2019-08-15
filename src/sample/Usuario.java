package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final StringProperty idUsuario;
    private final StringProperty login;
    private final StringProperty senha;

    public Usuario(String idUsuario, String login, String senha) {
        this.idUsuario = new SimpleStringProperty(idUsuario);
	this.login = new SimpleStringProperty(login);
	this.senha = new SimpleStringProperty(senha);
    }
    
    public String getIdUsuario() {
	return idUsuario.get();
    }

    public void setIdUsuario(String idUsuario) {
	this.idUsuario.set(idUsuario);
    }
	
    public StringProperty idUsuarioProperty() {
	return idUsuario;
    }
    
    public String getLogin() {
	return login.get();
    }

    public void setLogin(String login) {
	this.login.set(login);
    }
	
    public StringProperty loginProperty() {
	return login;
    }
    
    public String getSenha() {
	return senha.get();
    }

    public void setSenha(String senha) {
	this.senha.set(senha);
    }
	
    public StringProperty senhaProperty() {
	return senha;
    }
            
}
