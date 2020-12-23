package Business.Gestor;

import Business.Armazem.Localizacao;
import Data.GestorDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa o GestGestor
 */

public class GestGestor implements IGestGestor {
    public GestorDAO gestor;

    public GestGestor() {
        gestor = new GestorDAO();
    }

    /**
     * Valida os logins do Gestor
     *
     * @return boolean que indica a validade ou invalidade das credenciais inseridas
     */

    public boolean validaLoginGestor(String password) {
        Gestor g = gestor.get();
        return g.validaCredenciaisGestor(password);
    }

    /**
     * Indica se o gestor se encontra ou não online
     *
     * @return boolean que indica se o gestor está online ou não
     */

    public boolean isOnline() {
        Gestor g = gestor.get();
        return g.isOnline();
    }

    /**
     * Atribui a um gestor o estado online
     *
     * @param online boolean que indica se está ou não online
     */

    public void setOnline(boolean online) {
        Gestor g = gestor.get();
        g.setOnline(online);
        gestor.updateGestor(g);
    }
}
