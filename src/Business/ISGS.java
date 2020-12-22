package Business;

import java.util.List;

public interface ISGS {
    void registarPalete(String qrCode,String tipoMaterial);
    void notificarRobot(String qrCode);
    void recolherPalete(String robotID);
    void entregarPalete(String robotID);
    List<String> consultarListagem();
    boolean isOnline();
    void setOnline(boolean online);
    boolean validaLoginGestor (String password);
}
