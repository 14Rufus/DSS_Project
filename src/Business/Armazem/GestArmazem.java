package Business.Armazem;

import Data.RobotDAO;

import java.util.Iterator;
import java.util.Map;

public class GestArmazem implements IGestArmazem {
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

    public boolean existePaleteRececao(String qrCode){
        return zonaRececao.existePalete(qrCode);
    }

    // USE CASE REGISTAR PALETE
    private boolean validaPalete (String qrCode) {
        return zonaRececao.isPaleteValida(qrCode) && zonaArmazenamento.isPaleteValida(qrCode);
    }

    public void registaPalete (String qrCode, String tipoMaterial) {
        if (validaPalete(qrCode)) {
            zonaRececao.acrescentaPalete(qrCode,tipoMaterial);
        }
    }
    // ----------------------------

    public Robot getRobotDisponivel (){
        Robot r = null, res = null;
        Boolean isDisponivel = true;  //flag
        Iterator<Robot> it = robots.values().iterator();
        while (it.hasNext() && isDisponivel) {
            r = it.next();
            if (r.isDisponivel()){
                isDisponivel = false;
                res = r;
            }
        }
        return res;
    }


    public void notRobot(String qrCode){
        if (!existePaleteRececao(qrCode) || getRobotDisponivel() == null){
            System.out.println("robot/palete não disponivel");
        }
        else{
            Robot r = getRobotDisponivel();
            r.setInfoTransporte(qrCode, zonaArmazenamento.escolhePrateleira(),zonaArmazenamento.getZonaID());
            r.setDisponivel(false);
        }
    }

    //  ---------------------------------------------------------

    public void recolheP(String robotID){
        Robot r = robots.get(robotID);

        zonaRececao.recolhePalete(r.getPalete());
        r.setRecolheu(true);

        //alterar localizacao
    }

    public void entregaP(String robotID) {
        Robot r = robots.get(robotID);

        String zona = r.getZonaID();
        int prateleira = r.getPrateleira();
        zonaArmazenamento.arrumaPalete(zona,prateleira,r.getQrCode(),r.getQrCode()); //ver como passar a palete para zonaArmazenamento
        r.setDisponivel(true);
        r.setRecolheu(false);

        //alterar localizacao
    }
}
