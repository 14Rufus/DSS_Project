package Data;

import java.util.List;

public interface ISGS {
    void registarPalete(String qrCode,String tipoMaterial);
    void notificarRobot(String qrCode);
    void recolherPalete(String robotID);
    void entregarPalete(String robotID);
    List<String> consultarListagem();
}
