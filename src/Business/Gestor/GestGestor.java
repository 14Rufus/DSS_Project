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
    public Map<String, Localizacao> listagem;

    public GestGestor() {
        listagem = new HashMap<>();
        gestor = new GestorDAO();
        //gestor = new Gestor(1,"luis","luis",false);
    }

    /**
     * Devolve a listagem total de paletes do armazem
     *
     * @return listagem de paletes do armazem
     */

    public List<String> consultarL(){
        List <String> list = new ArrayList<>();
        for(String s: listagem.keySet()) {
            list.add(s + ": " + listagem.get(s).toString());
        }
        return list;
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
     * Adiciona localizacao
     *
     * @param l localizacao a adicionar
     */

    public void addLocalizacao(String l) {
        String [] list;
        list = l.split(".",3);
        System.out.println(list[0]);
        //listagem.putIfAbsent(list[0],new Localizacao(0,list[2]));
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
