package Business;

import java.util.List;

public interface ISGS {
    boolean registarPalete(String qrCode,String tipoMaterial);
    String notificarRobot(String qrCode);
    String recolherPalete(String robotID);
    String entregarPalete(String robotID);
    List<String> consultarListagem();
    boolean isOnline();
    void setOnline(boolean online);
    boolean validaLoginGestor (String password);
}
