package Business.Armazem;

import java.util.List;

public interface IGestArmazem {
    boolean registaPalete (String qrCode);
    String notRobot(String qrCode);
    String recolheP(String robotID);
    String entregaP(String robotID);
    List<String> getListagem();
}
