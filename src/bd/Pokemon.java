package bd;

public class Pokemon {

    private int id, cp, ps, idUsuario;
    private String nome, ataqueRápido, ataqueCarregado, idEspecie;

    public Pokemon() {
    }

    public Pokemon(int cp, int ps, int idUsuario, String nome, String ataqueRápido, String ataqueCarregado, String idEspecie) {
        this.cp = cp;
        this.ps = ps;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.ataqueRápido = ataqueRápido;
        this.ataqueCarregado = ataqueCarregado;
        this.idEspecie = idEspecie;
    }

    public Pokemon(int id, int cp, int ps, int idUsuario, String nome, String ataqueRápido, String ataqueCarregado, String idEspecie) {
        this.id = id;
        this.cp = cp;
        this.ps = ps;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.ataqueRápido = ataqueRápido;
        this.ataqueCarregado = ataqueCarregado;
        this.idEspecie = idEspecie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtaqueRápido() {
        return ataqueRápido;
    }

    public void setAtaqueRápido(String ataqueRápido) {
        this.ataqueRápido = ataqueRápido;
    }

    public String getAtaqueCarregado() {
        return ataqueCarregado;
    }

    public void setAtaqueCarregado(String ataqueCarregado) {
        this.ataqueCarregado = ataqueCarregado;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", cp=" + cp +
                ", ps=" + ps +
                ", idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", ataqueRápido='" + ataqueRápido + '\'' +
                ", ataqueCarregado='" + ataqueCarregado + '\'' +
                ", idEspecie='" + idEspecie + '\'' +
                '}';
    }
}
