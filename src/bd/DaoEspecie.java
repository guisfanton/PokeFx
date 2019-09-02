package bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoEspecie implements Dao<Especie> {

    @Override
    public boolean adiciona(Especie especie) {
        String sql = SQLConstantesEspecie.INSERT;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, especie.getId());
                stmt.setString(2, especie.getNome());
                stmt.setString(3, especie.getUrlFoto());
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
    public boolean altera(Especie m) {
        return false;
    }

    @Override
    public boolean remove(Especie m) {
        return false;
    }

    @Override
    public boolean pesquisa(Especie m) {
        return false;
    }

    @Override
    public ObservableList<Especie> pesquisaTodos() {
        ObservableList<Especie> especies = FXCollections.observableArrayList();
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(SQLConstantesEspecie.SEARCH);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Especie especie = new Especie();
                    especie.setId(rs.getString("id"));
                    especie.setNome(rs.getString("nome"));
                    especie.setUrlFoto(rs.getString("urlFoto"));
                    especies.add(especie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return especies;
    }

    public boolean carregaEspecies() {
        File folder = new File("src/fotospokemon");
        for (final File fileEntry : folder.listFiles()) {
            String id = fileEntry.getName().substring(0, 3);
            String nome = fileEntry.getName().substring(3, fileEntry.getName().length()-4);
            String urlFoto = fileEntry.toURI().toString();
            Especie especie = new Especie(id, nome, urlFoto);
            if(!pesquisaTodos().contains(especie)) {
                adiciona(especie);
            }
        }
        return true;
    }

    public Especie pesquisaUsandoId(String id){
        Especie especie = new Especie();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesEspecie.SEARCHUSINGID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    especie.setId(id);
                    especie.setNome(rs.getString("nome"));
                    especie.setUrlFoto(rs.getString("urlFoto"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return especie;
    }
}
