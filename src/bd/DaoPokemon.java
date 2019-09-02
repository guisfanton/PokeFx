package bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoPokemon implements Dao<Pokemon> {


    @Override
    public boolean adiciona(Pokemon pokemon) {
        String sql = SQLConstantesPokemon.INSERT;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, pokemon.getNome());
                stmt.setInt(3, pokemon.getCp());
                stmt.setInt(4, pokemon.getPs());
                stmt.setString(5, pokemon.getAtaqueRápido());
                stmt.setString(6, pokemon.getAtaqueCarregado());
                stmt.setString(7, pokemon.getIdEspecie());
                stmt.setInt(8, pokemon.getIdUsuario());
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir dados na tabela Especie!");
            return false;
        }
        return true;
    }

    @Override
    public boolean altera(Pokemon pokemon) {
        String sql = SQLConstantesPokemon.UPDATE;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, pokemon.getNome());
                stmt.setInt(2, pokemon.getCp());
                stmt.setInt(3, pokemon.getPs());
                stmt.setString(4, pokemon.getAtaqueRápido());
                stmt.setString(5, pokemon.getAtaqueCarregado());
                stmt.setString(6, pokemon.getIdEspecie());
                stmt.setInt(7, pokemon.getIdUsuario());
                stmt.setInt(8, pokemon.getId());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar dados do pokemon " + pokemon.getNome());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Pokemon pokemon) {
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(SQLConstantesPokemon.REMOVE)) {
                stmt.setInt(1, pokemon.getId());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover o pokemon " + pokemon.getNome());
            return false;
        }
        return true;
    }

    @Override
    public boolean pesquisa(Pokemon m) {
        return false;
    }

    @Override
    public ObservableList<Pokemon> pesquisaTodos() {
        ObservableList<Pokemon> pokemons = FXCollections.observableArrayList();
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(SQLConstantesPokemon.SEARCH);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setId(rs.getInt("id"));
                    pokemon.setNome(rs.getString("nome"));
                    pokemon.setPs(rs.getInt("ps"));
                    pokemon.setCp(rs.getInt("cp"));
                    pokemon.setAtaqueRápido(rs.getString("ataqueRapido"));
                    pokemon.setAtaqueCarregado(rs.getString("ataqueCarregado"));
                    pokemon.setIdEspecie(rs.getString("idEspecie"));
                    pokemon.setIdUsuario(rs.getInt("idUsuario"));
                    pokemons.add(pokemon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return pokemons;
    }

    public ObservableList<Pokemon> pesquisaPokemonsUsuario(int id){
        ObservableList<Pokemon> pokemons = FXCollections.observableArrayList();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesPokemon.SEARCHWITHUSERID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setId(rs.getInt("id"));
                    pokemon.setNome(rs.getString("nome"));
                    pokemon.setPs(rs.getInt("ps"));
                    pokemon.setCp(rs.getInt("cp"));
                    pokemon.setAtaqueRápido(rs.getString("ataqueRapido"));
                    pokemon.setAtaqueCarregado(rs.getString("ataqueCarregado"));
                    pokemon.setIdEspecie(rs.getString("idEspecie"));
                    pokemon.setIdUsuario(rs.getInt("idUsuario"));
                    pokemons.add(pokemon);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return pokemons;
    }

    public Pokemon pesquisaPokemonComID(int id){
        Pokemon pokemon = new Pokemon();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesPokemon.SEARCHUSINGID);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    pokemon.setId(id);
                    pokemon.setId(rs.getInt("id"));
                    pokemon.setNome(rs.getString("nome"));
                    pokemon.setPs(rs.getInt("ps"));
                    pokemon.setCp(rs.getInt("cp"));
                    pokemon.setAtaqueRápido(rs.getString("ataqueRapido"));
                    pokemon.setAtaqueCarregado(rs.getString("ataqueCarregado"));
                    pokemon.setIdEspecie(rs.getString("idEspecie"));
                    pokemon.setIdUsuario(rs.getInt("idUsuario"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return pokemon;
    }
}
