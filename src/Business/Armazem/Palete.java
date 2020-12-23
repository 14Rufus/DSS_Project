/**
 * Classe que representa uma Palete
 */
package Business.Armazem;

public class Palete {
    private String qrCode;
    private Localizacao localizacao;

    public Palete(String qrCode, Localizacao l) {
        this.qrCode = qrCode;
        localizacao = l;
    }

    public Palete(String qrCode,int prat,String zona,int id) {
        this.qrCode = qrCode;
        localizacao = new Localizacao(id,prat,zona);
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
     * Devolve a Prateleira em que a Palete está localizada
     *
     * @return  Prateleira onde a Palete se encontra
     */
    public int getPrateleira() {
        return localizacao.getPrateleira();
    }

    /**
     * Devolve o ID da Zona em que a Palete se encontra
     *
     * @return ID da Zona
     */
    public String getZonaID() {
        return localizacao.getZonaID();
    }

    /**
     * Altera ID da Zona
     *
     * @param zonaID novo ID da Zona
     */
    public void setZonaID(String zonaID) {
        localizacao.setZonaID(zonaID);
    }

    /**
     * Devolve o ID da Localização onde a Palete se encontra
     *
     * @return ID da Localização
     */
    public int getLocalizacaoID(){
        return localizacao.getIdLocalizacao();
    }

    /**
     * Devolve a Localização da Palete
     *
     * @return LOcalização da Palete
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Altera Localização da Palete
     *
     * @param localizacao nova Localização
     */
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
