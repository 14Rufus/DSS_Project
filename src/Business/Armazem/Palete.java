package Business.Armazem;

public class Palete {
    private String qrCode;
    private String tipoMaterial;
    private int prateleira;
    private String zonaID;

    public Palete(String qrCode, String tipoMaterial,int prat,String zID) {
        this.qrCode = qrCode;
        this.tipoMaterial = tipoMaterial;
        prateleira = prat;
        zonaID = zID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public int getPrateleira() {
        return prateleira;
    }

    public String getZonaID() {
        return zonaID;
    }

    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
