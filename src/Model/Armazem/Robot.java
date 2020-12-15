package Model.Armazem;

public class Robot {
    private String robotID;
    private boolean disponibilidade;
    private boolean recolheu;
    private InfoTransporte infoTransporte;

    public Robot(String id, boolean disp, boolean recolheu, InfoTransporte infoTransporte) {
        robotID = id;
        disponibilidade = disp;
        this.recolheu = recolheu;
        this.infoTransporte = infoTransporte;
    }

    public void setInfoTransporte(String qr,String tipo, String prat, String zona) {
        infoTransporte = new InfoTransporte(qr,tipo,prat,zona);
    }

    public InfoTransporte getInfoTransporte() {
        return infoTransporte;
    }

    public String getRobotID() {
        return robotID;
    }

    public void setRobotID(String robotID) {
        this.robotID = robotID;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public boolean isPaleteRecolhida() {
        return recolheu;
    }
}
