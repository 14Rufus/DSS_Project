package Business;

import java.util.List;

public interface IGestGestor {
    List<String> consultarL();
    void addLocalizacao(String l);
    boolean validaLoginGestor(int id, String password);
}
