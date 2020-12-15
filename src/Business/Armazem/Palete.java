package Business.Armazem;

public class Palete {
    private String qrCode;
    private String tipoMaterial;
    private int zona;
    private String prateleira;
    private String zonaID;

    public Palete(String qrCode, String tipoMaterial,int z,String prat,String zID) {
        this.qrCode = qrCode;
        this.tipoMaterial = tipoMaterial;
        zona = z;
        prateleira = prat;
        zonaID = zID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public int getZona() {
        return zona;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public String getZonaID() {
        return zonaID;
    }
}
