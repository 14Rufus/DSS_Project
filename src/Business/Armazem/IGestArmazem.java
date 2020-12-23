package Business.Armazem;

public interface IGestArmazem {
    void registaPalete (String qrCode, String tipoMaterial);
    void notRobot(String qrCode);
    boolean recolheP(String robotID);
     boolean entregaP(String robotID);
}
