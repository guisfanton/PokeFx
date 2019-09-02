package bd;

public class SQLConstantesEspecie {
    public static final String INSERT = "insert into "
            + "especie (id, nome, urlFoto) "
            + "values (?,?,?)";

    public static final String UPDATE = "update pokemon set "
            + "nome=?, cs=?, cp=?, ataqueRapido=?, ataqueCarregado=?, idEspecie=? where id=?";

    public static final String REMOVE = "delete from pokemon where id=?";

    public static final String SEARCH = "select * from especie";

    public static final String SEARCHUSINGID = "select * from especie where id=?";
}
