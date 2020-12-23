/**
 *  Classe que representa um robot
 */

package Business.Armazem;

public class Robot {
    private String robotID;
    private boolean disponivel;
    private boolean recolheu;
    private InfoTransporte infoTransporte;

    public Robot(String id, boolean disp, boolean recolheu, InfoTransporte infoTransporte) {
        robotID = id;
        disponivel = disp;
        this.recolheu = recolheu;
        this.infoTransporte = infoTransporte;
    }

    public Robot(String id) {
        robotID = id;
        disponivel = false;
        this.recolheu = false;
        this.infoTransporte = null;
    }

    /**
     * Altera Informação de Transporte
     *
     * @param qr Código QR da palete
     * @param prat Prateleira
     * @param zona Zona
     */
    public void setInfoTransporte(String qr, int prat, String zona) {
        infoTransporte = new InfoTransporte(qr,prat,zona);
    }

    /**
     * Devolve a Informação de Transporte
     *
     * @return Informação de Transporte
     */
    public InfoTransporte getInfoTransporte() {
        return infoTransporte;
    }

    /**
     * Apaga Informação de Transporte
     */
    public void removeInfo(){infoTransporte = null;}

    /**
     * Devolve ID do Robot
     *
     * @return ID do Robot
     */
    public String getRobotID() {
        return robotID;
    }

    /**
     * Altera ID do Robot
     *
     * @param robotID ID do Robot
     */
    public void setRobotID(String robotID) {
        this.robotID = robotID;
    }

    /**
     * Verifica se o Robot está disponível
     *
     * @return Disponibilidade do Robot
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Altera disponibilidade do Robot
     *
     * @param disponibilidade Disponobilidade do Robot
     */
    public void setDisponivel(boolean disponibilidade) {
        this.disponivel = disponibilidade;
    }

    /**
     * Verifica se o Robot tem uma palete que recolheu
     *
     * @return Booleano que representa se o robot recolheu a palete
     */
    public boolean isPaleteRecolhida() {
        return recolheu;
    }

    /**
     * Altera se o robot tem palete
     *
     * @param recolheu Robot tem ou não palete
     */
    public void setRecolheu(boolean recolheu) {
        this.recolheu = recolheu;
    }

    /**
     * Devolve a Zona ID do Robot
     *
     * @return Zona ID do Robot
     */
    public String getZonaID(){
        if(infoTransporte != null)
            return infoTransporte.getZonaID();
        else
            return null;
    }

    /**
     * Devolve prateleira em que o Robot se encontra
     *
     * @return Prateleira em que o Robot se encontra
     */
    public int getPrateleira(){
        if(infoTransporte != null)
            return infoTransporte.getPrateleira();
        else
            return -1;
    }

    /**
     * Devolve o Código QR da palete que o Robot transporta
     *
     * @return Código QR da Palete
     */
    public String getQrCode(){
        if(infoTransporte != null)
            return infoTransporte.getQrCode();
        else
            return null;
    }
}
