package Business.Armazem;

public interface IGestArmazem {
    void registaPalete (String qrCode, String tipoMaterial);
    void notRobot(String qrCode);
    String recolheP(String robotID);
    String entregaP(String robotID);
}
