package Business.Armazem;

public interface IGestArmazem {
    void registaPalete (String qrCode, String tipoMaterial);
    void notRobot(String qrCode);
    void recolheP(String robotID);
    void entregaP(String robotID);
}
