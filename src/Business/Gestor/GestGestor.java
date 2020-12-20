package Business.Gestor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestGestor {
    public Gestor gestor;
    public Map<String,Localizacao> listagem;

    public GestGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public List<String> consultarL(){
        List <String> list = new ArrayList<>();
        for(String s: listagem.keySet()) {
            list.add(s + ": " + listagem.get(s).toString());
        }
        return list;
    }
}
