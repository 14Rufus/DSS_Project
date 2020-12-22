package Business.Gestor;

import java.util.List;

public interface IGestGestor {
    List<String> consultarL();
    void addLocalizacao(String l);
    boolean validaLoginGestor(String password);
    boolean isOnline();
    void setOnline(boolean online);
}
