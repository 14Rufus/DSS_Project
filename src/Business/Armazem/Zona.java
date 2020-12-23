/**
 *  Classe que representa uma Zona
 */

package Business.Armazem;

public class Zona {
    private String zonaID;

    public Zona(String zonaID) {
        this.zonaID = zonaID;
    }

    /**
     * Devovle ID da Zona
     *
     * @return ID da Zona
     */
    public String getZonaID() {
        return zonaID;
    }
}
