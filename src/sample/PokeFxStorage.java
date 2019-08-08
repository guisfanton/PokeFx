package sample;

public class PokeFxStorage extends Database{



    public PokeFxStorage(String host, Integer port, String database, String user, String password) {
        super(host, port, database, user, password);


        /*query("CREATE TABLE IF NOT EXISTS " + TABLE_FLAG + "("
                + "region VARCHAR(16) PRIMARY KEY NOT NULL,"
                + "flag VARCHAR(16) NOT NULL,"
                + "value VARCHAR(256) NOT NULL);");*/
    }
}
