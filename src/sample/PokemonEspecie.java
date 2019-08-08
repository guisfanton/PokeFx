package sample;

public enum PokemonEspecie {

    PIKACHU(0);

    private Integer id;

    PokemonEspecie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static PokemonEspecie getById(Integer id) {
        for (PokemonEspecie especie : values()){
            if (especie.getId() == id){
                return especie;
            }
        }
        return null;
    }
}
