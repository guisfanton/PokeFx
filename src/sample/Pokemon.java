package sample;

public class Pokemon {

    private Integer id;
    private String nome;
    private PokemonEspecie especie;

    public Pokemon(Integer id, String nome, PokemonEspecie especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public PokemonEspecie getEspecie() {
        return especie;
    }
}
