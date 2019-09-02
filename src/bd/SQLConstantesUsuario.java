package bd;

public class SQLConstantesUsuario {

    public static final String INSERT = "insert into "
            + "usuario (id, nome, senha) "
            + "values (?,?,?)";

    public static final String LOGIN = "select * from usuario where nome=? and senha=?";

    public static final String VERIFY = "select * from usuario where nome=?";

    public static final String SEARCHID = "select id from usuario where nome=?";


}
