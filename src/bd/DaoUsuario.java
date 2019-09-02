package bd;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario implements Dao<Usuario> {
    @Override
    public boolean adiciona(Usuario user) {
        String sql = SQLConstantesUsuario.INSERT;
        try {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, null);
                stmt.setString(2, user.getNome());
                stmt.setString(3, user.getSenha());
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao inserir dados na tabela Usuario!");
            return false;
        }
        return true;
    }

    @Override
    public boolean altera(Usuario m) {
        return false;
    }

    @Override
    public boolean remove(Usuario m) {
        return false;
    }

    @Override
    public boolean pesquisa(Usuario m) {
        return false;
    }

    @Override
    public ObservableList<Usuario> pesquisaTodos() {
        return null;
    }

    public boolean login(Usuario usuario){
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesUsuario.LOGIN);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            {
                if(rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return false;
    }

    public boolean verifica(String nome){
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesUsuario.VERIFY);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            {
                if(rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return false;
    }

    public int pesquisaId(String nome){
        int id = -1;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SQLConstantesUsuario.SEARCHID);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por contatos no banco de dados!");
        }
        return id;
    }
}
