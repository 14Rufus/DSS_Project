package Business.Gestor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestGestor implements IGestGestor {
    public Gestor gestor;
    public Map<String,Localizacao> listagem;

    public GestGestor() {
        listagem = new HashMap<>();
        //gestor = new GestorDAO();
        gestor = new Gestor(1,"luis","luis",false);
    }

    public List<String> consultarL(){
        List <String> list = new ArrayList<>();
        for(String s: listagem.keySet()) {
            list.add(s + ": " + listagem.get(s).toString());
        }
        return list;
    }

    public boolean validaLoginGestor(String password) {
        return gestor.validaCredenciaisGestor(password);
    }

    public void addLocalizacao(String l) {
        String [] list;
        list = l.split(".",3);
        System.out.println(list[0]);
        //System.out.println(list[0]+"  "+list[1]+"  "+list[2]);
        listagem.putIfAbsent(list[0],new Localizacao(0,list[2]));
    }

    public boolean isOnline() {
        return gestor.isOnline();
    }

    public void setOnline(boolean online) {
        gestor.setOnline(online);
    }
}
