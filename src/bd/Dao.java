package bd;

import javafx.collections.ObservableList;

public interface Dao<E> {

    public boolean adiciona(E m);

    public boolean altera(E m);

    public boolean remove(E m);

    public boolean pesquisa(E m);

    public ObservableList<E> pesquisaTodos();
}