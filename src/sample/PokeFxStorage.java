package sample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokeFxStorage extends Database{

    private static final String TABELA_POKEMONS = "pokemons";

    public PokeFxStorage(String host, Integer port, String database, String user, String password) {
        super(host, port, database, user, password);

        query("CREATE TABLE IF NOT EXISTS " + TABELA_POKEMONS + "("
                + "idpokemon INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + "especie INTEGER NOT NULL,"
                + "nome VARCHAR(256) NOT NULL);");
    }

    public void addPokemon(PokemonEspecie pokemon, String nome) {
        query("INSERT INTO "+ TABELA_POKEMONS +" (especie, nome) VALUES (?, ?);",
                pokemon.getId(),
                nome);
    }

    public List<Pokemon> getAllPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + TABELA_POKEMONS +";");

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Pokemon pokemon = new Pokemon(result.getInt("idpokemon"), result.getString("nome"), PokemonEspecie.getById(result.getInt("especie")));
                pokemons.add(pokemon);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemons;
    }
}
