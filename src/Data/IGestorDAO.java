package Data;

import Business.Gestor.Gestor;

public interface IGestorDAO {
    GestorDAO getInstance();
    void updateGestor(String key, Gestor p);
}
