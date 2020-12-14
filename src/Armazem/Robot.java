public class Robot {
    private String robotID;
    private boolean disponibilidade;
    private InfoTransporte infoTransporte;

    public Robot(String id, boolean disp) {
        robotID = id;
        disponibilidade = disp;
    }

    public void setInfoTransporte(String qr,String tipo, String prat, String zona) {
        infoTransporte = new InfoTransporte(qr,tipo,prat,zona);
    }
}
