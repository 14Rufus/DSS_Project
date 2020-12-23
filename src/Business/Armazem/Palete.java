package Business.Armazem;

public class Palete {
    private String qrCode;
    private String tipoMaterial;
    private Localizacao localizacao;

    public Palete(String qrCode, String tipoMaterial,Localizacao l) {
        this.qrCode = qrCode;
        this.tipoMaterial = tipoMaterial;
        localizacao = l;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public int getPrateleira() {
        return localizacao.getPrateleira();
    }

    public String getZonaID() {
        return localizacao.getZonaID();
    }

    public void setZonaID(String zonaID) {
        localizacao.setZonaID(zonaID);
    }

    public int getLocalizacaoID(){
        return localizacao.getIdLocalizacao();
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
