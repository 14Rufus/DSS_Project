package Business.Armazem;

import Data.RobotDAO;

import java.util.Map;

public class GestArmazem {
    private Map<String, Robot> robots;
    private ZonaRececao zonaRececao;
    private ZonaArmazenamento zonaArmazenamento;

    public GestArmazem() {
        this.robots = RobotDAO.getInstance();
    }

    public void addRobot(String robotID) {
        robots.put(robotID,new Robot(robotID));
    }

    public void remRobot(String robotID) {
        robots.remove(robotID);
    }

    public void getRobots(){
        System.out.println(robots.values().toString());
    }

    public boolean validaPalete (String qrCode) {
        return zonaRececao.isPaleteValida(qrCode) && zonaArmazenamento.isPaleteValida(qrCode);
    }

    public void registaPalete (String qrCode, String tipoMaterial) {
        if (validaPalete(qrCode)) {

        }
    }
}
