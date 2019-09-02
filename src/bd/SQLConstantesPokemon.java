package bd;

public class SQLConstantesPokemon {

    public static final String INSERT = "insert into "
            + "pokemon (id, nome, cp, ps, ataqueRapido, ataqueCarregado, idEspecie, idUsuario) "
            + "values (?,?,?,?,?,?,?,?)";

    public static final String UPDATE = "update pokemon set "
            + "nome=?, cp=?, ps=?, ataqueRapido=?, ataqueCarregado=?, idEspecie=?, idUsuario=? where id=?";

    public static final String REMOVE = "delete from pokemon where id=?";

    public static final String SEARCH = "select * from pokemon";

    public static final String SEARCHWITHUSERID = "select * from pokemon where idUsuario = ?";

    public static final String SEARCHUSINGID = "select * from pokemon where id = ?";
}
