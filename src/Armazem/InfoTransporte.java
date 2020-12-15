package Armazem;

public class InfoTransporte {
    private String qrCode;
    private String tipo;
    private String prateleira;
    private String zonaID;

    public InfoTransporte(String qr, String t, String prat, String zona) {
        qrCode = qr;
        tipo = t;
        prateleira = prat;
        zonaID = zona;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public String getZonaID() {
        return zonaID;
    }

    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
