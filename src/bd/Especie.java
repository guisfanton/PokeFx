package bd;

import java.util.Objects;

public class Especie {

    private String id ,nome, urlFoto;

    public Especie() {
    }

    public Especie(String nome, String urlFoto) {
        this.nome = nome;
        this.urlFoto = urlFoto;
    }

    public Especie(String id, String nome, String urlFoto) {
        this.id = id;
        this.nome = nome;
        this.urlFoto = urlFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especie especie = (Especie) o;
        return Objects.equals(id, especie.id) &&
                Objects.equals(nome, especie.nome) &&
                Objects.equals(urlFoto, especie.urlFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, urlFoto);
    }
}
