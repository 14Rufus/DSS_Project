/**
 * Classe que representa uma Palete
 */
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

    /**
     * Devolve o Código QR da Palete
     *
     * @return Código QR
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Devolve o Tipo de Material da Palete
     *
     * @return Tipo de Material da Palete
     */
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * Devolve a Prateleira em que a Palete está localizada
     *
     * @return  Prateleira onde a Palete se encontra
     */
    public int getPrateleira() {
        return prateleira;
    }

    /**
     * Devolve o ID da Zona em que a Palete se encontra
     *
     * @return ID da Zona
     */
    public String getZonaID() {
        return zonaID;
    }

    /**
     * Altera ID da Zona
     *
     * @param zonaID novo ID da Zona
     */
    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
