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

    public void setInfoTransporte(String qr, int prat, String zona) {
        infoTransporte = new InfoTransporte(qr,prat,zona);
    }

    public InfoTransporte getInfoTransporte() {
        return infoTransporte;
    }

    public void removeInfo(){infoTransporte = null;}

    public String getRobotID() {
        return robotID;
    }

    public void setRobotID(String robotID) {
        this.robotID = robotID;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponibilidade) {
        this.disponivel = disponibilidade;
    }

    public boolean isPaleteRecolhida() {
        return recolheu;
    }

    public void setRecolheu(boolean recolheu) {
        this.recolheu = recolheu;
    }

    public String getZonaID(){
        if(infoTransporte != null)
            return infoTransporte.getZonaID();
        else
            return null;
    }

    public int getPrateleira(){
        if(infoTransporte != null)
            return infoTransporte.getPrateleira();
        else
            return -1;
    }

    public String getQrCode(){
        if(infoTransporte != null)
            return infoTransporte.getQrCode();
        else
            return null;
    }
}
