/**
 * Classe que representa a Informação de Transporte
 */

package Business.Armazem;

public class InfoTransporte {
    private String qrCode;
    private int prateleira;
    private String zonaID;

    public InfoTransporte(String qr, int prat, String zona) {
        qrCode = qr;
        prateleira = prat;
        zonaID = zona;
    }

    /**
     * Devovle o Código QR da Palete a Transportar
     *
     * @return Código QR da Palete
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Altera o Código QR da Palete a transportar
     *
     * @param qrCode novo Código QR da Palete
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * Devolve a Prateleira para a qual a Palete está a ser transportada
     *
     * @return Inteiro que representa a Prateleira
     */
    public int getPrateleira() {
        return prateleira;
    }

    /**
     * Altera a Prateleira para a qual a Palete está a ser transportada
     *
     * @param prateleira nova Prateleira
     */
    public void setPrateleira(int prateleira) {
        this.prateleira = prateleira;
    }

    /**
     * Devolve ID da Zona de transporte
     *
     * @return ID da Zona de transporte
     */
    public String getZonaID() {
        return zonaID;
    }

    /**
     * Altera ID da Zona de transporte
     *
     * @param zonaID novo ID da Zona de transporte
     */
    public void setZonaID(String zonaID) {
        this.zonaID = zonaID;
    }
}
