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
        zonaRececao = new ZonaRececao("Receção");
        zonaArmazenamento = new ZonaArmazenamento("z1","a");
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
        if (validaPalete(qrCode))
            zonaRececao.acrescentaPalete(qrCode,tipoMaterial);
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
        Robot r = getRobotDisponivel();
        if (!existePaleteRececao(qrCode) || r == null){
            System.out.println("robot/palete não disponivel");//print na view
        }
        else{
            r.setInfoTransporte(qrCode, zonaArmazenamento.escolhePrateleira(),zonaArmazenamento.getZonaID());
            r.setDisponivel(false);
        }
    }

    //  ---------------------------------------------------------

    public String recolheP(String robotID){
        Robot r = robots.get(robotID);
        if(r == null)
            return null;

        String qrCode = r.getQrCode();
        if(qrCode == null)
            return null;

        zonaRececao.recolhePalete(qrCode);
        r.setRecolheu(true);

        return qrCode + ".-1." + robotID;
    }

    public String entregaP(String robotID) {
        Robot r = robots.get(robotID);
        if(r == null)
            return null;

        String zona = r.getZonaID(), qrCode = r.getQrCode();
        int prateleira = r.getPrateleira();
        if(zona == null || prateleira == -1)
            return null;

        zonaArmazenamento.arrumaPalete(zona,prateleira,qrCode,qrCode); //ver como passar a palete para zonaArmazenamento
        r.setDisponivel(true);
        r.setRecolheu(false);
        r.removeInfo();

        return qrCode + "." + prateleira + "." + zona;
    }
}
