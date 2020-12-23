package Business.Armazem;

public class Robot {
    private String robotID;
    private boolean disponivel;
    private boolean recolheu;
    private InfoTransporte infoTransporte;
    private Localizacao localizacao;

    public Robot(String id, boolean disp, boolean recolheu, InfoTransporte infoTransporte,Localizacao l) {
        robotID = id;
        disponivel = disp;
        this.recolheu = recolheu;
        this.infoTransporte = infoTransporte;
        localizacao = l;
    }

    public Robot(String id) {
        robotID = id;
        disponivel = false;
        this.recolheu = false;
        this.infoTransporte = null;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public int getLocalizacaoID(){
        return localizacao.getIdLocalizacao();
    }

    public void setLocalizacao(String zona, int prat) {
        this.localizacao = localizacao;
    }

    public void setInfoTransporte(int id,String qr, int prat) {
        infoTransporte = new InfoTransporte(id,qr,prat);
    }

    public InfoTransporte getInfoTransporte() {
        return infoTransporte;
    }

    public void removeInfo(){ infoTransporte = null;}

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
